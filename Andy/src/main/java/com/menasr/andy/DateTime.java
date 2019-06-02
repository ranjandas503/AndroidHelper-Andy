package com.menasr.andy;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

//custom class for Date and Time
public class DateTime {

    /**
     * method to get date and time
     *
     * @param date_time_millisecond prove date and time millis
     * @param dateFormat            dateFormat String like "HH:mm:ss","yyyy-MM-dd HH:mm:ss"...etc
     * @return String date or time or date,time according to dateFormat
     */
    @SuppressLint("SimpleDateFormat")
    public String getTimeFromMillis(String date_time_millisecond, @NonNull String dateFormat) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(Long.parseLong(date_time_millisecond));
        return (new SimpleDateFormat(dateFormat)).format(calendar.getTime());
    }

    /**
     * method to get minutes from start and end time
     *
     * @param startTime       provide start time in millis
     * @param endTime         provide end time in millis
     * @param formatSpecifier pass format specifier like(:, -, /)
     * @return minutes as String
     */
    public String getDifferenceHHmmSS(Long startTime, Long endTime, String formatSpecifier) {
        long difference = endTime - startTime;
        int days = (int) (difference / (1000 * 60 * 60 * 24));
        int hours = (int) ((difference - (1000 * 60 * 60 * 24 * days)) / (1000 * 60 * 60));
        int min = (int) (difference - (1000 * 60 * 60 * 24 * days) - (1000 * 60 * 60 * hours)) / (1000 * 60);

        String h, m;
        h = hours < 10 ? ("0" + hours) : ("" + hours);
        m = min < 10 ? ("0" + min) : ("" + min);
        String seperator = ("%s" + formatSpecifier + "%s" + formatSpecifier + "00");

        return (String.format(seperator, h, m));
    }


    /**
     * Method to convert one date format to another format
     *
     * @param date          String date in any date format like dd-mm-yyyy
     * @param defaultFormat provide default String format in which you are passing your date
     * @param formatWanted  provide string format in which you want it.
     * @return String formatted String date according to your provided format,
     * in case of parse error it will send default string date provided
     */
    public static String convertDate(String date, String defaultFormat, String formatWanted) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat format1 = new SimpleDateFormat(defaultFormat);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat format2 = new SimpleDateFormat(formatWanted);
        try {
            return format2.format(format1.parse(date));
        } catch (ParseException e) {
            return date;
        }
    }

    /**
     * method to get minutes from start and end time
     *
     * @param startTime provide start time in millis
     * @param endTime   provide end time in millis
     * @return minutes as Integer
     */
    public Integer getDifferenceInMin(Long startTime, Long endTime) {
        long difference = endTime - startTime;
        int days = (int) (difference / (1000 * 60 * 60 * 24));
        int hours = (int) ((difference - (1000 * 60 * 60 * 24 * days)) / (1000 * 60 * 60));
        int min = (int) (difference - (1000 * 60 * 60 * 24 * days) - (1000 * 60 * 60 * hours)) / (1000 * 60);

        String h, m;
        h = hours < 10 ? ("0" + hours) : ("" + hours);
        m = min < 10 ? ("0" + min) : ("" + min);

        return (hours * 60) + min;
    }

    /**
     * method to get minutes from start and end time
     *
     * @param startTime  provide start time in millis
     * @param endTime    provide end time in millis
     * @param rTimeConst pass RTimeConstant value, like if you want return type
     *                   as hour then pass <b>RTimeConstant.HOUR</b>, if min
     *                   then <b>RTimeConstant.MIN</b> and so on.
     *                   You can only use TimeConst.<b>DAY</b>,<b>HOUR</b> or <b>MIN</b>,
     *                   don't use other values, in other cases it will return min only
     * @return hours as Long and minutes will be removed
     */
    public Long getDifference(Long startTime, Long endTime, String rTimeConst) {
        long difference = endTime - startTime;
        Long days = (difference / (1000 * 60 * 60 * 24));
        long hours = ((difference - (1000 * 60 * 60 * 24 * days)) / (1000 * 60 * 60));
        long min = (difference - (1000 * 60 * 60 * 24 * days) - (1000 * 60 * 60 * hours)) / (1000 * 60);

        switch (rTimeConst) {
            case TimeConst.DAY:
                return days;
            case TimeConst.HOUR:
                return ((days * 24) + hours);
            default:
                return ((days * 24) + (hours * 60) + min);
        }
    }

    /**
     * Method to get Date time from millis, You can use System.currentTimeMillis()
     *
     * @param dateFormat date format like <b>"HH:mm:ss"</b>
     *                   You can also use <b>TimeConst</b> class, which is also included in library
     * @return String date time according to DateFormat
     */
    @SuppressLint("SimpleDateFormat")
    public String getDateTimeFromMillis(long millis, String dateFormat) {
        return ((new SimpleDateFormat(dateFormat)).format(new Date(millis)));
    }


    /**
     * @param millis provide milliseconds
     * @return current system time in millis in String form with seconds to 00 in 24 hour format
     */
    public String getCurrentTimeStampWithSecRoundOff(long millis) {
        String ct = getDateTimeFromMillis(System.currentTimeMillis(), TimeConst.TIME_24HOUR);
        return ct.substring(0, ct.lastIndexOf(':')) + ":00";
    }

    /**
     * @return current time in millis
     */
    public long getCurTimeInMillis() {
        return System.currentTimeMillis();
    }

    /**
     * return current time 24 hour format
     */
    public String get24HourCurTime() {
        return getDateTimeFromMillis(getCurTimeInMillis(), TimeConst.TIME_24HOUR);
    }

    /**
     * @return current time in 12 hour format
     */
    public String get12HourCurTime() {
        return getDateTimeFromMillis(getCurTimeInMillis(), TimeConst.TIME_12HOUR);
    }

    /**
     * Method to get Current Date
     *
     * @return current date in your given String format
     */
    public String getCurDate(String format) {
        return getDateTimeFromMillis(getCurTimeInMillis(), format);
    }

    /**
     * method to provide <b>HH:MM:SS</b> after adding miutes to current <b>HH:MM</b>
     *
     * @param currentHHMM    must provide time in <b>HH:MM</b> format
     * @param minutes_to_add send minutes to add or remove
     * @return <b>HH:MM:SS</b> time after adding minutes
     */
    public String addRemMinToHHMM(String currentHHMM, Integer minutes_to_add) {
        Calendar cal = Calendar.getInstance();
        String h = null;
        String m = null;
        try {
            cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(currentHHMM.substring(0, currentHHMM.indexOf(':'))));
            cal.set(Calendar.MINUTE, Integer.parseInt(currentHHMM.substring((currentHHMM.indexOf(':') + 1), currentHHMM.length())));

            cal.add(Calendar.MINUTE, minutes_to_add);
            h = "" + cal.get(Calendar.HOUR_OF_DAY);
            m = "" + cal.get(Calendar.MINUTE);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        return (cal.get(Calendar.HOUR_OF_DAY) < 10 ? ("0" + h) : h) + ":" + (cal.get(Calendar.MINUTE) < 10 ? ("0" + m) : m);
    }


    public long getDaysFromMillis(long millis) {
        return TimeUnit.MILLISECONDS.toDays(millis);
    }

    public long getDaysFromMinutes(long min) {
        return TimeUnit.MINUTES.toDays(min);
    }

    public long getDaysFromHours(long hours) {
        return TimeUnit.HOURS.toDays(hours);
    }

    public long getDaysFromSeconds(long seconds) {
        return TimeUnit.SECONDS.toDays(seconds);
    }


    public long getHourFromMillis(long millis) {
        return TimeUnit.MILLISECONDS.toHours(millis);
    }

    public long getHourFromSeconds(long seconds) {
        return TimeUnit.SECONDS.toHours(seconds);
    }

    public long getHourFromMinutes(long min) {
        return TimeUnit.MINUTES.toHours(min);
    }

    public long getHourFromDays(long days) {
        return TimeUnit.DAYS.toHours(days);
    }


    public long getMinFromMillis(long millis) {
        return TimeUnit.MILLISECONDS.toMinutes(millis);
    }

    public long getMinFromSeconds(long seconds) {
        return TimeUnit.SECONDS.toMinutes(seconds);
    }

    public long getMinFromHours(long hours) {
        return TimeUnit.HOURS.toMinutes(hours);
    }

    public long getMinFromDays(long days) {
        return TimeUnit.DAYS.toMinutes(days);
    }


    public long getSecFromMillis(long millis) {
        return TimeUnit.MILLISECONDS.toSeconds(millis);
    }

    public long getSecFromMinute(long min) {
        return TimeUnit.MINUTES.toSeconds(min);
    }

    public long getSecFromHours(long hours) {
        return TimeUnit.HOURS.toSeconds(hours);
    }

    public long getSecFromDays(long days) {
        return TimeUnit.DAYS.toSeconds(days);
    }


    public long getMillisFromSecond(long millis) {
        return TimeUnit.MILLISECONDS.toSeconds(millis);
    }

    public long getMillisFromMinute(long min) {
        return TimeUnit.MINUTES.toSeconds(min);
    }

    public long getMillisFromHours(long hours) {
        return TimeUnit.HOURS.toSeconds(hours);
    }

    public long getMillisFromDays(long days) {
        return TimeUnit.DAYS.toSeconds(days);
    }

    /**
     * Method to get String date from millis
     *
     * @param format format of date i.e., like dd:mm:yyy..etc
     * @param millis milliseconds
     */
    public String getFormattedTime(String format, int millis) {
        return String.format(Locale.getDefault(), format,
                TimeUnit.MILLISECONDS.toMinutes(millis),
                TimeUnit.MILLISECONDS.toSeconds(millis) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
    }

    /**
     * Method to convert 12 hours time hh:mm to 24 hours time
     *
     * @param hhmm hour and minute, you can provide hour,minute and second also
     * @return hh:MM:ss a format
     */
    @SuppressLint("SimpleDateFormat")
    public String convert24HoursTimeTo12HoursTime(String hhmm) {
        try {
            final SimpleDateFormat sdf = new SimpleDateFormat("H:mm");
            return (new SimpleDateFormat("K:mm a").format(sdf.parse(hhmm)));
        } catch (final ParseException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * Method to get long millis from hhmmss
     *
     * @param hhmmss send hour min sec
     * @return long millis in 24 hour format
     */
    @SuppressLint("SimpleDateFormat")
    public long getMillisFromHHMMSS(String hhmmss) {
        try {
            return (new SimpleDateFormat("HH:mm:ss").parse(hhmmss)).getTime();
        } catch (ParseException e) {
            return 0L;
        }
    }

}
