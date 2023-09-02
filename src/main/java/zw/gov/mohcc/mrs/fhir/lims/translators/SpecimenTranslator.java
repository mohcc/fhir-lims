package zw.gov.mohcc.mrs.fhir.lims.translators;

import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Specimen;
import zw.gov.mohcc.mrs.fhir.lims.entities.SampleType;
import zw.gov.mohcc.mrs.fhir.lims.mapping.SampleTypeMapper;

public class SpecimenTranslator {

    public static SampleType toSampleType(Specimen specimen) {
        SampleType sampleType = null;
        CodeableConcept type = specimen.getType();
        if (type.hasCoding()) {
            for (Coding coding : type.getCoding()) {
                if (coding.getSystem().equals("urn:lims:code")) {
                    String theLimsCode = coding.getCode();
                    sampleType = SampleTypeMapper.findByLimsCode(theLimsCode);
                    //This if is for demonstration only. 
                    if (sampleType == null) {
                        sampleType = new SampleType();
                        sampleType.setCode(theLimsCode);
                        sampleType.setTitle(coding.getDisplay());
                    }
                } else if (coding.getSystem().equals("urn:impilo:code") && sampleType == null) {
                    String theImpiloCode = coding.getCode();
                    sampleType = SampleTypeMapper.findByImpiloCode(theImpiloCode);
                    //This if is for demonstration only. 
                    if (sampleType == null) {
                        sampleType = new SampleType();
                        sampleType.setTitle(coding.getDisplay());
                    }
                }
            }

            //This if is for demonstration only. 
            if (sampleType == null) {
                sampleType = new SampleType();
                Coding coding = type.getCodingFirstRep();
                sampleType.setTitle(coding.getDisplay());
            }
        }

        return sampleType;
    }

}
