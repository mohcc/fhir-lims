package zw.gov.mohcc.mrs.fhir.lims;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.base.composite.BaseIdentifierDt;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.client.interceptor.BasicAuthInterceptor;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Identifier;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.Task;
import zw.gov.mohcc.mrs.fhir.lims.util.IdentifierDt;

public class LimsPracSession {

    public static void main(String[] args) {

        //System.out.println(getArtNumber("e89b33d4-2908-4527-b3e6-6bd6579022fc"));
        //getTasksByClientIds(List.of("ZW090A17", "ZW030926"));
        //getTasksByLabCodes( List.of("ILB0003"));
        //getTasksByClientIdsAndLabCodes(List.of("ZW090A17", "ZW090A14"), List.of("ILB0003", "ILB0004"));
        //getTasksByTestCodes(List.of("ILT0056"));
        getTasks(List.of("ZW090A17"), List.of("ILB0045"), new Date(), new Date(), List.of("ILT0048"), Task.TaskStatus.REQUESTED);
    }

    public static void getTasksByLabCodes(List<String> labCodes) {
        List<BaseIdentifierDt> theIdentifiers = labCodes.stream()
                .map(labCode -> IdentifierDt.valueOf("urn:impilo:" + labCode + ":lab"))
                .collect(Collectors.toList());

        Bundle bundle = getShrFhirClient().search().forResource(Task.class)
                .where(Task.IDENTIFIER.exactly().identifiers(theIdentifiers))
                .returnBundle(Bundle.class)
                .execute();

        System.out.println(getShrFhirClient().getFhirContext().newJsonParser().setPrettyPrint(true).encodeResourceToString(bundle));

    }

    public static void getTasksByClientIds(List<String> clientIds) {
        List<BaseIdentifierDt> theIdentifiers = clientIds.stream()
                .map(clientId -> IdentifierDt.valueOf("urn:impilo:" + clientId + ":uid"))
                .collect(Collectors.toList());

        Bundle bundle = getShrFhirClient().search().forResource(Task.class)
                .where(Task.IDENTIFIER.exactly().identifiers(theIdentifiers))
                .returnBundle(Bundle.class)
                .execute();

        System.out.println(getShrFhirClient().getFhirContext().newJsonParser().setPrettyPrint(true).encodeResourceToString(bundle));

    }

    public static void getTasksByTestCodes(List<String> testCodes) {

        Bundle bundle = getShrFhirClient().search().forResource(Task.class)
                .where(Task.CODE.exactly().codes(testCodes.toArray(new String[0])))
                .returnBundle(Bundle.class)
                .execute();

        System.out.println(getShrFhirClient().getFhirContext().newJsonParser().setPrettyPrint(true).encodeResourceToString(bundle));

    }

    public static String getArtNumber(String patientId) {
        Patient patient = (Patient) getCrFhirClient().read().resource("Patient").withId(patientId).execute();
        if (patient.hasIdentifier()) {
            return patient.getIdentifier().stream().filter(i -> i.getSystem().equals("urn:impilo:art"))
                    .findFirst().map(Identifier::getValue).orElse(null);
        }

        return null;
    }

    public static void getTasksByClientIdsAndLabCodes(List<String> clientIds, List<String> labCodes) {

        List<BaseIdentifierDt> clientIdentifiers = clientIds.stream()
                .map(clientId -> IdentifierDt.valueOf("urn:impilo:" + clientId + ":uid"))
                .collect(Collectors.toList());

        List<BaseIdentifierDt> labIdentifiers = labCodes.stream()
                .map(labCode -> IdentifierDt.valueOf("urn:impilo:" + labCode + ":lab"))
                .collect(Collectors.toList());

        Bundle bundle = getShrFhirClient().search().forResource(Task.class)
                .where(Task.IDENTIFIER.exactly().identifiers(clientIdentifiers))
                .and(Task.GROUP_IDENTIFIER.exactly().identifiers(labIdentifiers))
                .returnBundle(Bundle.class)
                .execute();

        System.out.println(getShrFhirClient().getFhirContext().newJsonParser().setPrettyPrint(true).encodeResourceToString(bundle));

    }

    public static void getTasks(List<String> clientIds, List<String> labCodes, Date startDate, Date endDate, List<String> testCodes, Task.TaskStatus taskStatus) {

        List<BaseIdentifierDt> clientIdentifiers = clientIds.stream()
                .map(clientId -> IdentifierDt.valueOf("urn:impilo:" + clientId + ":uid"))
                .collect(Collectors.toList());

        List<BaseIdentifierDt> labIdentifiers = labCodes.stream()
                .map(labCode -> IdentifierDt.valueOf("urn:impilo:" + labCode + ":lab"))
                .collect(Collectors.toList());

        Bundle bundle = getShrFhirClient().search().forResource(Task.class)
                .where(Task.IDENTIFIER.exactly().identifiers(clientIdentifiers))
                .and(Task.GROUP_IDENTIFIER.exactly().identifiers(labIdentifiers))
                .and(Task.CODE.exactly().codes(testCodes.toArray(new String[0])))
                .and(Task.AUTHORED_ON.afterOrEquals().day(startDate))
                .and(Task.AUTHORED_ON.beforeOrEquals().day(endDate))
                .and(Task.STATUS.exactly().code(taskStatus.toCode()))
                .returnBundle(Bundle.class)
                .execute();

        System.out.println(getShrFhirClient().getFhirContext().newJsonParser().setPrettyPrint(true).encodeResourceToString(bundle));

    }

    public static IGenericClient getShrFhirClient() {
        FhirContext fhirContext = FhirContext.forR4();
        IGenericClient client = fhirContext.newRestfulGenericClient("https://core-openhim-zhie.mohcc.gov.zw/SHR/fhir/");
        BasicAuthInterceptor authInterceptor = new BasicAuthInterceptor("lims",
                "2vXB6dXMQqh@jJJK");
        client.registerInterceptor(authInterceptor);
        return client;
    }

    public static IGenericClient getCrFhirClient() {
        FhirContext fhirContext = FhirContext.forR4();
        IGenericClient client = fhirContext.newRestfulGenericClient("http://20.106.235.42:5001/CR/fhir/");
        BasicAuthInterceptor authInterceptor = new BasicAuthInterceptor("lims-service",
                "mylims");
        client.registerInterceptor(authInterceptor);
        return client;
    }

}
