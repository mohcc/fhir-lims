package zw.gov.mohcc.mrs.fhir.lims.entities;

import java.time.LocalDateTime;
import lombok.Data;

/**
 *  
 * Analysis Request
 * 
 */
@Data
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
    private LocalDateTime datePublished;    
    private String state;
    private Contact contact;
    
    
    
}
