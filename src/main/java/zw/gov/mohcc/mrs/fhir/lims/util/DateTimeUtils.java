package zw.gov.mohcc.mrs.fhir.lims.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DateTimeUtils {

    public static final String HARARE_ZONE = "Africa/Harare";
    public static final String DEFAULT_ZONE = HARARE_ZONE;

    private DateTimeUtils() {

    }

    public static LocalDate convertToLocalDate(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.of(DEFAULT_ZONE))
                .toLocalDate();
    }

    public static LocalDateTime convertToLocalDateTime(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.of(DEFAULT_ZONE))
                .toLocalDateTime();
    }

    public static Date convertToDate(LocalDate localDate) {
        return java.sql.Date.valueOf(localDate);
    }

    public static Date convertToDate(LocalDateTime localDateTime) {
        return java.sql.Timestamp.valueOf(localDateTime);
    }
}
