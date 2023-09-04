package zw.gov.mohcc.mrs.fhir.lims;

import java.util.Collections;
import org.hl7.fhir.r4.model.Task;
import zw.gov.mohcc.mrs.fhir.lims.entities.Sample;

//Order receipt confirmer
public class OrderReceiveConfirmer {

    private OrderReceiveConfirmer() {
    }

    /**
     * Note:: you need to confirm the receipt of this electronic Task(Order) as
     * soon as you save it in your local (lims) database
     *
     * @param task*
     */
    public static void confirmTaskReceived(Task task) {

        task.setStatus(Task.TaskStatus.RECEIVED);

        //Save this Task/Order in the Shared Health Record (SHR):: OpenHIE
        FhirResourcesSaver.saveFhirResources(Collections.singletonList(task));
    }

    public static void confirmTaskReceived(String taskId) {
        Task task = OrderFinder.findTaskById(taskId);
        if (task == null) {
            System.out.println("Task(" + taskId + ") not found");
            throw new RuntimeException("Task(" + taskId + ") not found");
        }
        confirmTaskReceived(task);
    }

    public static void confirmTaskReceived(Sample sample) {
        String clientOrderNumber = sample.getClientOrderNumber();
        confirmTaskReceived(clientOrderNumber);
    }

}
