package zw.gov.mohcc.mrs.fhir.lims;

import ca.uhn.fhir.rest.client.api.IGenericClient;
import org.hl7.fhir.r4.model.Patient;

/**
 * 
 * A class to retrieve whole patient records from the client registry (CR)
 */
public class PatientFinder {

    //Get the entire patient record from the client registry
    public static Patient findPatient(String patientId) {
        IGenericClient crFhirClient = CrFhirClientUtility.getFhirClient();
        Patient patient = (Patient) crFhirClient.read().resource("Patient").withId(patientId).execute();
        return patient;

    }

}
