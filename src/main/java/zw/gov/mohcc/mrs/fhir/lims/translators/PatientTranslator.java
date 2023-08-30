package zw.gov.mohcc.mrs.fhir.lims.translators;

import static ca.uhn.fhir.model.api.TemporalPrecisionEnum.DAY;
import static ca.uhn.fhir.model.api.TemporalPrecisionEnum.MONTH;
import static ca.uhn.fhir.model.api.TemporalPrecisionEnum.YEAR;
import org.hl7.fhir.r4.model.Address;
import static org.hl7.fhir.r4.model.Address.AddressType.BOTH;
import static org.hl7.fhir.r4.model.Address.AddressType.NULL;
import static org.hl7.fhir.r4.model.Address.AddressType.PHYSICAL;
import static org.hl7.fhir.r4.model.Address.AddressType.POSTAL;
import org.hl7.fhir.r4.model.ContactPoint;
import static org.hl7.fhir.r4.model.ContactPoint.ContactPointUse.HOME;
import static org.hl7.fhir.r4.model.ContactPoint.ContactPointUse.MOBILE;
import static org.hl7.fhir.r4.model.ContactPoint.ContactPointUse.NULL;
import static org.hl7.fhir.r4.model.ContactPoint.ContactPointUse.TEMP;
import static org.hl7.fhir.r4.model.ContactPoint.ContactPointUse.WORK;
import org.hl7.fhir.r4.model.DateType;
import org.hl7.fhir.r4.model.HumanName;
import org.hl7.fhir.r4.model.Patient;
import zw.gov.mohcc.mrs.fhir.lims.entities.LimsAddress;
import zw.gov.mohcc.mrs.fhir.lims.entities.LimsPatient;
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

        return limsPatient;

    }
    
    private static void setPhones(LimsPatient limsPatient, Patient fhirPatient){
        //Phone
        if (!fhirPatient.getTelecom().isEmpty()) {
            for (ContactPoint contactPoint : fhirPatient.getTelecom()) {
                if (null == contactPoint.getUse()) {
                    limsPatient.setPhoneMobile(contactPoint.getValue());
                } else {
                    switch (contactPoint.getUse()) {
                        case HOME:
                            limsPatient.setPhoneHome(contactPoint.getValue());
                            break;
                        case MOBILE:
                            limsPatient.setPhoneMobile(contactPoint.getValue());
                            break;
                        case WORK:
                            limsPatient.setPhoneHome(contactPoint.getValue());
                            break;
                        case NULL:
                            limsPatient.setPhoneHome(contactPoint.getValue());
                            break;
                        case TEMP:
                            limsPatient.setPhoneHome(contactPoint.getValue());
                            break;
                        default:
                            break;
                    }
                }
            }
        }
    }
    
    private static void setAddresses(LimsPatient limsPatient, Patient fhirPatient){
        if (!fhirPatient.getAddress().isEmpty()) {
            for (Address address : fhirPatient.getAddress()) {
                String city = address.getCity();
                LimsAddress limsAddress = new LimsAddress();
                limsAddress.setCity(city);
                if (null == address.getType()) {
                    limsPatient.setPhysicalAddress(limsAddress);
                } else switch (address.getType()) {
                    case PHYSICAL:
                        limsPatient.setPhysicalAddress(limsAddress);
                        break;
                    case POSTAL:
                        limsPatient.setPostalAddress(limsAddress);
                        break;
                    case BOTH:
                        limsPatient.setPostalAddress(limsAddress);
                        limsPatient.setPhysicalAddress(limsAddress);
                        break;
                    case NULL:
                        limsPatient.setPhysicalAddress(limsAddress);
                        break;
                    default:
                        break;
                }

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
}
