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

        for (int i = 0; i < code.getCoding().size() && analysisTemplate == null; i++) {
            Coding coding = code.getCoding().get(i);
            if (coding.getSystem().equals("urn:lims:code")) {
                String theLimsCode = coding.getCode();
                analysisTemplate = AnalysisTemplateMapper.findByLimsCode(theLimsCode);
            } else if (coding.getSystem().equals("urn:impilo:code")) {
                String theImpiloCode = coding.getCode();
                analysisTemplate = AnalysisTemplateMapper.findByImpiloCode(theImpiloCode);
            }
        }

        return analysisTemplate;
    }

}
