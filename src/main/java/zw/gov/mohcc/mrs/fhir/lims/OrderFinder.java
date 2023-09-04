package zw.gov.mohcc.mrs.fhir.lims;

import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.gclient.TokenClientParam;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Task;

public class OrderFinder {
    
    private OrderFinder(){
        
    }
    
    public static Task findTaskById(String taskId){
        Task task=null;
        IGenericClient fhirClient = FhirClientUtility.getFhirClient();
        Bundle bundle = fhirClient.search().forResource(Task.class)
                .where(new TokenClientParam("_id").exactly().code(taskId))
                .returnBundle(Bundle.class).execute();

        for (Bundle.BundleEntryComponent entry : bundle.getEntry()) {
            if (entry.hasResource()) {
                switch (entry.getResource().getResourceType()) {
                    case Task:
                        task = (Task) entry.getResource();
                        break;
                    default:
                        break;
                }

            }
        }
        
        return task;
    }
    
}
