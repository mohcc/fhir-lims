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
        for (int i = 0; i < type.getCoding().size() && sampleType == null; i++) {
            Coding coding = type.getCoding().get(i);
            if (coding.getSystem().equals("urn:lims:code")) {
                String theLimsCode = coding.getCode();
                sampleType = SampleTypeMapper.findByLimsCode(theLimsCode);
            } else if (coding.getSystem().equals("urn:impilo:code")) {
                String theImpiloCode = coding.getCode();
                sampleType = SampleTypeMapper.findByImpiloCode(theImpiloCode);
            }
        }

        return sampleType;
    }

}
