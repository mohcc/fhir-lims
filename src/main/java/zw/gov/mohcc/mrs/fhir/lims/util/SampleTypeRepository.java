package zw.gov.mohcc.mrs.fhir.lims.util;

import java.util.ArrayList;
import java.util.List;
import zw.gov.mohcc.mrs.fhir.lims.entities.SampleType;

public class SampleTypeRepository {

    private static final List<SampleType> sampleTypes = new ArrayList<>();

    static {
        add("LST006", "Blood plasma");
        add("LST0014", "Blood serum");
        add("LST005", "Bocal swab");
        add("LST004", "Buffy coat");
        add("LST0013", "Nasopharyngeal/Oropharyngeal swab");
        add("LST007", "Dried Blood Spot");
        add("LST003", "Oropharyngeal swab");
        add("LST0018", "Red blood cells");
        add("LST0010", "Saliva");
        add("LST008", "Semen");
        add("LST0016", "Whole blood");
        add("LST001", "Fluid");
        add("LST002", "Aspirate");

    }

    private static void add(String code, String title) {
        SampleType sampleType = new SampleType();
        sampleType.setCode(code);
        sampleType.setTitle(title);
        sampleTypes.add(sampleType);
    }

    public static List<SampleType> getSampleTypes() {
        return sampleTypes;
    }

}
