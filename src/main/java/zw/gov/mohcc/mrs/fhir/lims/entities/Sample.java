package zw.gov.mohcc.mrs.fhir.lims.entities;

import java.time.LocalDateTime;
import lombok.Data;
import lombok.ToString;

/**
 *  
 * Analysis Request
 * 
 */
@Data
@ToString
public class Sample {
    
    private AnalysisTemplate sampleTemplate;
    private SampleType sampleType;
    private Patient patient;
    private Client client;
    private String clientOrderNumber;
    private String clientSampleId;
    private LocalDateTime dateReceived;
    private LocalDateTime dateReceivedAtHub;
    private LocalDateTime dateSampled;
    
    
    
}
