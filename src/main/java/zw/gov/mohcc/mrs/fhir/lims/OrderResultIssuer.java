package zw.gov.mohcc.mrs.fhir.lims;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import org.hl7.fhir.r4.model.BooleanType;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.DiagnosticReport;
import org.hl7.fhir.r4.model.Extension;
import org.hl7.fhir.r4.model.Identifier;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Quantity;
import org.hl7.fhir.r4.model.Reference;
import org.hl7.fhir.r4.model.Resource;
import org.hl7.fhir.r4.model.Task;
import zw.gov.mohcc.mrs.fhir.lims.entities.AnalysisService;
import zw.gov.mohcc.mrs.fhir.lims.entities.AnalysisTemplate;
import zw.gov.mohcc.mrs.fhir.lims.entities.Instrument;
import zw.gov.mohcc.mrs.fhir.lims.entities.LabAnalysis;
import zw.gov.mohcc.mrs.fhir.lims.entities.LabContact;
import zw.gov.mohcc.mrs.fhir.lims.entities.Method;
import zw.gov.mohcc.mrs.fhir.lims.entities.Sample;
import static zw.gov.mohcc.mrs.fhir.lims.util.DateTimeUtils.convertToDate;

public class OrderResultIssuer {

    private OrderResultIssuer() {

    }

    public static void publishResults(Sample sample) {

        String taskId = sample.getClientOrderNumber();
        Collection<LabAnalysis> labAnalysisCollection = sample.getLabAnalyses();

        Task task = OrderFinder.findTaskById(taskId);
        if (task == null) {
            System.out.println("Task(" + taskId + ") not found");
            throw new RuntimeException("Task(" + taskId + ") not found");
        }

        List<Observation> observations = new ArrayList<>();

        //Fhir Resources for the Result Bundle
        List<Resource> fhirResources = new ArrayList<>();

        task.setStatus(Task.TaskStatus.COMPLETED);
        if (sample.getCriticalResult() != null) {
            addCriticalResult(task, sample.getCriticalResult());
        }

        Task.TaskOutputComponent output = new Task.TaskOutputComponent();

        DiagnosticReport diagnosticReport = getDiagnosticReport(sample, task);
        String diagnosticReportId = diagnosticReport.getIdElement().getIdPart();
        Reference diagnosticReportReference = FhirReferenceCreator.getReference(diagnosticReportId, "DiagnosticReport");
        output.setValue(diagnosticReportReference);
        task.setOutput(Collections.singletonList(output));

        List<Reference> observationReferences = new ArrayList<>();
        diagnosticReport.setResult(observationReferences);

        for (LabAnalysis labAnalysis : labAnalysisCollection) {
            Observation observation = getObservation(sample, task, labAnalysis);
            String observationId = observation.getIdElement().getIdPart();
            Reference observationReference = FhirReferenceCreator.getReference(observationId, "Observation");
            diagnosticReport.getResult().add(observationReference);
            observations.add(observation);
        }

        //Result Bundle to be posted to SHR
        //NB:: Start with Observations, followed by Diagnostic Reports, and finally Tasks
        for (Observation observation : observations) {
            fhirResources.add(observation);
        }
        fhirResources.add(diagnosticReport);
        fhirResources.add(task);

        //Save this Result Bundle in the Shared Health Record (SHR):: OpenHIE
        FhirResourcesSaver.saveFhirResources(fhirResources);

    }

    private static void addCriticalResult(Task task, boolean criticalResult) {
        Extension extension = new Extension();
        extension.setId("criticalResult-" + task.getIdElement().getIdPart());
        extension.setUrl("urn:task:criticalResult");
        extension.setValue(new BooleanType(criticalResult));
        task.addExtension(extension);
    }

    public static DiagnosticReport getDiagnosticReport(Sample sample, Task task) {
        LabContact submitter = sample.getSubmitter();
        LabContact verifier = sample.getVerifier();
        LocalDate dateSubmitted = sample.getDateSubmitted();
        LocalDate dateVerified = sample.getDateVerified();
        AnalysisTemplate sampleTemplate = sample.getSampleTemplate();

        DiagnosticReport diagnosticReport = new DiagnosticReport();
        diagnosticReport.setId(UUID.randomUUID().toString());

        diagnosticReport.setCode(new CodeableConcept(new Coding("urn:lims:code", sampleTemplate.getCode(), sampleTemplate.getTitle())));
        diagnosticReport.setSubject(task.getFor());

        //Submitter
        if (submitter != null) {
            Reference submitterReference = getLabContactReference(submitter);
            diagnosticReport.setPerformer(Collections.singletonList(submitterReference));
        }

        //Verifier
        if (verifier != null) {
            Reference verifierReference = getLabContactReference(verifier);
            diagnosticReport.setResultsInterpreter(Collections.singletonList(verifierReference));
        }

        //Date Submitted
        if (dateSubmitted != null) {
            diagnosticReport.setEffective(new DateTimeType(convertToDate(dateSubmitted)));
        }

        //Date Verified
        if (dateVerified != null) {
            diagnosticReport.setIssued(convertToDate(dateVerified));
        }

        return diagnosticReport;
    }

    //Lab Analyses
    public static Observation getObservation(Sample sample, Task task, LabAnalysis labAnalysis) {
        double resultValue = labAnalysis.getResultValue();
        AnalysisService analysisService = labAnalysis.getAnalysis();
        Method method = labAnalysis.getMethod();
        Instrument instrument = labAnalysis.getInstrument();

        Observation observation = new Observation();
        observation.setId(UUID.randomUUID().toString());
        observation.setSubject(task.getFor());
        observation.setBasedOn(task.getBasedOn());

        //Device or Instrument
        Reference deviceReference = getReference(instrument.getCode(), "Device", instrument.getTitle(), false);
        observation.setDevice(deviceReference);

        //Method
        observation.setMethod(new CodeableConcept(new Coding("urn:lims:code", method.getCode(), method.getTitle())));

        //AnalysisService coding
        observation.setCode(new CodeableConcept(new Coding("urn:lims:code", analysisService.getCode(), analysisService.getTitle())));
        //Result
        observation.setValue(new Quantity().setValue(resultValue).setUnit(analysisService.getUnit()));
        return observation;
    }
    
    public void addResultInterpretations(Observation observation){            
        addResultIntepretation(observation, "HVL", "High Viral Load");
        addResultIntepretation(observation, "VL", "1. VL <= 1000 copies/ml: Continue on current Regimen. 2. VL > 1000 copies/ml: Clinical and Counseling action Required");
    }

    protected void addCriticalResult(Observation observation, boolean criticalResult) {
        CodeableConcept codeableConcept = new CodeableConcept();
        codeableConcept.setText("CRITICAL_RESULT");
        Coding coding = new Coding();
        coding.setSystem("urn:lims:code");
        coding.setCode(String.valueOf(criticalResult));
        coding.setDisplay(String.valueOf(criticalResult));
        codeableConcept.addCoding(coding);
        observation.addInterpretation(codeableConcept);
    }

    protected void addResultIntepretation(Observation observation, String interpretationCode, String interpretationText) {
        CodeableConcept codeableConcept = new CodeableConcept();
        codeableConcept.setText("RESULT_INTERPRETATION");
        Coding coding = new Coding();
        coding.setSystem("urn:lims:code");
        coding.setCode(interpretationCode);
        coding.setDisplay(interpretationText);
        codeableConcept.addCoding(coding);
        observation.addInterpretation(codeableConcept);
    }

    private static Reference getLabContactReference(LabContact labContact) {
        return getReference(labContact.getLabContactId(), "Practitioner", labContact.getFullname(), false);
    }

    private static Reference getReference(String id, String type, String display, boolean hasReference) {
        Reference reference = new Reference();
        if (hasReference) {
            reference.setReference(type + "/" + id).setType(type);
        } else {
            reference.setType(type);
            Identifier identifier = new Identifier();
            identifier.setSystem("urn:lims:code");
            identifier.setValue(id);
            identifier.setUse(Identifier.IdentifierUse.SECONDARY);
            reference.setDisplay(display);
            reference.setIdentifier(identifier);
        }
        return reference;
    }

}
