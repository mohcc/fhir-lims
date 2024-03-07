package zw.gov.mohcc.mrs.fhir.lims;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.client.interceptor.BasicAuthInterceptor;
import java.util.List;
import java.util.stream.Collectors;
import org.hl7.fhir.r4.model.Patient;

public class ClientRegistry {

    public static void main(String[] args) {
        FhirContext fhirContext = FhirContext.forR4();
        IGenericClient client = fhirContext.newRestfulGenericClient("http://localhost:5001/CR/fhir/");
        BasicAuthInterceptor authInterceptor = new BasicAuthInterceptor("test", "test1234");
        client.registerInterceptor(authInterceptor);

        String patientId = "7788749e-da45-11ee-ad9f-6747eb4196ad";

        Patient patient = (Patient) client.read().resource("Patient").withId(patientId).execute();

        String goldenTagCode = "5c827da5-4858-4f3d-a50c-62ece001efea";

        boolean isGoldenRecord = patient.getMeta().getTag().stream().anyMatch(t -> t.getCode().equals(goldenTagCode));

        if (isGoldenRecord) {

            List<String> patientIds = patient.getLink().stream().map(l -> l.getOther().getReferenceElement().getIdPart())
                    .collect(Collectors.toList());

            System.out.println("Linked patientids=" + patientIds);
            //Use f
        }else{
             String goldenRecordId = patient.getLink().stream().map(l -> l.getOther().getReferenceElement().getIdPart())
                    .findFirst().orElse(null);

            System.out.println("Golder record=" + goldenRecordId);
        }

        String string = client.getFhirContext().forR4().newJsonParser()
                .setPrettyPrint(true)
                .encodeResourceToString(patient);

        System.out.println(string);

    }
    
    
    

}
