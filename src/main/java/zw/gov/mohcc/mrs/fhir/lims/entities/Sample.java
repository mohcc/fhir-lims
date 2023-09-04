package zw.gov.mohcc.mrs.fhir.lims.entities;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 *  
 * Analysis Request
 * 
 */
@Data
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Sample {
    
    @EqualsAndHashCode.Include
    private String clientOrderNumber;
    private AnalysisTemplate sampleTemplate;
    private SampleType sampleType;
    private LimsPatient patient;
    private Client client;   
    private String clientSampleId;
    private LocalDateTime dateReceived;
    private LocalDateTime dateReceivedAtHub;
    private LocalDateTime dateSampled;
    private LocalDateTime datePublished;    
    private String state;
    private LabContact contact;
    private SampleCondition sampleCondition;   
    
    private List<LabAnalysis> labAnalyses;
    
    //Custom (for pregnancy, breastfeeding etc)
    private ExtraDetails extraDetails;
    private String status;
    

   
    
    
}
