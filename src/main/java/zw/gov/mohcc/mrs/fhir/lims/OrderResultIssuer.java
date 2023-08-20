package zw.gov.mohcc.mrs.fhir.lims;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.DiagnosticReport;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Quantity;
import org.hl7.fhir.r4.model.Reference;
import org.hl7.fhir.r4.model.Resource;
import org.hl7.fhir.r4.model.Task;

public class OrderResultIssuer {

    private OrderResultIssuer() {

    }

    public static void issueResult(Task task) {

        List<Resource> fhirResources = new ArrayList<>();

        task.setStatus(Task.TaskStatus.COMPLETED);
        Task.TaskOutputComponent output = new Task.TaskOutputComponent();

        DiagnosticReport diagnosticReport = getDiagnosticReport(task);
        String diagnosticReportId = diagnosticReport.getIdElement().getIdPart();
        Reference diagnosticReportReference = getReference(diagnosticReportId, "DiagnosticReport");
        output.setValue(diagnosticReportReference);
        task.setOutput(Collections.singletonList(output));

        Observation observation = getObservation(task);        

        String observationId = observation.getIdElement().getIdPart();
        Reference observationReference = getReference(observationId, "Observation");
        diagnosticReport.setResult(Collections.singletonList(observationReference));

        fhirResources.add(observation);
        fhirResources.add(diagnosticReport);
        fhirResources.add(task);

        FhirResoucesSaver.saveFhirResources(fhirResources);

    }

    public static DiagnosticReport getDiagnosticReport(Task task) {
        DiagnosticReport diagnosticReport = new DiagnosticReport();
        diagnosticReport.setId(UUID.randomUUID().toString());
        diagnosticReport.setCode(new CodeableConcept(new Coding("http://loinc.org", "22748-8", "")));
        diagnosticReport.setSubject(task.getFor());
        return diagnosticReport;
    }

    //Analysis
    public static Observation getObservation(Task task) {
        Observation observation = new Observation();
        observation.setId(UUID.randomUUID().toString());
        observation.setSubject(task.getFor());
        //Add Test Analysis Code
        observation.setCode(new CodeableConcept(new Coding("http://loinc.org", "22748-8", "")));
        observation.setValue(new Quantity().setValue(Math.random() * 500).setUnit("UI/L"));
        return observation;
    }

    

     private static Reference getReference(String id, String type) {
        Reference reference = new Reference();      
        reference.setReference(type + "/" + id);
        reference.setType(type);
        return reference;
    }

}
