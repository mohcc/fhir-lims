package zw.gov.mohcc.mrs.fhir.lims.entities;

import lombok.Data;
import lombok.ToString;

/**
 *
 * SampleTemplate / Test 
 */
@Data
@ToString
public class AnalysisTemplate {
    
    private String title;
    private String description;
    private SampleType sampleType;
    private SamplePoint samplePoint;
    
    //Custom
    private String code;
    
}
