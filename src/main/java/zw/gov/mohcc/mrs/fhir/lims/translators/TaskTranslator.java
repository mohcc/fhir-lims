package zw.gov.mohcc.mrs.fhir.lims.translators;

import org.hl7.fhir.r4.model.Identifier;
import org.hl7.fhir.r4.model.Location;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.ServiceRequest;
import org.hl7.fhir.r4.model.Specimen;
import org.hl7.fhir.r4.model.Task;
import zw.gov.mohcc.mrs.fhir.lims.TaskAssociatedObjects;
import zw.gov.mohcc.mrs.fhir.lims.entities.AnalysisTemplate;
import zw.gov.mohcc.mrs.fhir.lims.entities.Client;
import zw.gov.mohcc.mrs.fhir.lims.entities.Sample;
import zw.gov.mohcc.mrs.fhir.lims.entities.SampleType;
import zw.gov.mohcc.mrs.fhir.lims.util.TaskBag;

public class TaskTranslator {

    //From Task(Fhir) to Sample or Analysis Request (Lims)
    public static Sample toSample(Task task) {

        TaskBag taskBag = TaskAssociatedObjects.getTaskAssociatedObjects(task);
        Location facility = taskBag.getFacility();
        Specimen specimen = taskBag.getSpecimen();
        Patient patient = taskBag.getPatient();
        ServiceRequest serviceRequest = taskBag.getServiceRequest();

        //Analysis Request
        Sample sample = new Sample();

        //Client Order Number
        sample.setClientOrderNumber(task.getIdElement().getIdPart());

        //Client Sample ID
        sample.setClientSampleId(specimen.getIdentifier().stream()
                .filter(i -> i.getSystem().equals("urn:impilo:uid"))
                .map(Identifier::getValue)
                .findFirst().orElse(null)
        );

        //Facility/Internal Client
        Client client = LocationClientTranslator.toClient(facility);
        sample.setClient(client); 

        //Patient
        zw.gov.mohcc.mrs.fhir.lims.entities.LimsPatient limsPatient = PatientTranslator.toLimsPatient(patient);
        sample.setPatient(limsPatient); //NB: You may want to save this Patient in your DB 

        //SampleTemplate or Test
        AnalysisTemplate sampleTemplate= ServiceRequestTranslator.toAnalysisTemplate(serviceRequest);
        sample.setSampleTemplate(sampleTemplate);
        
        //SampleType
        SampleType sampleType = SpecimenTranslator.toSampleType(specimen);
        sample.setSampleType(sampleType);

        return sample;

    }

}
