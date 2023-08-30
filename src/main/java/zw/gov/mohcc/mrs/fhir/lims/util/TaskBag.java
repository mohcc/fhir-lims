package zw.gov.mohcc.mrs.fhir.lims.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hl7.fhir.r4.model.Encounter;
import org.hl7.fhir.r4.model.Location;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.ServiceRequest;
import org.hl7.fhir.r4.model.Specimen;
import org.hl7.fhir.r4.model.Task;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskBag {

    Task task;
    Patient patient;
    Location laboratory;
    Location facility;
    Encounter encounter;
    Specimen specimen; //Sample
    ServiceRequest serviceRequest; //Test
}
