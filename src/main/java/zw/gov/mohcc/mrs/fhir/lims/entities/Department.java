package zw.gov.mohcc.mrs.fhir.lims.entities;

import lombok.Data;
import lombok.ToString;

//Lab Department
@Data
@ToString
public class Department {

    private String title;
    private String description;

}
