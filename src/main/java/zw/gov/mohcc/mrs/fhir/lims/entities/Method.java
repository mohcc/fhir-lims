package zw.gov.mohcc.mrs.fhir.lims.entities;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Method {

    private String title;
    private String methodId;
    private String instructions;
    private List<Instrument> instruments;
    private boolean manualEntryOfResults;
    private boolean accredited;

    //Custom
    private String code;

}
