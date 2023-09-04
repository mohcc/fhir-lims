package zw.gov.mohcc.mrs.fhir.lims.mapping;

import java.util.HashMap;
import java.util.Map;
import zw.gov.mohcc.mrs.fhir.lims.entities.AnalysisTemplate;

public class AnalysisTemplateMapper {

    private static final Map<String, AnalysisTemplate> impiloMap = new HashMap<>();
    private static final Map<String, AnalysisTemplate> limsMap = new HashMap<>();

    static {
        createAnalysisTemplate("LTT0013", "HIV Viral Load PLASMA", "35ccdf6f-adee-11e7-b30f-3372a2d8551e");
    }
    
    public static AnalysisTemplate findByImpiloCode(String impiloCode){
        return impiloMap.get(impiloCode);
    }
    
    public static AnalysisTemplate findByLimsCode(String limsCode){
        return limsMap.get(limsCode);
    }

    private static void createAnalysisTemplate(String limsCode, String limsTitle, String impiloCode) {
        AnalysisTemplate analysisTemplate = new AnalysisTemplate();
        analysisTemplate.setCode(limsCode);
        analysisTemplate.setTitle(limsTitle);

        limsMap.put(limsCode, analysisTemplate);
        if (impiloCode != null) {
            impiloMap.put(impiloCode, analysisTemplate);
        }

    }
    
    

}
