package com.menasr.org;

import android.widget.EditText;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

public class StringHelper {

    /**
     * Method to convert String to ArrayList
     *
     * @param string   string which will converted to array
     * @param splitter splitter on the basis of which, string is converted to Array
     *                 i.e., like <b>',',':'..etc</b>
     */
    public List<?> stringToArray(String string, String splitter) {
        return Arrays.asList(string.split(splitter));
    }

    /**
     * @param email check for valid email with regex
     * @return true if matches else false
     */
    public boolean isValidEmail(String email) {
        return (email.matches(Const.EMAIL_REGEX1) && email.matches(Const.EMAIL_REGEX2));
    }

    /**
     * @param name check whether it is a vlid name or not.
     * @return true if it matches the regex or false
     */
    public boolean isVlaidName(String name) {
        return name.matches(Const.NAME_REGEX);
    }

    public String getNameRegex() {
        return Const.NAME_REGEX;
    }

    public String getEmailRegex1() {
        return Const.EMAIL_REGEX1;
    }

    public String getEmailRegex2() {
        return Const.EMAIL_REGEX2;
    }

    /**
     * @param tv pass textview to check if it is empty or not
     */
    public boolean isTextViewEmpty(TextView tv) {
        return tv.getText().toString().trim().length() > 0;
    }

    /**
     * @param et pass textview to check if it is empty or not
     */
    public boolean isEditTextEmpty(EditText et) {
        return et.getText().toString().trim().length() > 0;
    }

    /**
     * Set the first letter of string upper case.
     *
     * @param s The string.
     * @return the string with first letter upper.
     */
    public String upperFirstLetter(final String s) {
        if (s == null || s.length() == 0) return "";
        if (!Character.isLowerCase(s.charAt(0))) return s;
        return String.valueOf((char) (s.charAt(0) - 32)) + s.substring(1);
    }

    /**
     * Set the first letter of string lower case.
     *
     * @param s The string.
     * @return the string with first letter lower.
     */
    public String lowerFirstLetter(final String s) {
        if (s == null || s.length() == 0) return "";
        if (!Character.isUpperCase(s.charAt(0))) return s;
        return String.valueOf((char) (s.charAt(0) + 32)) + s.substring(1);
    }

    /**
     * Reverse the string.
     *
     * @param s The string.
     * @return the reverse string.
     */
    public String reverse(final String s) {
        if (s == null) return "";
        int len = s.length();
        if (len <= 1) return s;
        int mid = len >> 1;
        char[] chars = s.toCharArray();
        char c;
        for (int i = 0; i < mid; ++i) {
            c = chars[i];
            chars[i] = chars[len - i - 1];
            chars[len - i - 1] = c;
        }
        return new String(chars);
    }

}
