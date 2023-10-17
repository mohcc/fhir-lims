package zw.gov.mohcc.mrs.fhir.lims.util;

import ca.uhn.fhir.model.api.IElement;
import ca.uhn.fhir.model.base.composite.BaseIdentifierDt;
import ca.uhn.fhir.model.primitive.StringDt;
import ca.uhn.fhir.model.primitive.UriDt;
import java.util.List;


public class IdentifierDt extends BaseIdentifierDt{
    
    
    private String system;
    private String value;

    public IdentifierDt() {
    }

    public IdentifierDt(String system, String value) {
        this.system = system;
        this.value = value;
    }
    
    public IdentifierDt(String system) {
        this.system = system;
    }
    
    
    
    @Override
    public UriDt getSystemElement() {
       return new UriDt(system);
    }

    @Override
    public StringDt getValueElement() {
        return new StringDt(value);
    }

    @Override
    public BaseIdentifierDt setSystem(String system) {
        this.system=system;
        return this;
    }

    @Override
    public BaseIdentifierDt setValue(String value) {
        this.value=value;
        return this;
    }

    @Override
    public boolean isEmpty() {
       return (this.value==null || this.value.isEmpty()) && (this.system==null || this.system.isEmpty());
    }

    @Override
    public <T extends IElement> List<T> getAllPopulatedChildElementsOfType(Class<T> theType) {
         throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public static IdentifierDt valueOf(String system,String value){
        return new IdentifierDt(system, value);
    }
    
    public static IdentifierDt valueOf(String system){
        return new IdentifierDt(system);
    }
    
}
