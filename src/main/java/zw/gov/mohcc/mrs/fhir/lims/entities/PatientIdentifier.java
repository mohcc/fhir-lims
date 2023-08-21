package zw.gov.mohcc.mrs.fhir.lims.entities;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PatientIdentifier {
        
    private String identifierType;
    private String identifier;
    
    
}
