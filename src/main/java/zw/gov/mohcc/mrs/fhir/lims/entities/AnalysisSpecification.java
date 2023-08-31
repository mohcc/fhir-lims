package zw.gov.mohcc.mrs.fhir.lims.entities;

import java.util.List;
import lombok.Data;

//Example: Blood Plasma Analysis (Panic) 
@Data
public class AnalysisSpecification {

    private SampleType sampleType; // E.g Blood Plasma
    private String title; //E.g Blood Plasma Analysis (Panic)
    private String description;
    
    private List<Specification> specifications;

    @Data
    public static class Specification {

        private AnalysisService service;
        private Double minWarn;
        private String minOperator; // >= or >
        private Double min;
        private String maxOperator; // <= or <
        private Double max;
        private Double maxWarn;
        private Double lessThanMin;
        private Double greaterThanMax;

    }

}
