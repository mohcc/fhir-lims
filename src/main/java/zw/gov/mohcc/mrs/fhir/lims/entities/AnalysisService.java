package zw.gov.mohcc.mrs.fhir.lims.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


//Examples: HIV VIRAL LOAD (DBS), HIV Viral Load (PLASMA)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnalysisService {
    
    private String title;
    private String description;
    private String shortTitle;
    private String commercialId;
    private String protocolId;
    private String unit;
    private AnalysisCategory analysisCategory; //E.g Viral Load
    private LabDepartment department;// Laboratory Department
    private String analysisKeyword;
    
    //Custom
    private String uid;
    private String code;
    
}
