package zw.gov.mohcc.mrs.fhir.lims.util;

import java.util.ArrayList;
import java.util.List;
import zw.gov.mohcc.mrs.fhir.lims.entities.AnalysisService;

public class AnalysisServiceRepository {

    private static final List<AnalysisService> analysisServices = new ArrayList<>();

    static {
        //Viral Load
        add("VLDBS", "HIV VIRAL LOAD (DBS)", "copies/ml");
        add("VLPLASMA", "HIV Viral Load (PLASMA)", null);
        //TB Sample Analysis
        add("BAP","BAP", null);
        add("GROWTHCONTROL", "Growth Control", null);
        add("SmearMicroscopy", "Smear Microscopy", null);
        add("TBCULTURE", "TB Culture", null);
        add("TBLAM", "TB LAM TEST", null);
        add("GeneXpert", "TB on GeneXpert", null);
        
    }

    private static void add(String analysisKeyword, String title, String unit) {
        AnalysisService analysisService = new AnalysisService();
        analysisService.setTitle(title);
        analysisService.setAnalysisKeyword(analysisKeyword);
        analysisService.setUnit(unit);
        analysisService.setCode(analysisKeyword);
        analysisServices.add(analysisService);
    }

    public static List<AnalysisService> getAnalysisServices() {
        return analysisServices;
    }

}
