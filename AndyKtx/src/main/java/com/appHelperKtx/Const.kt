package com.appHelperKtx

object Const {

    val NAME_REGEX = "^[a-zA-Z\\s]+"
    val EMAIL_REGEX1 = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$"
    val EMAIL_REGEX2 = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])"

    val PLAYSTORELINK = "market://details?id="
    val RETURN_LINK = "http://play.google.com/store/apps/details?id="

    val KB = 1024
    val MB = KB * KB

    val VIDEO_URL = "http://youtube.com/watch?v="
    val IMAGE_URL = "http://img.youtube.com/vi/"

    val DIR_NAME = "r_cache_r"
    val MEM_CACHE_SIZE = MB * 5

    val INITIAL_CAPACITY = 16
    val LOAD_FACTOR = 0.75f
    val CACHE_FILENAME_PREFIX = "my_cache_"
    val IO_BUFFER_SIZE = 3 * KB // 3KB
    val MAX_REMOVALS = 4
    val DISK_MAX_CACHE_ITEM_COUNT = 32
    val DISK_MAX_CACHE_BYTE_SIZE = (MB * 10).toLong()  // 10MB

    val LINE_SEP = System.getProperty("line.separator")

    val BUFFER_LEN = 8192

}