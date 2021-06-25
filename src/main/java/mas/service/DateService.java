package mas.service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static java.util.Calendar.*;

public class DateService {
    public static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat simpleDateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public static int getYearDifference(Date date1, Date date2) {
        boolean isNaturalOrder = date1.before(date2);

        Calendar firstCalendar = getCalendar(isNaturalOrder ? date1 : date2),
                lastCalendar = getCalendar(isNaturalOrder ? date2 : date1);

        int yearDifference = lastCalendar.get(YEAR) - firstCalendar.get(YEAR);

        if (!isFullYearDifference(firstCalendar, lastCalendar)) {
            yearDifference--;
        }

        return isNaturalOrder ? yearDifference : -yearDifference;
    }

    public static Calendar getCalendar(Date date) {
        Calendar calendar = Calendar.getInstance(getPolishLocale());
        calendar.setTime(date);

        return calendar;
    }

    public static Locale getPolishLocale() {
        return new Locale("pl", "PL");
    }

    private static boolean isFullYearDifference(Calendar firstCalendar, Calendar lastCalendar) {
        boolean isFirstMonthEarlier = firstCalendar.get(MONTH) < lastCalendar.get(MONTH),
                isFirstMonthEqual = firstCalendar.get(MONTH) == lastCalendar.get(MONTH),
                isFirstDayOfMonthEarlierOrEqual = firstCalendar.get(DATE) <= lastCalendar.get(DATE);

        return isFirstMonthEarlier || isFirstMonthEqual && isFirstDayOfMonthEarlierOrEqual;
    }

    public static Date convertToDateViaSqlDate(LocalDateTime dateToConvert) {
        return (dateToConvert != null)
                ? java.sql.Timestamp.valueOf(dateToConvert)
                : null;
    }

    public static LocalDateTime convertToLocalDateViaSqlDate(Date dateToConvert) {
        return (dateToConvert != null)
                ? new java.sql.Timestamp(dateToConvert.getTime()).toLocalDateTime()
                : null;
    }
}
