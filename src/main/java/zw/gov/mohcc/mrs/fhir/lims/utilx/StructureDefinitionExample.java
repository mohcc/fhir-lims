package zw.gov.mohcc.mrs.fhir.lims.utilx;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.context.FhirVersionEnum;
import ca.uhn.fhir.context.support.IValidationSupport;
import java.util.List;
import org.hl7.fhir.common.hapi.validation.support.PrePopulatedValidationSupport;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.r4.model.StructureDefinition;

public class StructureDefinitionExample {

    public static void main(String[] args) {

        FhirContext context = new FhirContext(FhirVersionEnum.R4);

        boolean addCustomSupport = false;

        if (addCustomSupport) {
            IValidationSupport defaultSupport = context.getValidationSupport();
            PrePopulatedValidationSupport support = new PrePopulatedValidationSupport(context);
            List<IBaseResource> defaultDefinitions = defaultSupport.fetchAllStructureDefinitions();
            for (IBaseResource definition : defaultDefinitions) {
                support.addStructureDefinition(definition);
                System.out.println("Definition ::" + definition);
            }

            context.setValidationSupport(support);
        }

        StructureDefinition structureDefinition
                = (StructureDefinition) context.getValidationSupport().fetchStructureDefinition("Task");

       StructureDefinitionWrapper structureDefinitionWrapper=new StructureDefinitionWrapper(structureDefinition);
       
       structureDefinitionWrapper.getSnapshotDefinitions().forEach(elementDefinition->{
           System.out.println(elementDefinition.getId());
       });
       
       
        
        

    }

}
