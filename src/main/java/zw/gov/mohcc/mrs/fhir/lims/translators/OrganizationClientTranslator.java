package zw.gov.mohcc.mrs.fhir.lims.translators;

import org.hl7.fhir.r4.model.Organization;
import zw.gov.mohcc.mrs.fhir.lims.entities.Client;

public class OrganizationClientTranslator {

    public static Client toClient(Organization organization) {
        Client limsClient = new Client();
        //You can this clientId to look up for an Internal Client in your DB
        String clientId = organization.getIdElement().getIdPart();        
        limsClient.setClientId(clientId);
        limsClient.setName(organization.getName());
        return limsClient;
    }

}
