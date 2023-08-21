package zw.gov.mohcc.mrs.fhir.lims.entities;

//Facility or Internal Client

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Client {
    
    private String name;
    private String clientId;
    private String vatNumber;
    private String phone;
    private String fax;
    private String emailAddress;
    
    
}
