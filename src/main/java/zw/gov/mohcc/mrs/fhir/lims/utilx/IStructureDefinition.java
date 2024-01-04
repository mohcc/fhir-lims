package zw.gov.mohcc.mrs.fhir.lims.utilx;

import java.util.List;

public interface IStructureDefinition {

    String getUrl();

    String getType();

    IElementDefinition getRootDefinition();

    List<IElementDefinition> getSnapshotDefinitions();

}
