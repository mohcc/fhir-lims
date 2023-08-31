package zw.gov.mohcc.mrs.fhir.lims.entities;

import lombok.Data;

//Example: 
//   Acceptable, Compromised, Compromised low volume, Compromised with contamination, Compromised with loss,  
//   Pending
@Data
public class SampleCondition {
    
    private String title;
    private String description;
    
}
