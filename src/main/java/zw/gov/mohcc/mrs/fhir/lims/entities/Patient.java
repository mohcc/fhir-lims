package zw.gov.mohcc.mrs.fhir.lims.entities;

import java.util.List;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Patient {
    
    private String clientPatientId;
    private String firstname;
    private String surname;
    private String phoneMobile;
    private List<PatientIdentifier> additionalIdentifiers;
    
     
}
