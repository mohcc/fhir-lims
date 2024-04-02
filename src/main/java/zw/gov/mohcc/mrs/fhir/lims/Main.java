package zw.gov.mohcc.mrs.fhir.lims;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;
import java.util.TimeZone;

public class Main {

    public static void main(String[] args) throws ParseException {

        DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        String string2 = "2024-04-02T00:00:00.000+02:00";
        Date result2 = df2.parse(string2);

        for (String string : TimeZone.getAvailableIDs(TimeZone.getTimeZone(
                "GMT+00:00").getRawOffset())) {
            System.out.println(string);
        }
        
        System.out.println(result2);

        /*System.out.println(new Date().toInstant()
                .atZone(ZoneId.of("Africa/Harare"))
                .toLocalDate());*/
        System.out.println(result2.toInstant()
                .atZone(ZoneId.of("Africa/Harare"))
                .toLocalDate());
    }

}
