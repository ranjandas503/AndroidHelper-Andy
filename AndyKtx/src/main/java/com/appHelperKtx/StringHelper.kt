package com.appHelperKtx

import android.widget.EditText
import android.widget.TextView

import java.util.Arrays

class StringHelper {

    val nameRegex: String
        get() = Const.NAME_REGEX

    val emailRegex1: String
        get() = Const.EMAIL_REGEX1

    val emailRegex2: String
        get() = Const.EMAIL_REGEX2

    /**
     * Method to convert String to ArrayList
     *
     * @param string   string which will converted to array
     * @param splitter splitter on the basis of which, string is converted to Array
     * i.e., like **',',':'..etc**
     */
    fun stringToArray(string: String, splitter: String): List<*> {
        return Arrays.asList(*string.split(splitter.toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray())
    }

    /**
     * @param email check for valid email with regex
     * @return true if matches else false
     */
    fun isValidEmail(email: String): Boolean {
        return email.matches(Const.EMAIL_REGEX1.toRegex()) && email.matches(Const.EMAIL_REGEX2.toRegex())
    }

    /**
     * @param name check whether it is a vlid name or not.
     * @return true if it matches the regex or false
     */
    fun isVlaidName(name: String): Boolean {
        return name.matches(Const.NAME_REGEX.toRegex())
    }

    /**
     * @param tv pass textview to check if it is empty or not
     */
    fun isTextViewEmpty(tv: TextView): Boolean {
        return tv.text.toString().trim { it <= ' ' }.length > 0
    }

    /**
     * @param et pass textview to check if it is empty or not
     */
    fun isEditTextEmpty(et: EditText): Boolean {
        return et.text.toString().trim { it <= ' ' }.length > 0
    }

    /**
     * Set the first letter of string upper case.
     *
     * @param s The string.
     * @return the string with first letter upper.
     */
    fun upperFirstLetter(s: String?): String {
        if (s == null || s.length == 0) return ""
        return if (!Character.isLowerCase(s[0])) s else (s[0].toInt() - 32).toChar().toString() + s.substring(1)
    }

    /**
     * Set the first letter of string lower case.
     *
     * @param s The string.
     * @return the string with first letter lower.
     */
    fun lowerFirstLetter(s: String?): String {
        if (s == null || s.length == 0) return ""
        return if (!Character.isUpperCase(s[0])) s else (s[0].toInt() + 32).toChar().toString() + s.substring(1)
    }

    /**
     * Reverse the string.
     *
     * @param s The string.
     * @return the reverse string.
     */
    fun reverse(s: String?): String {
        if (s == null) return ""
        val len = s.length
        if (len <= 1) return s
        val mid = len shr 1
        val chars = s.toCharArray()
        var c: Char
        for (i in 0 until mid) {
            c = chars[i]
            chars[i] = chars[len - i - 1]
            chars[len - i - 1] = c
        }
        return String(chars)
    }

}
