package zw.gov.mohcc.mrs.fhir.lims.entities;

import java.time.LocalDate;
import lombok.Data;

@Data
public class LabAnalysis {

    private AnalysisService analysis;
    private Method method;
    private Double resultValue;
    private LocalDate dueDate;
    private Instrument instrument;
    private LabContact analyst;
    private LabContact submitter;    
    private LabContact verifier; //Confirm this!

}
