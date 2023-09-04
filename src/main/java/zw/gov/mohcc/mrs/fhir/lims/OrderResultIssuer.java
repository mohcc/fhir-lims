package zw.gov.mohcc.mrs.fhir.lims;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.DiagnosticReport;
import org.hl7.fhir.r4.model.Identifier;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Quantity;
import org.hl7.fhir.r4.model.Reference;
import org.hl7.fhir.r4.model.Resource;
import org.hl7.fhir.r4.model.Task;
import zw.gov.mohcc.mrs.fhir.lims.entities.AnalysisService;
import zw.gov.mohcc.mrs.fhir.lims.entities.Instrument;
import zw.gov.mohcc.mrs.fhir.lims.entities.LabAnalysis;
import zw.gov.mohcc.mrs.fhir.lims.entities.Method;
import zw.gov.mohcc.mrs.fhir.lims.entities.Sample;

public class OrderResultIssuer {

    private OrderResultIssuer() {

    }

    public static void submitResults(Task task, Collection<LabAnalysis> labAnalysisCollection) {

        List<Observation> observations = new ArrayList<>();

        //We call this Result Bundle
        List<Resource> fhirResources = new ArrayList<>();

        task.setStatus(Task.TaskStatus.COMPLETED);
        Task.TaskOutputComponent output = new Task.TaskOutputComponent();

        DiagnosticReport diagnosticReport = getDiagnosticReport(task);
        String diagnosticReportId = diagnosticReport.getIdElement().getIdPart();
        Reference diagnosticReportReference = FhirReferenceCreator.getReference(diagnosticReportId, "DiagnosticReport");
        output.setValue(diagnosticReportReference);
        task.setOutput(Collections.singletonList(output));

        List<Reference> observationReferences = new ArrayList<>();
        diagnosticReport.setResult(observationReferences);

        for (LabAnalysis labAnalysis : labAnalysisCollection) {
            Observation observation = getObservation(task, labAnalysis);
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

    public static DiagnosticReport getDiagnosticReport(Task task) {
        DiagnosticReport diagnosticReport = new DiagnosticReport();
        diagnosticReport.setId(UUID.randomUUID().toString());
        diagnosticReport.setCode(new CodeableConcept(new Coding("http://loinc.org", "22748-8", "")));
        diagnosticReport.setSubject(task.getFor());

        return diagnosticReport;
    }

    //Lab Analyses
    public static Observation getObservation(Task task, LabAnalysis labAnalysis) {
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
        observation.setValue(new Quantity().setValue(resultValue).setUnit("UI/L"));
        return observation;
    }

    public static Reference getReference(String id, String type, String display, boolean hasReference) {
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

    public static void submitResults(String taskId, Collection<LabAnalysis> labAnalysisCollection) {
        Task task = OrderFinder.findTaskById(taskId);
        if (task == null) {
            System.out.println("Task(" + taskId + ") not found");
            throw new RuntimeException("Task(" + taskId + ") not found");
        }

        submitResults(task, labAnalysisCollection);
    }

    public static void submitResults(Sample sample) {
        String taskId = sample.getClientOrderNumber();
        submitResults(taskId, sample.getLabAnalyses());
    }

}
