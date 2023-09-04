package zw.gov.mohcc.mrs.fhir.lims.util;

import java.util.ArrayList;
import java.util.List;
import zw.gov.mohcc.mrs.fhir.lims.entities.LabContact;

public class LabContactRepository {

    private static final List<LabContact> labContacts = new ArrayList<>();
    
    static{
        add("44","Allan", "Kudakwashe", "Chadamoyo");
        add("80","Aurthur", null, "Musendame");
        add("79","Culani", null, "Msekiwa");
        add("57","Lawrence", null, "Chirowodza");
        add("61","Mufaro", null, "Masamha");
    }
    
    private static void add(String labContactId, String firstname, String middlename, String surname){
        LabContact labContact=new LabContact();
        labContact.setLabContactId(labContactId);
        labContact.setFirstname(firstname);
        labContact.setMiddlename(middlename);
        labContact.setSurname(surname);
        labContacts.add(labContact);        
    }

    public static List<LabContact> getLabContacts() {
        return labContacts;
    }

}
