package zw.gov.mohcc.mrs.fhir.lims;

import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Identifier;
import org.hl7.fhir.r4.model.Location;
import org.hl7.fhir.r4.model.Organization;
import org.hl7.fhir.r4.model.ServiceRequest;
import org.hl7.fhir.r4.model.Specimen;

public class ImpiloCodeFinder {

    public static String getImpiloLaboratoryCode(Location location) {
        String impiloLaboratoryCode = location.getIdentifier().stream()
                .filter(i -> i.getSystem().equals("urn:impilo:code"))
                .map(Identifier::getValue)
                .findFirst().orElse(null);
        return impiloLaboratoryCode;
    }

    public static String getImpiloClientCode(Location location) {
        String impiloClientCode = location.getIdentifier().stream()
                .filter(i -> i.getSystem().equals("urn:impilo:uid"))
                .map(Identifier::getValue)
                .findFirst().orElse(null);
        return impiloClientCode;
    }

    public static String getImpiloClientCode(Organization organization) {
        String impiloClientCode = organization.getIdentifier().stream()
                .filter(i -> i.getSystem().equals("urn:impilo:uid"))
                .map(Identifier::getValue)
                .findFirst().orElse(null);
        return impiloClientCode;
    }

    public static String getImpiloTestReasonCode(ServiceRequest serviceRequest) {
        String impiloTestReasonCode = serviceRequest.getReasonCode().get(0).getCoding().stream()
                .filter(i -> i.getSystem().equals("urn:impilo:code"))
                .map(Coding::getCode)
                .findFirst().orElse(null);
        return impiloTestReasonCode;
    }
    
    public static String getImpiloTestCode(ServiceRequest serviceRequest) {
        String impiloTestCode = serviceRequest.getCode().getCoding().stream()
                .filter(i -> i.getSystem().equals("urn:impilo:code"))
                .map(Coding::getCode)
                .findFirst().orElse(null);
        return impiloTestCode;
    }
    
    public static String getImpiloSampleTypeCode(Specimen specimen) {
        String impiloSampleTypeCode = specimen.getType().getCoding().stream()
                .filter(i -> i.getSystem().equals("urn:impilo:code"))
                .map(Coding::getCode)
                .findFirst().orElse(null);
        return impiloSampleTypeCode;
    }

}
