package zw.gov.mohcc.mrs.fhir.lims.mapping;

import java.util.HashMap;
import java.util.Map;
import zw.gov.mohcc.mrs.fhir.lims.entities.SampleType;

public class SampleTypeMapper {

    private static final Map<String, SampleType> impiloMap = new HashMap<>();
    private static final Map<String, SampleType> limsMap = new HashMap<>();

    static {
        createSampleType("LST006", "Blood plasma", "IST0002");
        createSampleType("LST002", "Aspirate", "IST0004");
    }

    public static SampleType findByImpiloCode(String impiloCode) {
        return impiloMap.get(impiloCode);
    }

    public static SampleType findByLimsCode(String limsCode) {
        return limsMap.get(limsCode);
    }

    private static void createSampleType(String limsCode, String limsTitle, String impiloCode) {
        SampleType sampleType = new SampleType();
        sampleType.setCode(limsCode);
        sampleType.setTitle(limsTitle);

        limsMap.put(limsCode, sampleType);
        if (impiloCode != null) {
            impiloMap.put(impiloCode, sampleType);
        }

    }

}
