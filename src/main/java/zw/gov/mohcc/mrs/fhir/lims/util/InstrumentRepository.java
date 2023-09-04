package zw.gov.mohcc.mrs.fhir.lims.util;

import java.util.ArrayList;
import java.util.List;
import zw.gov.mohcc.mrs.fhir.lims.entities.Instrument;

public class InstrumentRepository {
    
    private static final List<Instrument> instruments=new ArrayList<>();
    
    static{
        add("INSTR1", "ABBOTT");
        add("INSTR2", "BIO-RAD CFX96");
        add("INSTR3", "Direct Smear Microscopy");
        add("INSTR4", "GeneXpert");
        add("INSTR5", "Hologic Pather");
        add("INSTR6", "Sysmex-KX21-N");
        add("INSTR7", "USTAR");
    }
    
    
    
    private static void add(String code, String title){
        Instrument instrument=new Instrument();
        instrument.setTitle(title);
        instrument.setCode(code);
        instruments.add(instrument);
    }
    
    public static List<Instrument> getInstruments(){
        return instruments;
    }
    
}
