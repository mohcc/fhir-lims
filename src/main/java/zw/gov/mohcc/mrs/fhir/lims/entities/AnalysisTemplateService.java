package zw.gov.mohcc.mrs.fhir.lims.entities;

import lombok.Data;

@Data
public class AnalysisTemplateService {
    
    private AnalysisService service; //E.g HIV Viral Load (PLASMA)
    private String partition;
    private Boolean hidden;
    
    
}
