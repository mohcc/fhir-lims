package zw.gov.mohcc.mrs.fhir.lims.translators;

import java.util.Date;
import org.hl7.fhir.r4.model.DateType;
import org.hl7.fhir.r4.model.Identifier;
import org.hl7.fhir.r4.model.Location;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.ServiceRequest;
import org.hl7.fhir.r4.model.Specimen;
import org.hl7.fhir.r4.model.Task;
import zw.gov.mohcc.mrs.fhir.lims.TaskAssociatedObjects;
import zw.gov.mohcc.mrs.fhir.lims.entities.AnalysisTemplate;
import zw.gov.mohcc.mrs.fhir.lims.entities.Client;
import zw.gov.mohcc.mrs.fhir.lims.entities.LimsPatient;
import zw.gov.mohcc.mrs.fhir.lims.entities.Sample;
import zw.gov.mohcc.mrs.fhir.lims.entities.SampleType;
import zw.gov.mohcc.mrs.fhir.lims.util.DateTimeUtils;
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
                .filter(i -> i.getSystem().equals("urn:impilo:cid"))
                .map(Identifier::getValue)
                .findFirst().orElse(null)
        );

        Date dateCollected = getDateCollected(specimen);
        if (dateCollected != null) {
            sample.setDateSampled(DateTimeUtils.convertToLocalDateTime(dateCollected));
        }

        //Facility/Internal Client
        Client client = LocationClientTranslator.toClient(facility);
        sample.setClient(client);

        //Patient
        LimsPatient limsPatient = PatientTranslator.toLimsPatient(patient);
        sample.setPatient(limsPatient); //NB: You may want to save this Patient in your DB 

        //SampleTemplate or Test
        AnalysisTemplate sampleTemplate = ServiceRequestTranslator.toAnalysisTemplate(serviceRequest);
        sample.setSampleTemplate(sampleTemplate);

        //SampleType
        SampleType sampleType = SpecimenTranslator.toSampleType(specimen);
        sample.setSampleType(sampleType);

        return sample;

    }

    private static Date getDateCollected(Specimen specimen) {
        Specimen.SpecimenCollectionComponent collection = specimen.getCollection();
        if (collection != null && collection.getCollected() instanceof DateType) {
            DateType dateTypeCollected = (DateType) collection.getCollected();
            if (dateTypeCollected != null) {
                return dateTypeCollected.getValue();
            }
        }
        return null;
    }

}
