package zw.gov.mohcc.mrs.fhir.lims;

import java.util.Collections;
import java.util.List;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Extension;
import org.hl7.fhir.r4.model.Task;
import zw.gov.mohcc.mrs.fhir.lims.entities.RejectionReason;

public class OrderRejecter {

    public static final String STATUS_REASONS_EXTENSION_URL = "urn:status:reasons";

    private OrderRejecter() {

    }

    public static void rejectOrder(Task task, List<RejectionReason> rejectionReasons) {

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
