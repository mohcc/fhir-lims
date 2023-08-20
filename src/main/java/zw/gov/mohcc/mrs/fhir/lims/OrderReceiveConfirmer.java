package zw.gov.mohcc.mrs.fhir.lims;

import ca.uhn.fhir.rest.client.api.IGenericClient;
import org.hl7.fhir.r4.model.Task;

public class OrderReceiveConfirmer {
    
    private OrderReceiveConfirmer(){        
    }
    
    public static void confirmTaskReceived(Task task){
        IGenericClient client=FhirClientUtility.getFhirClient();
        task.setStatus(Task.TaskStatus.RECEIVED);
        client.update().resource(task).execute();        
    }
    
}
