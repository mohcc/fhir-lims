package zw.gov.mohcc.mrs.fhir.lims.utilx;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.hl7.fhir.r4.model.CanonicalType;
import org.hl7.fhir.r4.model.ElementDefinition;
import org.hl7.fhir.r4.model.ElementDefinition.TypeRefComponent;
import org.hl7.fhir.r4.model.PrimitiveType;

public class ElementDefinitionWrapper implements IElementDefinition {

    private final ElementDefinition elementDefinition;

    public ElementDefinitionWrapper(ElementDefinition elementDefinition) {
        this.elementDefinition = elementDefinition;
    }

    @Override
    public String getId() {
        return elementDefinition.getId();
    }

    @Override
    public String getPath() {
        return elementDefinition.getPath();
    }

    @Override
    public String getContentReference() {
        return elementDefinition.getContentReference();
    }

    @Override
    public String getMax() {
        return elementDefinition.getMax();
    }

    @Override
    public String getFirstTypeCode() {
        return elementDefinition.getTypeFirstRep().getCode();
    }

    @Override
    public boolean hasSingleType() {
        return (elementDefinition.getType().size() == 1);
    }

    @Override
    public List<String> getAllTypeCodes() {
        return elementDefinition.getType().stream()
                .map(TypeRefComponent::getCode)
                .collect(Collectors.toList());
    }

    @Override
    public String getSliceName() {
        return elementDefinition.getSliceName();
    }

    @Override
    public boolean getIsModifier() {
        return elementDefinition.getIsModifier();
    }

    @Override
    public String getFixedPrimitiveValue() {
        if (elementDefinition.getFixed() == null) {
            return null;
        }
        return elementDefinition.getFixed().primitiveValue();
    }

    @Override
    public List<String> getReferenceTargetProfiles() {
        return elementDefinition.getType().stream()
                .filter(type -> "Reference".equals(type.getCode()))
                .map(TypeRefComponent::getTargetProfile)
                .filter(Objects::nonNull)
                // Note there is a difference between how `Reference` types are represented in R4 vs STU3,
                // in R4 all "target profiles" (i.e., reference's target type) are under the same `type`
                // while in STU3 we have multiple `type` each with a single target-profile.
                .flatMap(Collection::stream)
                .map(PrimitiveType::getValue)
                .collect(Collectors.toList());
    }

    @Override
    public String getFirstTypeProfile() {
        List<CanonicalType> profiles = elementDefinition.getTypeFirstRep().getProfile();
        if (profiles == null || profiles.isEmpty()) {
            return null;
        }
        return profiles.get(0).getValue();
    }

    @Override
    public String toString() {
        return elementDefinition.toString();
    }
}
