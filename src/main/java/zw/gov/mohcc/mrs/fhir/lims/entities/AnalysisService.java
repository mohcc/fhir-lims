package zw.gov.mohcc.mrs.fhir.lims.entities;

import java.util.List;
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
    private LabDepartment department;// Laboratory Department e.g Viral Load
    private String analysisKeyword;
    
    //Method section
    private Boolean instrumentAssignmentNoRequired;
    private List<Method> methods; //E.g RNA PCR, RDT, TMA
    private Boolean instrumentAssignmentAllowed;
    private List<Instrument> instruments;//E.g ABBORT, Hologic Pather, GeneXpert, BIO-RAD CFX96
    private Instrument defaultInstrument; //E.g Hologic Pather
    private Method defaultMethod; //E.g TMA
    private Boolean useDefaultCalculationOfMethod;
    private Calculation calculation;//E.g Viral Load
    private Boolean accredited;
    private Double duplicationVariation;
    
    
    
    
    
    //Custom
    private String uid;
    private String code;
    
}
