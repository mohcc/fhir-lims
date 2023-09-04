package zw.gov.mohcc.mrs.fhir.lims.util;

import java.util.ArrayList;
import java.util.List;
import zw.gov.mohcc.mrs.fhir.lims.entities.RejectionReason;

public class RejectionReasonRepository {
    
    private static final List<RejectionReason> rejectionReasons=new ArrayList<>();
    
    static{
        add("001a00e5d61545cd9de7ca8087cc6a73", "RR001", "Specimen lacked proper identification");
        add("001a00e5d61545cd9de7ca8087cc6a74", "RR002", "Mismatching form and specimen");
        add("001a00e5d61545cd9de7ca8087cc6a75", "RR003", "Insufficient specimen quantity");
        add("001a00e5d61545cd9de7ca8087cc6a76", "RR004", "Request form not submitted");
        add("001a00e5d61545cd9de7ca8087cc6a77", "RR005", "Specimen not submitted");
        add("001a00e5d61545cd9de7ca8087cc6a78", "RR006", "Cross contamination");
        add("001a00e5d61545cd9de7ca8087cc6a80", "RR007", "Sample collected on Humidity Indicator");
        add("001a00e5d61545cd9de7ca8087cc6a81", "RR008", "Routine Histological");
        add("001a00e5d61545cd9de7ca8087cc6a82", "RR009", "Clotted Blood");
        add("001a00e5d61545cd9de7ca8087cc6a83", "RR0010", "Specimen too old");
        add("001a00e5d61545cd9de7ca8087cc6a84", "RR0011", "No sample submitted");
        add("001a00e5d61545cd9de7ca8087cc6a85", "RR0012", "Quantity not sufficient");
        
    }

    public static List<RejectionReason> getRejectionReasons() {
        return rejectionReasons;
    }
    
    private static void add(String uid, String code, String name){
       rejectionReasons.add(new RejectionReason(uid,code,name));
    }
    
    
    
}
