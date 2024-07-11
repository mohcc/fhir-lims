package zw.gov.mohcc.mrs.fhir.lims;

import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.gclient.TokenClientParam;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Encounter;
import org.hl7.fhir.r4.model.Location;
import org.hl7.fhir.r4.model.Organization;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.ServiceRequest;
import org.hl7.fhir.r4.model.Specimen;
import org.hl7.fhir.r4.model.Task;
import zw.gov.mohcc.mrs.fhir.lims.util.TaskBag;

public class TaskAssociatedObjects {

    //A Task is Lab Order
    public static TaskBag getTaskAssociatedObjects(Task task) {
        Organization organization = null; //Patient's Managing Organization(Primary Referrer)
        Patient patient = null;
        Location laboratory = null;
        Location facility = null;//Client::=> The assigned client of this request
        Encounter encounter = null;
        Specimen specimen = null; //Sample
        ServiceRequest serviceRequest = null; //Test

        IGenericClient shrFhirClient = ShrFhirClientUtility.getFhirClient();

        String taskId = task.getIdElement().getIdPart();

        Bundle bundle = shrFhirClient.search().forResource(Task.class)
                .where(new TokenClientParam("_id").exactly().code(taskId))
                .include(Task.INCLUDE_ALL)
                .returnBundle(Bundle.class).execute();

        for (Bundle.BundleEntryComponent entry : bundle.getEntry()) {
            if (entry.hasResource()) {
                switch (entry.getResource().getResourceType()) {
                    case Task:
                        task = (Task) entry.getResource();
                        break;
                    case Encounter:
                        encounter = (Encounter) entry.getResource();
                        break;
                    case ServiceRequest:
                        serviceRequest = (ServiceRequest) entry.getResource();
                        break;
                    case Patient:
                        //This is a shallow patient record from the SHR, without demographics (eg. name, address, contacts etc)
                        patient = (Patient) entry.getResource();
                        break;
                    default:
                        break;
                }

            }
        }

        //Get Laboratory
        if (task != null) {

            String taskLaboratoryId = task.getLocation().getReferenceElement().getIdPart();

            bundle = shrFhirClient.search().forResource(Location.class)
                    .where(new TokenClientParam("_id").exactly().code(taskLaboratoryId))
                    .include(Location.INCLUDE_ALL)
                    .returnBundle(Bundle.class).execute();

            for (Bundle.BundleEntryComponent entry : bundle.getEntry()) {
                if (entry.hasResource()) {
                    switch (entry.getResource().getResourceType()) {
                        case Location:
                            laboratory = (Location) entry.getResource();
                            break;
                        default:
                            break;
                    }

                }
            }
        }

        //Get Patient's Managing Organization (Patient's Primary Referrer)
        if (patient != null && patient.hasManagingOrganization()) {

            String patientManagingOrganizationId = patient.getManagingOrganization().getReferenceElement().getIdPart();

            bundle = shrFhirClient.search().forResource(Organization.class)
                    .where(new TokenClientParam("_id").exactly().code(patientManagingOrganizationId))
                    .include(Organization.INCLUDE_ALL)
                    .returnBundle(Bundle.class).execute();

            for (Bundle.BundleEntryComponent entry : bundle.getEntry()) {
                if (entry.hasResource()) {
                    switch (entry.getResource().getResourceType()) {
                        case Organization:
                            organization = (Organization) entry.getResource();
                            break;
                        default:
                            break;
                    }

                }
            }
        }

        ///NB: LIMS team 
        if (patient != null) {
            String patientId = patient.getIdElement().getIdPart();
            System.out.println("Patient ID="+patientId);
            try {
                //Retrieve the entire patient from the client registry (CR)
                Patient crPatient = PatientFinder.findPatient(patientId);
                if (crPatient != null) {
                    patient = crPatient;
                }else{
                    System.out.println("Patient from CR is NULL");
                }
            } catch (Exception ex) {
                System.out.println("*******Failed to get patient from CR**********");
            }
        }

        //Get Specimen (Sample)
        if (serviceRequest != null) {

            String serviceRequestId = serviceRequest.getIdElement().getIdPart();

            bundle = shrFhirClient.search().forResource(ServiceRequest.class)
                    .where(new TokenClientParam("_id").exactly().code(serviceRequestId))
                    .include(ServiceRequest.INCLUDE_ALL)
                    .returnBundle(Bundle.class).execute();

            for (Bundle.BundleEntryComponent entry : bundle.getEntry()) {
                if (entry.hasResource()) {
                    switch (entry.getResource().getResourceType()) {
                        case Specimen:
                            specimen = (Specimen) entry.getResource();
                            break;
                        default:
                            break;
                    }

                }
            }
        }

        //Get Facility (Order/Sample Client)
        if (encounter != null) {
            String encounterId = encounter.getIdElement().getIdPart();

            bundle = shrFhirClient.search().forResource(Encounter.class)
                    .where(new TokenClientParam("_id").exactly().code(encounterId))
                    .include(Encounter.INCLUDE_ALL)
                    .returnBundle(Bundle.class).execute();

            for (Bundle.BundleEntryComponent entry : bundle.getEntry()) {
                if (entry.hasResource()) {
                    switch (entry.getResource().getResourceType()) {
                        case Location:
                            facility = (Location) entry.getResource();
                            break;
                        default:
                            break;
                    }

                }
            }
        }

        return new TaskBag(task, patient, laboratory, facility, encounter, specimen, serviceRequest, organization);

    }

}
