package zw.gov.mohcc.mrs.fhir.lims.entities;

import java.time.LocalDate;
import lombok.Data;

@Data
public class LabAnalysis {

    private AnalysisService analysis;
    private Method method;
    private String resultValue;
    private LocalDate dueDate;
    private Instrument instrument;
    private LabContact analyst;
    private LabContact submitter;    
    private LabContact verifier; //Confirm this!
    private Boolean critical;
    private String interpretationText;
    

}
