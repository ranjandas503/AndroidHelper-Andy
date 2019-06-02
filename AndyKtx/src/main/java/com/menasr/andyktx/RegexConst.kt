package com.menasr.andyktx

object RegexConst {
    /**
     * Regex of simple mobile.
     */
    val REGEX_MOBILE_SIMPLE = "^[1]\\d{10}$"

    /**
     * Regex of telephone number.
     */
    val REGEX_TEL = "^0\\d{2,3}[- ]?\\d{7,8}"

    /**
     * Regex of email.
     */
    val REGEX_EMAIL = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$"

    /**
     * Regex of url.
     */
    val REGEX_URL = "[a-zA-z]+://[^\\s]*"

    /**
     * Regex of username.
     *
     * scope for "a-z", "A-Z", "0-9", "_", "Chinese character"
     *
     * can't end with "_"
     *
     * length is between 6 to 20
     */
    val REGEX_USERNAME = "^[\\w\\u4e00-\\u9fa5]{6,20}(?<!_)$"

    /**
     * Regex of date which pattern is "yyyy-MM-dd".
     */
    val REGEX_DATE = "^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$"

    /**
     * Regex of ip address.
     */
    val REGEX_IP = "((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)"

    /**
     * Regex of double-byte characters.
     */
    val REGEX_DOUBLE_BYTE_CHAR = "[^\\x00-\\xff]"

    /**
     * Regex of blank line.
     */
    val REGEX_BLANK_LINE = "\\n\\s*\\r"

    /**
     * Regex of QQ number.
     */
    val REGEX_QQ_NUM = "[1-9][0-9]{4,}"

    /**
     * Regex of positive integer.
     */
    val REGEX_POSITIVE_INTEGER = "^[1-9]\\d*$"

    /**
     * Regex of negative integer.
     */
    val REGEX_NEGATIVE_INTEGER = "^-[1-9]\\d*$"

    /**
     * Regex of integer.
     */
    val REGEX_INTEGER = "^-?[1-9]\\d*$"

    /**
     * Regex of non-negative integer.
     */
    val REGEX_NOT_NEGATIVE_INTEGER = "^[1-9]\\d*|0$"

    /**
     * Regex of non-positive integer.
     */
    val REGEX_NOT_POSITIVE_INTEGER = "^-[1-9]\\d*|0$"

    /**
     * Regex of positive float.
     */
    val REGEX_POSITIVE_FLOAT = "^[1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*$"

    /**
     * Regex of negative float.
     */
    val REGEX_NEGATIVE_FLOAT = "^-[1-9]\\d*\\.\\d*|-0\\.\\d*[1-9]\\d*$"
}
