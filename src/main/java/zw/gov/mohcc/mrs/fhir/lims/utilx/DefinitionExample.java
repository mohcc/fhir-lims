package zw.gov.mohcc.mrs.fhir.lims.utilx;

import ca.uhn.fhir.context.BaseRuntimeChildDefinition;
import ca.uhn.fhir.context.BaseRuntimeElementCompositeDefinition;
import ca.uhn.fhir.context.BaseRuntimeElementDefinition;
import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.context.RuntimeResourceDefinition;
import org.hl7.fhir.r4.model.UuidType;

public class DefinitionExample {

    public static void main1(String[] args) {
        RuntimeResourceDefinition patientDefinition = FhirContext.forR4().getResourceDefinition("Patient");
        for (BaseRuntimeChildDefinition brc : patientDefinition.getChildren()) {
            System.out.println(brc.getElementName());
        }

    }

    public static void main2(String[] args) {
        RuntimeResourceDefinition taskDefinition = FhirContext.forR4().getResourceDefinition("Task");

        for (BaseRuntimeChildDefinition brc : taskDefinition.getChildren()) {
            System.out.println(brc.getElementName());
            System.out.println(brc.getValidChildNames());
            for (String name : brc.getValidChildNames()) {
                BaseRuntimeElementDefinition bre = brc.getChildByName(name);
                if (bre != null) {
                    System.out.println(bre.getName());
                    if (!(bre instanceof BaseRuntimeElementCompositeDefinition)) {
                        System.out.println("Not composite");
                    } else {
                        System.out.println("Composite");
                    }
                }
            }

            System.out.println("===========================");
        }

    }

    public static void main(String[] args) {
        printResource("Task");

    }

    private static void printResource(String resource) {
        RuntimeResourceDefinition taskDefinition = FhirContext.forR4().getResourceDefinition(resource);
        
        

        for (BaseRuntimeChildDefinition brc : taskDefinition.getChildren()) {

            System.out.println(brc.getElementName());
            
           

            BaseRuntimeElementDefinition bre = brc.getChildByName(brc.getElementName());

            if (bre != null) {
                System.out.println(bre.getName());
                if (!(bre instanceof BaseRuntimeElementCompositeDefinition)) {
                    System.out.println("Not composite");
                } else {
                    System.out.println("Composite");
                }
                
                /*
                System.out.println(bre.getChildren());
                for (Object brc1 : bre.getChildren()) {
                    System.out.println(((BaseRuntimeChildDefinition) brc1).getValidChildNames());
                    
                }*/

            }

            System.out.println("===========================");
        }
    }


}
