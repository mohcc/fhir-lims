package zw.gov.mohcc.mrs.fhir.lims;

import ca.uhn.fhir.rest.client.api.IGenericClient;
import java.util.List;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Resource;

public class FhirResourcesSaver {
    
    private FhirResourcesSaver(){
        
    }
    
    //Save FHIR Resources (Task, Observation, Diagnostic Report etc) in the Shared Health Record (SHR):: OpenHIE
    public static void saveFhirResources(List<Resource> resources) {

        IGenericClient client = FhirClientUtility.getFhirClient();

        Bundle transactionBundle = new Bundle();
        transactionBundle.setType(Bundle.BundleType.TRANSACTION);
        
        for (Resource resource : resources) {
            Bundle.BundleEntryComponent component = transactionBundle.addEntry();
            component.setResource(resource);
            component.getRequest().setUrl(getRequestUrl(resource))
                    .setMethod(Bundle.HTTPVerb.PUT);
        }
        
        client.transaction().withBundle(transactionBundle).execute();
    }
    
    
    private static String getRequestUrl(Resource resource) {
        return resource.fhirType() + "/" + resource.getIdElement().getIdPart();
    }
    
}
