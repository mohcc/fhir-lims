package zw.gov.mohcc.mrs.fhir.lims.util;

import java.util.ArrayList;
import java.util.List;
import zw.gov.mohcc.mrs.fhir.lims.entities.Method;

public class MethodRepository {
    
    private static final List<Method> methods=new ArrayList<>();
    
    static{
       add("RNA-PCR","RNA PCR");
       add("RT-PCR","RT PCR");
       add("TMA","TMA");
    }
    
    private static void add(String code,String title){
        Method method=new Method();
        method.setCode(code);
        method.setTitle(title);
        methods.add(method);
    }
    
    public static List<Method> getMethods(){
        return methods;
    }
    
    
}
