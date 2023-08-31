package zw.gov.mohcc.mrs.fhir.lims.entities;

import lombok.Data;

//Example:: Viral Load
@Data
public class AnalysisCategory {

    private String title;
    private String description;
    private LabDepartment department; //Lab Department
    private Float sortKey;

}
