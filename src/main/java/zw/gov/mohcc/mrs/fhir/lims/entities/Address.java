package zw.gov.mohcc.mrs.fhir.lims.entities;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Address {
    
    private Country country;
    private State state;
    private District district;    
    private String city;
    private String postalCode;
    private String address;
    
}
