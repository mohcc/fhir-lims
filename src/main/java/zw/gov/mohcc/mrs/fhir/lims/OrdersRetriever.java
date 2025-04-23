package zw.gov.mohcc.mrs.fhir.lims;

import ca.uhn.fhir.model.base.composite.BaseIdentifierDt;
import ca.uhn.fhir.rest.api.SortOrderEnum;
import ca.uhn.fhir.rest.api.SortSpec;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Task;
import zw.gov.mohcc.mrs.fhir.lims.util.IdentifierDt;

public class OrdersRetriever {

    private OrdersRetriever() {

    }

    //Get Lab Request Orders from Impilo Facilities or Sites in Fhir format
    public static List<Task> getRequestedTasks() {

        List<Task> tasks = new ArrayList<>();

        IGenericClient client = ShrFhirClientUtility.getFhirClient();

        Bundle bundle = client.search().forResource(Task.class)
                .where(Task.STATUS.exactly().code(Task.TaskStatus.REQUESTED.toCode()))
                .returnBundle(Bundle.class)
                .execute();
        
        
        
        for (Bundle.BundleEntryComponent entry : bundle.getEntry()) {
            if (entry.hasResource()) {
                switch (entry.getResource().getResourceType()) {
                    case Task:
                        Task task = (Task) entry.getResource();
                        tasks.add(task);
                        break;
                    default:
                        break;
                }
            }
        }

        return tasks;

    }
    
    

    //Get Lab Request Orders from Impilo Facilities or Sites in Fhir format
    public static List<Task> getRequestedTasks(Task.TaskStatus status, List<String> clientIds) {

        List<Task> tasks = new ArrayList<>();

        IGenericClient client = ShrFhirClientUtility.getFhirClient();

        List<BaseIdentifierDt> theIdentifiers = clientIds.stream().map(clientId -> IdentifierDt.valueOf("urn:impilo:" + clientId + ":uid"))
                .collect(Collectors.toList());

        Bundle bundle = client.search().forResource(Task.class)
                .where(Task.STATUS.exactly().code(status.toCode()))
                .where(Task.IDENTIFIER.exactly().identifiers(theIdentifiers))
                .returnBundle(Bundle.class)
                .execute();

        for (Bundle.BundleEntryComponent entry : bundle.getEntry()) {
            if (entry.hasResource()) {
                switch (entry.getResource().getResourceType()) {
                    case Task:
                        Task task = (Task) entry.getResource();
                        tasks.add(task);
                        break;
                    default:
                        break;
                }
            }
        }

        return tasks;

    }

    //Not for Lims (This is just for testing)
    public static List<Task> getAllTasks() {

        List<Task> tasks = new ArrayList<>();

        IGenericClient client = ShrFhirClientUtility.getFhirClient();

        Bundle bundle = client.search().forResource(Task.class)
                .returnBundle(Bundle.class)
                .execute();

        for (Bundle.BundleEntryComponent entry : bundle.getEntry()) {
            if (entry.hasResource()) {
                switch (entry.getResource().getResourceType()) {
                    case Task:
                        Task task = (Task) entry.getResource();
                        tasks.add(task);
                        break;
                    default:
                        break;
                }
            }
        }

        return tasks;

    }
    
    
    public static List<Task> getRequestedTasksLastUpdatedDesc(List<Task.TaskStatus> taskStatuses) {

        List<Task> tasks = new ArrayList<>();

        IGenericClient client = ShrFhirClientUtility.getFhirClient();

        Bundle bundle = client.search().forResource(Task.class)
                .where(Task.STATUS.exactly().codes(taskStatuses.stream().map(Task.TaskStatus::toCode).collect(Collectors.toList())))
                .sort(new SortSpec("authored-on", SortOrderEnum.DESC))
                .returnBundle(Bundle.class)
                .execute();

        for (Bundle.BundleEntryComponent entry : bundle.getEntry()) {
            if (entry.hasResource()) {
                switch (entry.getResource().getResourceType()) {
                    case Task:
                        Task task = (Task) entry.getResource();                        
                        tasks.add(task);
                        break;
                    default:
                        break;
                }
            }
        }

        return tasks;

    }

}
