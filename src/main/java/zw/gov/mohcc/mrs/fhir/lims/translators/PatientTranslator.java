package zw.gov.mohcc.mrs.fhir.lims.translators;

import static ca.uhn.fhir.model.api.TemporalPrecisionEnum.DAY;
import static ca.uhn.fhir.model.api.TemporalPrecisionEnum.MONTH;
import static ca.uhn.fhir.model.api.TemporalPrecisionEnum.YEAR;
import java.util.ArrayList;
import java.util.List;
import org.hl7.fhir.r4.model.Address;
import static org.hl7.fhir.r4.model.Address.AddressType.BOTH;
import static org.hl7.fhir.r4.model.Address.AddressType.NULL;
import static org.hl7.fhir.r4.model.Address.AddressType.PHYSICAL;
import static org.hl7.fhir.r4.model.Address.AddressType.POSTAL;
import org.hl7.fhir.r4.model.BooleanType;
import org.hl7.fhir.r4.model.ContactPoint;
import static org.hl7.fhir.r4.model.ContactPoint.ContactPointUse.HOME;
import static org.hl7.fhir.r4.model.ContactPoint.ContactPointUse.MOBILE;
import static org.hl7.fhir.r4.model.ContactPoint.ContactPointUse.NULL;
import static org.hl7.fhir.r4.model.ContactPoint.ContactPointUse.TEMP;
import static org.hl7.fhir.r4.model.ContactPoint.ContactPointUse.WORK;
import org.hl7.fhir.r4.model.DateType;
import org.hl7.fhir.r4.model.Enumerations;
import static org.hl7.fhir.r4.model.Enumerations.AdministrativeGender.FEMALE;
import static org.hl7.fhir.r4.model.Enumerations.AdministrativeGender.MALE;
import org.hl7.fhir.r4.model.Extension;
import org.hl7.fhir.r4.model.HumanName;
import org.hl7.fhir.r4.model.Identifier;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.StringType;
import zw.gov.mohcc.mrs.fhir.lims.entities.LimsAddress;
import zw.gov.mohcc.mrs.fhir.lims.entities.LimsGender;
import zw.gov.mohcc.mrs.fhir.lims.entities.LimsPatient;
import zw.gov.mohcc.mrs.fhir.lims.entities.PatientIdentifier;
import zw.gov.mohcc.mrs.fhir.lims.util.DateTimeUtils;

public class PatientTranslator {
    
    public static LimsPatient toLimsPatient(Patient fhirPatient) {
        
        LimsPatient limsPatient = new LimsPatient();
        
        String clientPatientId = fhirPatient.getIdElement().getIdPart();
        
        limsPatient.setClientPatientId(clientPatientId);

        //Name
        if (fhirPatient.hasName()) {
            HumanName humanName = fhirPatient.getName().get(0);
            if (humanName.hasGiven()) {
                limsPatient.setFirstname(humanName.getGiven().get(0).getValue());
            }
            
            limsPatient.setSurname(humanName.getFamily());
        }

        //Birth Date
        limsPatient.setBirthDateMissing(fhirPatient.getBirthDateElement() == null);
        
        if (fhirPatient.getBirthDateElement() != null) {
            limsPatient.setBirthDate(DateTimeUtils.convertToLocalDate(fhirPatient.getBirthDate()));
            limsPatient.setBirthDateEstimated(isEstimated(fhirPatient.getBirthDateElement()));
        }
        
       
        //Phones
        setPhones(limsPatient, fhirPatient);

        //Address
        setAddresses(limsPatient, fhirPatient);

        //Additional Identifiers
        setAdditionalIdentifiers(limsPatient, fhirPatient);
        
        //Consent to sms
        limsPatient.setConsentToSms(getConsentToSms(fhirPatient));
        
        //Gender
        setGender(limsPatient, fhirPatient);
        
        return limsPatient;
        
    }
    
    private static void setAdditionalIdentifiers(LimsPatient limsPatient, Patient fhirPatient) {
        //Additional Identifiers
        List<PatientIdentifier> additionalIdentifiers = new ArrayList<>();
        limsPatient.setAdditionalIdentifiers(additionalIdentifiers);
        
        String artNumber = fhirPatient.getIdentifier().stream()
                .filter(i -> i.getSystem().equals("urn:impilo:art"))
                .map(Identifier::getValue)
                .findFirst().orElse(null);
        
        if (artNumber != null) {
            PatientIdentifier artNumberIdentifier = new PatientIdentifier();
            artNumberIdentifier.setIdentifierType("ART_NUMBER");
            artNumberIdentifier.setIdentifier(artNumber);
            additionalIdentifiers.add(artNumberIdentifier);
        }
        
        String cohortNo = fhirPatient.getExtension().stream()
                .filter(e -> e.getUrl().equals("urn:impilo:cohort"))
                .map(Extension::getValue)
                .filter(t -> t instanceof StringType)
                .map(t -> (StringType) t)
                .map(StringType::getValue)
                .findFirst().orElse(null);
        
        if (cohortNo != null) {
            PatientIdentifier cohortNumberIdentifier = new PatientIdentifier();
            cohortNumberIdentifier.setIdentifierType("COHORT");
            cohortNumberIdentifier.setIdentifier(cohortNo);
            additionalIdentifiers.add(cohortNumberIdentifier);
        }
    }
    
    private static void setPhones(LimsPatient limsPatient, Patient fhirPatient) {
        //Phone
        if (!fhirPatient.getTelecom().isEmpty()) {
            for (ContactPoint contactPoint : fhirPatient.getTelecom()) {
                
                if (contactPoint.getUse() == null && limsPatient.getPhoneMobile() == null) {
                    limsPatient.setPhoneMobile(contactPoint.getValue());
                }
                
                if (contactPoint.getUse() != null) {
                    switch (contactPoint.getUse()) {
                        case HOME:
                            limsPatient.setPhoneHome(contactPoint.getValue());
                            break;
                        case MOBILE:
                            limsPatient.setPhoneMobile(contactPoint.getValue());
                            break;
                        case WORK:
                        case NULL:
                        case TEMP:
                        default:
                            break;
                    }
                }
            }
        }
    }
    
    private static void setAddresses(LimsPatient limsPatient, Patient fhirPatient) {
        if (!fhirPatient.getAddress().isEmpty()) {
            for (Address address : fhirPatient.getAddress()) {
                String city = address.getCity();
                LimsAddress limsAddress = new LimsAddress();
                limsAddress.setCity(city);
                
                if (address.getType() == null && limsPatient.getPhysicalAddress() == null) {
                    limsPatient.setPhysicalAddress(limsAddress);
                }
                
                if (address.getType() != null) {
                    switch (address.getType()) {
                        case PHYSICAL:
                            limsPatient.setPhysicalAddress(limsAddress);
                            break;
                        case POSTAL:
                            limsPatient.setPostalAddress(limsAddress);
                            break;
                        case BOTH:
                            if (limsPatient.getPostalAddress() == null) {
                                limsPatient.setPostalAddress(limsAddress);
                            }
                            
                            if (limsPatient.getPhysicalAddress() == null) {
                                limsPatient.setPhysicalAddress(limsAddress);
                            }
                            break;
                        case NULL:
                        default:
                            break;
                    }
                }
                
            }
            
        }
    }
    
    private static void setGender(LimsPatient limsPatient, Patient fhirPatient){
        //Gender
        if(fhirPatient.getGender()!=null){
            if(null==fhirPatient.getGender()){
                limsPatient.setGender(LimsGender.DONT_KNOW);
            }else switch (fhirPatient.getGender()) {
                case FEMALE:
                    limsPatient.setGender(LimsGender.FEMALE);
                    break;
                case MALE:
                    limsPatient.setGender(LimsGender.MALE);
                    break;
                default:
                    limsPatient.setGender(LimsGender.DONT_KNOW);
                    break;
            }
        
        }
    }
    
    private static boolean isEstimated(DateType date) {
        switch (date.getPrecision()) {
            case DAY:
                return false;
            case MONTH:
            case YEAR:
                return true;
        }
        return false;
    }
    
    private static Boolean getConsentToSms(Patient fhirPatient){
        for(Extension extension:fhirPatient.getExtension()){
            if(extension.getUrl().equals("urn:consent:sms")){
                BooleanType type=(BooleanType)extension.getValue();
                return type.booleanValue();
            }            
        }
        return null;
    }
}
