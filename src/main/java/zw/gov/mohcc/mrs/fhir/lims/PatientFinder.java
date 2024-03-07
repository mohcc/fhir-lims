package zw.gov.mohcc.mrs.fhir.lims;

import ca.uhn.fhir.rest.client.api.IGenericClient;
import org.hl7.fhir.r4.model.Patient;

public class PatientFinder {
    
    
    public static Patient findPatient(String patientId){
        IGenericClient crFhirClient = ShrFhirClientUtility.getFhirClient();
        Patient patient = (Patient) crFhirClient.read().resource("Patient").withId(patientId).execute();
        return patient;

    }
    
}
