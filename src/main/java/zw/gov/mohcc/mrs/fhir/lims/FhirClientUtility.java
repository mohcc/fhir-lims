package zw.gov.mohcc.mrs.fhir.lims;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.client.interceptor.BasicAuthInterceptor;

public class FhirClientUtility {

    //Note:: The last forward slash is required for the baseUrl
    //In reality this should be autowired
    public static String baseUrl = "http://localhost:8090/fhir/";
    public static String username = "limsUsername";
    public static String password = "limsPassword";

    private FhirClientUtility() {
    }

    public static IGenericClient getFhirClient() {
        FhirContext fhirContext = FhirContext.forR4();
        IGenericClient fhirClient = fhirContext.newRestfulGenericClient(baseUrl);
        BasicAuthInterceptor authInterceptor = new BasicAuthInterceptor(username,
                password);
        fhirClient.registerInterceptor(authInterceptor);
        return fhirClient;
    }

}
