/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package zw.gov.mohcc.mrs.fhir.lims.translators;

import org.hl7.fhir.r4.model.HumanName;
import org.hl7.fhir.r4.model.Identifier;
import org.hl7.fhir.r4.model.Patient;

/**
 *
 * @author charles
 */
public class PatientTranslator {

    public static zw.gov.mohcc.mrs.fhir.lims.entities.Patient toLimsPatient(Patient fhirPatient) {

        zw.gov.mohcc.mrs.fhir.lims.entities.Patient limsPatient = new 
        zw.gov.mohcc.mrs.fhir.lims.entities.Patient();

        String clientPatientId = fhirPatient.getIdentifier().stream()
                .filter(i -> i.hasSystem() && i.getSystem().equals("urn:impilo:uid"))
                .map(Identifier::getValue)
                .findFirst().orElse(null);

        limsPatient.setClientPatientId(clientPatientId);

        if (fhirPatient.hasName()) {
            HumanName humanName = fhirPatient.getName().get(0);
            if (humanName.hasGiven()) {
                limsPatient.setFirstname(humanName.getGiven().get(0).getValue());
            }
                       
            limsPatient.setSurname(humanName.getFamily());
            
        }

        return limsPatient;

    }
}
