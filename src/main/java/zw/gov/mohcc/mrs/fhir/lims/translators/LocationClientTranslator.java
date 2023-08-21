package zw.gov.mohcc.mrs.fhir.lims.translators;

import org.hl7.fhir.r4.model.Location;
import zw.gov.mohcc.mrs.fhir.lims.entities.Client;

public class LocationClientTranslator {

    public static Client toClient(Location location) {
        Client limsClient = new Client();
        
        return limsClient;
    }

}
