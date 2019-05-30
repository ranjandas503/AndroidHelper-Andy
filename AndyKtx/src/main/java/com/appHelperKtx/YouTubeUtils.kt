package com.appHelperKtx

import android.net.Uri
import android.text.TextUtils

import java.net.MalformedURLException
import java.net.URL

class YouTubeUtils {

    /**
     * Crete youtube video url on the basis of videoId
     *
     * @param videoId send video id of url
     * @throws IllegalArgumentException if video id is null or black
     */
    fun createVideoUrl(videoId: String): String {

        if (TextUtils.isEmpty(videoId)) {
            throw IllegalArgumentException("Video Id cannot be null or blank")
        }

        return Const.VIDEO_URL + videoId
    }

    /**
     * Creates thubmnail url for a given video ID.
     *
     * @param videoId video id
     * @param quality use **Quality.'name'** for quality
     */
    fun createThumbnailUrl(videoId: String, quality: String?): String {
        var quality = quality

        if (quality == null)
            quality = Quality.THUMBNAIL_QUALITY_DEFAULT

        if (!quality.equals(Quality.THUMBNAIL_QUALITY_DEFAULT, ignoreCase = true) &&
                !quality.equals(Quality.THUMBNAIL_QUALITY_MQ, ignoreCase = true) &&
                !quality.equals(Quality.THUMBNAIL_QUALITY_HQ, ignoreCase = true) &&
                !quality.equals(Quality.THUMBNAIL_QUALITY_SD, ignoreCase = true)) {
            throw IllegalArgumentException("Invalid quality thumbnail requested")
        }

        return Const.IMAGE_URL + videoId + "/" + quality + ".jpg"
    }

    /****
     * Checks to see if the your contains the authority "youtube.com"
     */
    fun isYouTubeUrl(url: String): Boolean {

        if (TextUtils.isEmpty(url)) {
            return false
        }

        val uri = Uri.parse(url)
        val authority = uri.authority
        return if (!TextUtils.isEmpty(authority) && authority!!.contains("youtube.com")) {
            true
        } else {
            false
        }
    }

    /**
     * Fetches video ID from given you tube video URL.
     *
     * @param videoUrl complete url of youtube video
     * @return video ID in String form
     */
    fun getVideoId(videoUrl: String): String? {
        var videoId: String? = null

        // Sample YouTube URLs.
        // "http://www.youtube.com/watch?v=8mKTiD02v3M";
        // "http://www.youtube.com/v/8mKTiD02v3M?version=3&autohide=1";
        // "http://youtu.be/8mKTiD02v3M";

        val url: URL
        try {

            url = URL(videoUrl)

            if (!TextUtils.isEmpty(videoUrl)) {
                if (videoUrl.contains("?v=")) {
                    videoId = videoUrl.split("\\?v=".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1]
                } else if (videoUrl.contains("?version")) {
                    videoId = url.path.split("\\/".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[2]
                } else {
                    videoId = url.path.split("\\/".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[1]
                }
            }
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return videoId
    }


    /**
     * Use This Interface for Quality
     */
    interface Quality {
        companion object {
            val THUMBNAIL_QUALITY_DEFAULT = "default"
            val THUMBNAIL_QUALITY_MQ = "mqdefault"
            val THUMBNAIL_QUALITY_HQ = "hqdefault"
            val THUMBNAIL_QUALITY_SD = "sddefault"
        }
    }
}
