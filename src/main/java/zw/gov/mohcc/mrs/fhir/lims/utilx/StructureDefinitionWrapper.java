package zw.gov.mohcc.mrs.fhir.lims.utilx;

import java.util.List;
import java.util.stream.Collectors;
import org.hl7.fhir.r4.model.StructureDefinition;

public class StructureDefinitionWrapper implements IStructureDefinition{
    
    private final StructureDefinition structureDefinition;

    public StructureDefinitionWrapper(StructureDefinition structureDefinition) {
      this.structureDefinition = structureDefinition;
    }

    @Override
    public String getUrl() {
      return structureDefinition.getUrl();
    }

    @Override
    public String getType() {
      return structureDefinition.getType();
    }

    @Override
    public IElementDefinition getRootDefinition() {
      return new ElementDefinitionWrapper(structureDefinition.getSnapshot().getElementFirstRep());
    }

    @Override
    public List<IElementDefinition> getSnapshotDefinitions() {
      return structureDefinition.getSnapshot().getElement().stream()
          .map(d -> new ElementDefinitionWrapper(d))
          .collect(Collectors.toList());
    }
    
}
