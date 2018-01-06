package kr.mashup.feedget.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class StringFormatter {

    /*
    2017.03.01
    2015.12.30
     */
    public static String dateYYMMDDdotFormat(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        return dateFormat.format(date);
    }

    /*
    1일 남음
    10일 남음
    111일 남음
    1,034일 남음
     */
    public static String dueDateRemainFormat(Date dueDate) {
        Date todayDate = new Date();
        long diff = dueDate.getTime() - todayDate.getTime();
        return String.format(
                "%s일 남음",
                numberFormat(TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS))
        );

    }

    /*
    1 -> 1
    12 -> 12
    134 -> 134
    5432 -> 5,432
     */
    public static String numberFormat(int number) {
        return numberFormat((long) number);
    }

    /*
    1 -> 1
    12 -> 12
    134 -> 134
    5432 -> 5,432
     */
    public static String numberFormat(long number) {
        DecimalFormat decimalFormatter = new DecimalFormat("#,###");
        return decimalFormatter.format(number);
    }

}
