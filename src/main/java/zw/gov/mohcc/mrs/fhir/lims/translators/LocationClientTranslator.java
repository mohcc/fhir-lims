package zw.gov.mohcc.mrs.fhir.lims.translators;

import org.hl7.fhir.r4.model.Location;
import zw.gov.mohcc.mrs.fhir.lims.entities.Client;

public class LocationClientTranslator {

    public static Client toClient(Location location) {
        Client limsClient = new Client();
        //You can this clientId to look up for an Internal Client in your DB
        String clientId = location.getIdElement().getIdPart();        
        limsClient.setClientId(clientId);
        limsClient.setName(location.getName());
        return limsClient;
    }

}
