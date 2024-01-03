package zw.gov.mohcc.mrs.fhir.lims;

import java.util.Collection;
import java.util.Collections;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Extension;
import org.hl7.fhir.r4.model.Task;
import zw.gov.mohcc.mrs.fhir.lims.entities.RejectionReason;
import zw.gov.mohcc.mrs.fhir.lims.entities.Sample;

public class OrderRejecter {

    public static final String STATUS_REASONS_EXTENSION_URL = "urn:status:reasons";

    private OrderRejecter() {

    }

    public static void rejectOrder(Task task, Collection<RejectionReason> rejectionReasons) {

        task.setStatus(Task.TaskStatus.REJECTED);
        
        Extension statusReasonsExtension = new Extension(STATUS_REASONS_EXTENSION_URL);

        for (RejectionReason rejectionReason : rejectionReasons) {
            Extension statusReasonExtension = getStatusReasonExtension(rejectionReason);
            statusReasonsExtension.addExtension(statusReasonExtension);
        }

        task.addExtension(statusReasonsExtension);

        //Save this Task/Order in the Shared Health Record (SHR):: OpenHIE
        FhirResourcesSaver.saveFhirResources(Collections.singletonList(task));

    }

    public static void rejectOrder(String taskId, Collection<RejectionReason> rejectionReasons) {

        Task task = OrderFinder.findTaskById(taskId);
        if (task == null) {
            System.out.println("Task(" + taskId + ") not found");
            throw new RuntimeException("Task(" + taskId + ") not found");
        }

        rejectOrder(task, rejectionReasons);

    }

    public static void rejectOrder(Sample sample, Collection<RejectionReason> rejectionReasons) {
        String clientOrderNumber = sample.getClientOrderNumber();
        rejectOrder(clientOrderNumber, rejectionReasons);
    }

    private static Extension getStatusReasonExtension(RejectionReason rejectionReason) {
        CodeableConcept statusReasonConcept = new CodeableConcept();
        Coding limsStatusReasonCode = new Coding();
        limsStatusReasonCode.setSystem("urn:lims:code");
        limsStatusReasonCode.setCode(rejectionReason.getCode());
        limsStatusReasonCode.setDisplay(rejectionReason.getName());
        statusReasonConcept.addCoding(limsStatusReasonCode);

        Extension statusReasonExtension = new Extension();
        statusReasonExtension.setUrl(STATUS_REASONS_EXTENSION_URL + "#" + rejectionReason.getCode());
        statusReasonExtension.setValue(statusReasonConcept);

        return statusReasonExtension;
    }

}
