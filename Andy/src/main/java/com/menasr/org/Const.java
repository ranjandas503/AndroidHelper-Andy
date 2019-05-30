package com.menasr.org;

class Const {

    static final String NAME_REGEX = "^[a-zA-Z\\s]+";
    static final String EMAIL_REGEX1 = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
    static final String EMAIL_REGEX2 = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

    static final String PLAYSTORELINK = "market://details?id=";
    static final String RETURN_LINK = "http://play.google.com/store/apps/details?id=";

    static final int KB = 1024;
    static final int MB = (KB * KB);

    static final String VIDEO_URL = "http://youtube.com/watch?v=";
    static final String IMAGE_URL = "http://img.youtube.com/vi/";

    static final String DIR_NAME = "r_cache_r";
    static final int MEM_CACHE_SIZE = MB * 5;

    static final int INITIAL_CAPACITY = 16;
    static final float LOAD_FACTOR = 0.75f;
    static final String CACHE_FILENAME_PREFIX = "my_cache_";
    static final int IO_BUFFER_SIZE = 3 * KB; // 3KB
    static final int MAX_REMOVALS = 4;
    static final int DISK_MAX_CACHE_ITEM_COUNT = 32;
    static final long DISK_MAX_CACHE_BYTE_SIZE = MB * 10;  // 10MB

    static final String LINE_SEP = System.getProperty("line.separator");

    static final int BUFFER_LEN = 8192;
}
