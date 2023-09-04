package zw.gov.mohcc.mrs.fhir.lims.translators;

import java.util.Date;
import org.apache.commons.text.WordUtils;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.Identifier;
import org.hl7.fhir.r4.model.Location;
import org.hl7.fhir.r4.model.Organization;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.Period;
import org.hl7.fhir.r4.model.ServiceRequest;
import org.hl7.fhir.r4.model.Specimen;
import org.hl7.fhir.r4.model.Task;
import org.hl7.fhir.r4.model.Type;
import zw.gov.mohcc.mrs.fhir.lims.TaskAssociatedObjects;
import zw.gov.mohcc.mrs.fhir.lims.entities.AnalysisTemplate;
import zw.gov.mohcc.mrs.fhir.lims.entities.Client;
import zw.gov.mohcc.mrs.fhir.lims.entities.LimsPatient;
import zw.gov.mohcc.mrs.fhir.lims.entities.Sample;
import zw.gov.mohcc.mrs.fhir.lims.entities.SampleType;
import zw.gov.mohcc.mrs.fhir.lims.util.DateTimeUtils;
import zw.gov.mohcc.mrs.fhir.lims.util.TaskBag;

public class TaskSampleTranslator {

    //From Task(Fhir) to Sample or Analysis Request (Lims)
    public static Sample toSample(Task task) {

        TaskBag taskBag = TaskAssociatedObjects.getTaskAssociatedObjects(task);
        Location facility = taskBag.getFacility();
        Specimen specimen = taskBag.getSpecimen();
        Patient patient = taskBag.getPatient();
        ServiceRequest serviceRequest = taskBag.getServiceRequest();
        Organization organization=taskBag.getOrganization();

        //Analysis Request
        Sample sample = new Sample();

        //Client Order Number
        String clientOrderNumber = task.getIdElement().getIdPart();
        sample.setClientOrderNumber(clientOrderNumber);

        //Client Sample ID
        String clientSampleId=
                specimen.getIdentifier().stream()
                .filter(i -> i.getSystem().equals("urn:impilo:cid"))
                .map(Identifier::getValue)
                .findFirst().orElse(null);
        sample.setClientSampleId(clientSampleId);

        Date dateCollected = getDateCollected(specimen);
        if (dateCollected != null) {
            sample.setDateSampled(DateTimeUtils.convertToLocalDateTime(dateCollected));
        }

        //Facility/Internal Client
        Client client = LocationClientTranslator.toClient(facility);
        sample.setClient(client);

        //Patient
        LimsPatient limsPatient = PatientTranslator.toLimsPatient(patient);
        if(organization!=null){
            Client primaryReferrer=OrganizationClientTranslator.toClient(organization);
            limsPatient.setPrimaryReferrer(primaryReferrer);
        }
        sample.setPatient(limsPatient); //NB: You may want to save this Patient in your DB 

        //SampleTemplate or Test
        AnalysisTemplate sampleTemplate = ServiceRequestTranslator.toAnalysisTemplate(serviceRequest);
        sample.setSampleTemplate(sampleTemplate);

        //SampleType
        SampleType sampleType = SpecimenTranslator.toSampleType(specimen);
        sample.setSampleType(sampleType);
        
        //Status
        sample.setStatus(WordUtils.capitalizeFully(task.getStatus().name()));

        return sample;

    }

    private static Date getDateCollected(Specimen specimen) {
        if (specimen.hasCollection() && specimen.getCollection().hasCollected()) {
            Type collected = specimen.getCollection().getCollected();
            if (collected instanceof DateTimeType) {
                DateTimeType dateTimeTypeCollected = (DateTimeType) collected;
                return dateTimeTypeCollected.getValue();
            } else if (collected instanceof Period) {
                Period periodCollected = (Period) collected;
                return periodCollected.getEnd();
            }
        }
        return null;
    }

}
