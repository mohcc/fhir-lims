package zw.gov.mohcc.mrs.fhir.lims.translators;

import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.ServiceRequest;
import zw.gov.mohcc.mrs.fhir.lims.entities.AnalysisTemplate;
import zw.gov.mohcc.mrs.fhir.lims.mapping.AnalysisTemplateMapper;

public class ServiceRequestTranslator {

    //To SampleTemplate
    public static AnalysisTemplate toAnalysisTemplate(ServiceRequest serviceRequest) {
        AnalysisTemplate analysisTemplate = null;

        CodeableConcept code = serviceRequest.getCode();
        if (code.hasCoding()) {

            for (Coding coding : code.getCoding()) {
                if (coding.getSystem().equals("urn:lims:code")) {
                    String theLimsCode = coding.getCode();
                    analysisTemplate = AnalysisTemplateMapper.findByLimsCode(theLimsCode);
                    //This if is for demonstration only. 
                    if (analysisTemplate == null) {
                        analysisTemplate = new AnalysisTemplate();
                        analysisTemplate.setCode(theLimsCode);
                        analysisTemplate.setTitle(coding.getDisplay());
                    }
                } else if (coding.getSystem().equals("urn:impilo:code") && analysisTemplate == null) {
                    String theImpiloCode = coding.getCode();
                    analysisTemplate = AnalysisTemplateMapper.findByImpiloCode(theImpiloCode);
                    //This if is for demonstration only. 
                    if (analysisTemplate == null) {
                        analysisTemplate = new AnalysisTemplate();
                        analysisTemplate.setTitle(coding.getDisplay());
                    }
                }
            }

            //This if is for demonstration only. 
            if (analysisTemplate == null) {
                analysisTemplate = new AnalysisTemplate();
                Coding coding = code.getCodingFirstRep();
                analysisTemplate.setTitle(coding.getDisplay());
            }
        }
        return analysisTemplate;
    }

}
