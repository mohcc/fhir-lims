package zw.gov.mohcc.mrs.fhir.lims.util;

import java.util.ArrayList;
import java.util.List;
import zw.gov.mohcc.mrs.fhir.lims.entities.AnalysisTemplate;

public class AnalysisTemplateRepository {

    private static final List<AnalysisTemplate> analysisTemplates = new ArrayList<>();
    
    static{
        add("LTT001","Bacteriology - Smear");
        add("LTT002","Microscopy");
        add("LTT003","Biochemical Tests");
        add("LTT004","COVID19");
        add("LTT005","Culture (Bacteriology)");
        add("LTT006","GeneXpert");
        add("LTT007","LPA");
        add("LTT008","Routine Histological");
        add("LTT009","Examination");
        add("LTT0010","TB Culture");
        add("LTT0012","Viral Load DBS");
        add("LTT0013","Viral Load Plasma");
    }
    
    private static void add(String code,String title){
        AnalysisTemplate analysisTemplate=new AnalysisTemplate();
        analysisTemplate.setCode(code);
        analysisTemplate.setTitle(title);
        analysisTemplates.add(analysisTemplate);
    }
    
    public static List<AnalysisTemplate> getAnalysisTemplates(){
        return analysisTemplates;
    }

}
