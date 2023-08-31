package zw.gov.mohcc.mrs.fhir.lims.entities;

import lombok.Data;

//Example: Hologic Pather
@Data
public class Instrument {

    private String title;
    private InstrumentType instrumentType; //E.g Analyzer

}
