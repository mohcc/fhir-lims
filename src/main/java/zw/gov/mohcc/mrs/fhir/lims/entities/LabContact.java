package zw.gov.mohcc.mrs.fhir.lims.entities;



import java.util.List;
import lombok.Data;

//Lab Contact
@Data
public class LabContact {    
      
    private String salutation;
    private String firstname;
    private String middleInitial;
    private String surname;
    private List<LabDepartment> departments;
    private LabDepartment defaultDepartment;
    
    private String emailAddress;
    private String phoneBusiness;
    private String fax;
    private String phoneHome;
    private String phoneMobile;
    
    private boolean consentToSms;
    
    //Address
    private LimsAddress physicalAddress;
    private LimsAddress postalAddress;
    
    //Internal
    private String labContactId; 
    private String username;
    private List<String> userGroups;
    
}
