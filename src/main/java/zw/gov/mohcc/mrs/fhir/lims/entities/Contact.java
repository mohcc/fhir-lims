package zw.gov.mohcc.mrs.fhir.lims.entities;



import java.util.List;
import lombok.Data;

//Lab Contact
@Data
public class Contact {
    
    private String salutation;
    private String firstname;
    private String middleInitial;
    private String surname;
    private List<Department> departments;
    private Department defaultDepartment;
    
    private String emailAddress;
    private String phoneBusiness;
    private String fax;
    private String phoneHome;
    private String phoneMobile;
    
    private boolean consentToSms;
    
}
