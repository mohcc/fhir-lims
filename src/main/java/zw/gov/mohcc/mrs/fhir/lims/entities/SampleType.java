package zw.gov.mohcc.mrs.fhir.lims.entities;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SampleType {
    
    private String title;
    private String prefix;
    private boolean hazardous;
    private String minimumVolum;
    
    //Custom
    private String code;
    
}
