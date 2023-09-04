package zw.gov.mohcc.mrs.fhir.lims.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Method {
    
    private String code;
    private String title;
    
}
