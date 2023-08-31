package zw.gov.mohcc.mrs.fhir.lims.entities;

import lombok.Data;


//Examples: HIV VIRAL LOAD (DBS), HIV Viral Load (PLASMA)
@Data
public class AnalysisService {
    
    private String title;
    private String description;
    private String shortTitle;
    private String commercialId;
    private String protocolId;
    private String unit;
    private AnalysisCategory analysisCategory; //E.g Viral Load
    private LabDepartment department;// Laboratory Department
    
}
