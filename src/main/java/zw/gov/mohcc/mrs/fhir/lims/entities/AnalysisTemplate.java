package zw.gov.mohcc.mrs.fhir.lims.entities;

import java.util.List;
import lombok.Data;
import lombok.ToString;

/**
 *
 * SampleTemplate. E.g HIV Viral Load PLASMA.
 * This is equivalent to an Investigation in Impilo EHR
 */
@Data
@ToString
public class AnalysisTemplate {
    
    private String title;
    private String description;
    private SampleType sampleType; //E.g Blood plasma
    private SamplePoint samplePoint;
    private List<AnalysisTemplateService> templateServices;
    
    //Custom
    private String code;
    
}
