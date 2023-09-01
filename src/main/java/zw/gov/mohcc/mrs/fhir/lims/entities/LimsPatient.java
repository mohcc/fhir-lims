package zw.gov.mohcc.mrs.fhir.lims.entities;

import java.time.LocalDate;
import java.util.List;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class LimsPatient {
    
    private String patientId;
    
    //Default
    private String clientPatientId;
    private String firstname;
    private String surname;
    private String phoneMobile;
    private Boolean consentToSms;  
    private List<PatientIdentifier> additionalIdentifiers;
    private Gender gender;
    private boolean birthDateMissing;
    private boolean birthDateEstimated;
    private LocalDate birthDate;
    private Country country;
    private State state;
    private District district;
    private String remarks;
    
    
    //Insurance
    private InsuranceCompany insuranceCompany;
    private String insuranceNumber;
    private boolean sendInvoices;
    private boolean guarantor;
    
    //Personal
    private String emailAddress;
    private String phoneHome;
    private String birthPlace;
    private Ethnicity ethnicity;
    private String citizenship;
    private String mothersName;
    private String fathersName;
    private String civilStatus;
    
    //Address
    private LimsAddress physicalAddress;
    private LimsAddress postalAddress;
    
    //Publication preference
    private boolean inheritDefaultSettings;
    private boolean allowResultsDistribution;
    
    private Client primaryReferrer;
     
}
