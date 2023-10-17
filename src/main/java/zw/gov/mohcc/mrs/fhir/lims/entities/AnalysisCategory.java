package zw.gov.mohcc.mrs.fhir.lims.entities;

import lombok.Data;

//Example:: Viral Load. This a Test in Impilo EHR
@Data
public class AnalysisCategory {

    private String title;
    private String description;
    private LabDepartment department; //Lab Department
    private Float sortKey;

}
