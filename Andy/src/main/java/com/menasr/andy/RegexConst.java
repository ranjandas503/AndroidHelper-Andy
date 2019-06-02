package com.menasr.andy;

public class RegexConst {
    /**
     * Regex of simple mobile.
     */
    public final String REGEX_MOBILE_SIMPLE = "^[1]\\d{10}$";

    /**
     * Regex of telephone number.
     */
    public final String REGEX_TEL = "^0\\d{2,3}[- ]?\\d{7,8}";

    /**
     * Regex of email.
     */
    public final String REGEX_EMAIL = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";

    /**
     * Regex of url.
     */
    public final String REGEX_URL = "[a-zA-z]+://[^\\s]*";

    /**
     * Regex of username.
     * <p>scope for "a-z", "A-Z", "0-9", "_", "Chinese character"</p>
     * <p>can't end with "_"</p>
     * <p>length is between 6 to 20</p>
     */
    public final String REGEX_USERNAME = "^[\\w\\u4e00-\\u9fa5]{6,20}(?<!_)$";

    /**
     * Regex of date which pattern is "yyyy-MM-dd".
     */
    public final String REGEX_DATE = "^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$";

    /**
     * Regex of ip address.
     */
    public final String REGEX_IP = "((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)";

    /**
     * Regex of double-byte characters.
     */
    public final String REGEX_DOUBLE_BYTE_CHAR = "[^\\x00-\\xff]";

    /**
     * Regex of blank line.
     */
    public final String REGEX_BLANK_LINE = "\\n\\s*\\r";

    /**
     * Regex of QQ number.
     */
    public final String REGEX_QQ_NUM = "[1-9][0-9]{4,}";

    /**
     * Regex of positive integer.
     */
    public final String REGEX_POSITIVE_INTEGER = "^[1-9]\\d*$";

    /**
     * Regex of negative integer.
     */
    public final String REGEX_NEGATIVE_INTEGER = "^-[1-9]\\d*$";

    /**
     * Regex of integer.
     */
    public final String REGEX_INTEGER = "^-?[1-9]\\d*$";

    /**
     * Regex of non-negative integer.
     */
    public final String REGEX_NOT_NEGATIVE_INTEGER = "^[1-9]\\d*|0$";

    /**
     * Regex of non-positive integer.
     */
    public final String REGEX_NOT_POSITIVE_INTEGER = "^-[1-9]\\d*|0$";

    /**
     * Regex of positive float.
     */
    public final String REGEX_POSITIVE_FLOAT = "^[1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*$";

    /**
     * Regex of negative float.
     */
    public final String REGEX_NEGATIVE_FLOAT = "^-[1-9]\\d*\\.\\d*|-0\\.\\d*[1-9]\\d*$";
}
