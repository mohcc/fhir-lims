package zw.gov.mohcc.mrs.fhir.lims.translators;

import org.hl7.fhir.r4.model.BooleanType;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Task;
import zw.gov.mohcc.mrs.fhir.lims.entities.ExtraDetails;

public class TaskExtraDetailsTranslator {

    public static ExtraDetails toSample(Task task) {
        ExtraDetails extraDetails = new ExtraDetails();

        if (task.hasInput()) {
            for (Task.ParameterComponent paramComponent : task.getInput()) {
                CodeableConcept type = paramComponent.getType();
                for (Coding coding : type.getCoding()) {
                    String code = coding.getCode();
                    if (code.equals("isPregnant")) {
                        boolean isPregnant = ((BooleanType) paramComponent.getValue()).booleanValue();
                        extraDetails.setPregnancy(isPregnant);

                    } else if (code.equals("isBreastFeeding")) {
                        boolean isBreastFeeding = ((BooleanType) paramComponent.getValue()).booleanValue();
                        extraDetails.setPregnancy(isBreastFeeding);
                    }
                }

            }

        }

        return extraDetails;
    }

}
