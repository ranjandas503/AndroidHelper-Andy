package com.menasr.androidhelper

import android.annotation.SuppressLint
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class DateTime {

    /**
     * method to get date and time
     *
     * @param date_time_millisecond prove date and time millis
     * @param dateFormat            dateFormat String like "HH:mm:ss","yyyy-MM-dd HH:mm:ss"...etc
     * @return String date or time or date,time according to dateFormat
     */
    @SuppressLint("SimpleDateFormat")
    fun getTimeFromMillis(date_time_millisecond: String, dateFormat: String): String {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = java.lang.Long.parseLong(date_time_millisecond)
        return SimpleDateFormat(dateFormat).format(calendar.time)
    }

    /**
     * method to get minutes from start and end time
     *
     * @param startTime       provide start time in millis
     * @param endTime         provide end time in millis
     * @param formatSpecifier pass format specifier like(:, -, /)
     * @return minutes as String
     */
    fun getDifferenceHHmmSS(startTime: Long?, endTime: Long?, formatSpecifier: String): String {
        val difference = endTime!! - startTime!!
        val days = (difference / (1000 * 60 * 60 * 24)).toInt()
        val hours = ((difference - 1000 * 60 * 60 * 24 * days) / (1000 * 60 * 60)).toInt()
        val min =
            (difference - (1000 * 60 * 60 * 24 * days).toLong() - (1000 * 60 * 60 * hours).toLong()).toInt() / (1000 * 60)

        val h: String
        val m: String
        h = if (hours < 10) "0$hours" else "" + hours
        m = if (min < 10) "0$min" else "" + min
        val seperator = "%s" + formatSpecifier + "%s" + formatSpecifier + "00"

        return String.format(seperator, h, m)
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
    fun convertDate(date: String, defaultFormat: String, formatWanted: String): String {
        @SuppressLint("SimpleDateFormat") val format1 = SimpleDateFormat(defaultFormat)
        @SuppressLint("SimpleDateFormat") val format2 = SimpleDateFormat(formatWanted)
        try {
            return format2.format(format1.parse(date))
        } catch (e: ParseException) {
            return date
        }

    }

    /**
     * method to get minutes from start and end time
     *
     * @param startTime provide start time in millis
     * @param endTime   provide end time in millis
     * @return minutes as Integer
     */
    fun getDifferenceInMin(startTime: Long?, endTime: Long?): Int? {
        val difference = endTime!! - startTime!!
        val days = (difference / (1000 * 60 * 60 * 24)).toInt()
        val hours = ((difference - 1000 * 60 * 60 * 24 * days) / (1000 * 60 * 60)).toInt()
        val min =
            (difference - (1000 * 60 * 60 * 24 * days).toLong() - (1000 * 60 * 60 * hours).toLong()).toInt() / (1000 * 60)

        return hours * 60 + min
    }

    /**
     * method to get minutes from start and end time
     *
     * @param startTime  provide start time in millis
     * @param endTime    provide end time in millis
     * @param rTimeConst pass RTimeConstant value, like if you want return type
     * as hour then pass **RTimeConstant.HOUR**, if min
     * then **RTimeConstant.MIN** and so on.
     * You can only use RTimeConst.**DAY**,**HOUR** or **MIN**,
     * don't use other values, in other cases it will return min only
     * @return hours as Long and minutes will be removed
     */
    fun getDifference(startTime: Long?, endTime: Long?, rTimeConst: String): Long? {
        val difference = endTime!! - startTime!!
        val days = difference / (1000 * 60 * 60 * 24)
        val hours = (difference - 1000 * 60 * 60 * 24 * days) / (1000 * 60 * 60)
        val min = (difference - 1000 * 60 * 60 * 24 * days - 1000 * 60 * 60 * hours) / (1000 * 60)

        when (rTimeConst) {
            RTimeConst.DAY -> return days
            RTimeConst.HOUR -> return days * 24 + hours
            else -> return days * 24 + hours * 60 + min
        }
    }

    /**
     * Method to get Date time from millis, You can use System.currentTimeMillis()
     *
     * @param dateFormat date format like **"HH:mm:ss"**
     * You can also use **RTimeConst** class, which is also included in library
     * @return String date time according to DateFormat
     */
    @SuppressLint("SimpleDateFormat")
    fun getDateTimeFromMillis(millis: Long, dateFormat: String): String {
        return SimpleDateFormat(dateFormat).format(Date(millis))
    }


    /**
     * @param millis provide milliseconds
     * @return current system time in millis in String form with seconds to 00 in 24 hour format
     */
    fun getCurrentTimeStampWithSecRoundOff(millis: Long): String {
        val ct = getDateTimeFromMillis(System.currentTimeMillis(), RTimeConst.TIME_24HOUR)
        return ct.substring(0, ct.lastIndexOf(':')) + ":00"
    }

    /**
     * @return current time in millis
     */
    fun getCurTimeInMillis(): Long {
        return System.currentTimeMillis()
    }

    /**
     * return current time 24 hour format
     */
    fun get24HourCurTime(): String {
        return getDateTimeFromMillis(getCurTimeInMillis(), RTimeConst.TIME_24HOUR)
    }

    /**
     * @return current time in 12 hour format
     */
    fun get12HourCurTime(): String {
        return getDateTimeFromMillis(getCurTimeInMillis(), RTimeConst.TIME_12HOUR)
    }

    /**
     * Method to get Current Date
     *
     * @return current date in your given String format
     */
    fun getCurDate(format: String): String {
        return getDateTimeFromMillis(getCurTimeInMillis(), format)
    }

    /**
     * method to provide **HH:MM:SS** after adding miutes to current **HH:MM**
     *
     * @param currentHHMM    must provide time in **HH:MM** format
     * @param minutes_to_add send minutes to add or remove
     * @return **HH:MM:SS** time after adding minutes
     */
    fun addRemMinToHHMM(currentHHMM: String, minutes_to_add: Int?): String {
        val cal = Calendar.getInstance()
        var h: String? = null
        var m: String? = null
        try {
            cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(currentHHMM.substring(0, currentHHMM.indexOf(':'))))
            cal.set(
                Calendar.MINUTE,
                Integer.parseInt(currentHHMM.substring(currentHHMM.indexOf(':') + 1, currentHHMM.length))
            )

            cal.add(Calendar.MINUTE, minutes_to_add!!)
            h = "" + cal.get(Calendar.HOUR_OF_DAY)
            m = "" + cal.get(Calendar.MINUTE)
        } catch (e: NumberFormatException) {
            e.printStackTrace()
        }

        return (if (cal.get(Calendar.HOUR_OF_DAY) < 10) "0" + h!! else h) + ":" + if (cal.get(Calendar.MINUTE) < 10) "0" + m!! else m
    }


    fun getDaysFromMillis(millis: Long): Long = TimeUnit.MILLISECONDS.toDays(millis)

    fun getDaysFromMinutes(min: Long): Long = TimeUnit.MINUTES.toDays(min)


    fun getDaysFromHours(hours: Long): Long = TimeUnit.HOURS.toDays(hours)


    fun getDaysFromSeconds(seconds: Long): Long = TimeUnit.SECONDS.toDays(seconds)


    fun getHourFromMillis(millis: Long): Long = TimeUnit.MILLISECONDS.toHours(millis)


    fun getHourFromSeconds(seconds: Long): Long = TimeUnit.SECONDS.toHours(seconds)


    fun getHourFromMinutes(min: Long): Long = TimeUnit.MINUTES.toHours(min)


    fun getHourFromDays(days: Long): Long = TimeUnit.DAYS.toHours(days)


    fun getMinFromMillis(millis: Long): Long = TimeUnit.MILLISECONDS.toMinutes(millis)


    fun getMinFromSeconds(seconds: Long): Long = TimeUnit.SECONDS.toMinutes(seconds)


    fun getMinFromHours(hours: Long): Long = TimeUnit.HOURS.toMinutes(hours)


    fun getMinFromDays(days: Long): Long = TimeUnit.DAYS.toMinutes(days)


    fun getSecFromMillis(millis: Long): Long = TimeUnit.MILLISECONDS.toSeconds(millis)


    fun getSecFromMinute(min: Long): Long = TimeUnit.MINUTES.toSeconds(min)


    fun getSecFromHours(hours: Long): Long = TimeUnit.HOURS.toSeconds(hours)


    fun getSecFromDays(days: Long): Long = TimeUnit.DAYS.toSeconds(days)


    fun getMillisFromSecond(millis: Long): Long = TimeUnit.MILLISECONDS.toSeconds(millis)


    fun getMillisFromMinute(min: Long): Long = TimeUnit.MINUTES.toSeconds(min)


    fun getMillisFromHours(hours: Long): Long = TimeUnit.HOURS.toSeconds(hours)


    fun getMillisFromDays(days: Long): Long = TimeUnit.DAYS.toSeconds(days)


    /**
     * Method to get String date from millis
     *
     * @param format format of date i.e., like dd:mm:yyy..etc
     * @param millis milliseconds
     */
    fun getFormattedTime(format: String, millis: Int): String {
        return String.format(
            Locale.getDefault(), format,
            TimeUnit.MILLISECONDS.toMinutes(millis.toLong()),
            TimeUnit.MILLISECONDS.toSeconds(millis.toLong()) - TimeUnit.MINUTES.toSeconds(
                TimeUnit.MILLISECONDS.toMinutes(
                    millis.toLong()
                )
            )
        )
    }

    /**
     * Method to convert 12 hours time hh:mm to 24 hours time
     *
     * @param hhmm hour and minute, you can provide hour,minute and second also
     * @return hh:MM:ss a format
     */
    @SuppressLint("SimpleDateFormat")
    fun convert24HoursTimeTo12HoursTime(hhmm: String): String {
        try {
            val sdf = SimpleDateFormat("H:mm")
            return SimpleDateFormat("K:mm a").format(sdf.parse(hhmm))
        } catch (e: ParseException) {
            e.printStackTrace()
            return ""
        }

    }

    /**
     * Method to get long millis from hhmmss
     *
     * @param hhmmss send hour min sec
     * @return long millis in 24 hour format
     */
    @SuppressLint("SimpleDateFormat")
    fun getMillisFromHHMMSS(hhmmss: String): Long {
        try {
            return SimpleDateFormat("HH:mm:ss").parse(hhmmss).time
        } catch (e: ParseException) {
            return 0L
        }

    }
}