package com.menasr.andy;

import android.net.Uri;
import android.text.TextUtils;

import java.net.MalformedURLException;
import java.net.URL;

public class YouTubeUtils {

    /**
     * Crete youtube video url on the basis of videoId
     *
     * @param videoId send video id of url
     * @throws IllegalArgumentException if video id is null or black
     */
    public String createVideoUrl(String videoId) {

        if (TextUtils.isEmpty(videoId)) {
            throw new IllegalArgumentException("Video Id cannot be null or blank");
        }

        return Const.VIDEO_URL + videoId;
    }

    /**
     * Creates thubmnail url for a given video ID.
     *
     * @param videoId video id
     * @param quality use <b>Quality.'name'</b> for quality
     **/
    public String createThumbnailUrl(String videoId, String quality) {

        if (quality == null)
            quality = Quality.THUMBNAIL_QUALITY_DEFAULT;

        if (!quality.equalsIgnoreCase(Quality.THUMBNAIL_QUALITY_DEFAULT) &&
                !quality.equalsIgnoreCase(Quality.THUMBNAIL_QUALITY_MQ) &&
                !quality.equalsIgnoreCase(Quality.THUMBNAIL_QUALITY_HQ) &&
                !quality.equalsIgnoreCase(Quality.THUMBNAIL_QUALITY_SD)) {
            throw new IllegalArgumentException("Invalid quality thumbnail requested");
        }

        return Const.IMAGE_URL + videoId + "/" + quality + ".jpg";
    }

    /****
     * Checks to see if the your contains the authority "youtube.com"
     ****/
    public boolean isYouTubeUrl(String url) {

        if (TextUtils.isEmpty(url)) {
            return false;
        }

        Uri uri = Uri.parse(url);
        String authority = uri.getAuthority();
        if (!TextUtils.isEmpty(authority) && authority.contains("youtube.com")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Fetches video ID from given you tube video URL.
     *
     * @param videoUrl complete url of youtube video
     * @return video ID in String form
     */
    public String getVideoId(String videoUrl) {
        String videoId = null;

        // Sample YouTube URLs.
        // "http://www.youtube.com/watch?v=8mKTiD02v3M";
        // "http://www.youtube.com/v/8mKTiD02v3M?version=3&autohide=1";
        // "http://youtu.be/8mKTiD02v3M";

        URL url;
        try {

            url = new URL(videoUrl);

            if (!TextUtils.isEmpty(videoUrl)) {
                if (videoUrl.contains("?v=")) {
                    videoId = videoUrl.split("\\?v=")[1];
                } else if (videoUrl.contains("?version")) {
                    videoId = url.getPath().split("\\/")[2];
                } else {
                    videoId = url.getPath().split("\\/")[1];
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return videoId;
    }


    /**
     * Use This Interface for Quality
     */
    public interface Quality {
        String THUMBNAIL_QUALITY_DEFAULT = "default";
        String THUMBNAIL_QUALITY_MQ = "mqdefault";
        String THUMBNAIL_QUALITY_HQ = "hqdefault";
        String THUMBNAIL_QUALITY_SD = "sddefault";
    }
}
