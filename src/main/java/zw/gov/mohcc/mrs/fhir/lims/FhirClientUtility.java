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

    private static IGenericClient fhirClient;

    private FhirClientUtility() {
    }

    public static IGenericClient getFhirClient() {
        if (fhirClient == null) {
            fhirClient = createFhirClient();
        }
        return fhirClient;

    }

    private static IGenericClient createFhirClient() {
        FhirContext fhirContext = FhirContext.forR4();
        // Set how long to try and establish the initial TCP connection (in ms)
        fhirContext.getRestfulClientFactory().setConnectTimeout(5 * 60 * 1000);
        // Set how long to block for individual read/write operations (in ms)
        fhirContext.getRestfulClientFactory().setSocketTimeout(5 * 60 * 1000);
        IGenericClient client = fhirContext.newRestfulGenericClient(baseUrl);
        BasicAuthInterceptor authInterceptor = new BasicAuthInterceptor(username,
                password);
        client.registerInterceptor(authInterceptor);
        return client;
    }

}
