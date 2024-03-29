package com.menasr.andy;

import android.app.Activity;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class Media {

    /**
     * Get total duration of video uri
     *
     * @param ctx      context of activity
     * @param mediaUri uri of video file
     * @return long duration
     */
    public long getDuration(Context ctx, Uri mediaUri) {
        Cursor cur = ctx.getContentResolver().query(mediaUri, new String[]{MediaStore.Video.Media.DURATION}, null, null, null);
        long duration = -1;

        try {
            if (cur != null && cur.getCount() > 0) {
                while (cur.moveToNext()) {
                    duration = cur.getLong(cur.getColumnIndex(MediaStore.Video.Media.DURATION));

                    if (duration == 0)
                        Andy.LOG.printInAllLogFormat("Get Duration", "#getMediaDuration The image size was found to be 0. Reason: UNKNOWN");

                }    // end while
            } else if (cur.getCount() == 0) {
                Andy.LOG.printInAllLogFormat("Get Duration", "#getMediaDuration cur size is 0. File may not exist");
            } else {
                Andy.LOG.printInAllLogFormat("Get Duration", "#getMediaDuration cur is null");
            }
        } finally {
            if (cur != null && !cur.isClosed()) {
                cur.close();
            }
        }

        return duration;
    }

    /**
     * Checks if the parameter {@link Uri} is a Media content uri.
     ****/
    public boolean isMediaContentUri(Uri uri) {
        if (!uri.toString().contains("content://media/")) {
            Andy.LOG.printInAllLogFormat("Media content uri", "#isContentUri The uri is not a media content uri");
            return false;
        } else {
            return true;
        }
    }

    /**
     * Creates an intent to take a video from camera or gallery or any other application that can
     * handle the intent.
     *
     * @param ctx
     * @param savingUri
     * @param durationInSeconds
     * @return
     */
    public Intent createTakeVideoIntent(Activity ctx, Uri savingUri, int durationInSeconds) {

        if (savingUri == null) {
            throw new NullPointerException("Uri cannot be null");
        }

        final List<Intent> cameraIntents = new ArrayList<Intent>();
        final Intent captureIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        final PackageManager packageManager = ctx.getPackageManager();
        final List<ResolveInfo> listCam = packageManager.queryIntentActivities(captureIntent, 0);
        for (ResolveInfo res : listCam) {
            final String packageName = res.activityInfo.packageName;
            final Intent intent = new Intent(captureIntent);
            intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
            intent.setPackage(packageName);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, savingUri);
            intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, durationInSeconds);
            cameraIntents.add(intent);
        }

        // Filesystem.
        final Intent galleryIntent = new Intent();
        galleryIntent.setType("video/*");
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);

        // Chooser of filesystem options.
        final Intent chooserIntent = Intent.createChooser(galleryIntent, "Select Source");

        // Add the camera options.
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, cameraIntents.toArray(new Parcelable[]{}));

        return chooserIntent;
    }

    /**
     * Creates a ACTION_IMAGE_CAPTURE photo & ACTION_GET_CONTENT intent. This intent will be
     * aggregation of intents required to take picture from Gallery and Camera at the minimum. The
     * intent will also be directed towards the apps that are capable of sourcing the image data.
     * For e.g. Dropbox, Astro file manager.
     *
     * @param savingUri Uri to store a high resolution image at. If the user takes the picture using the
     *                  camera the image will be stored at this uri.
     **/
    public Intent createTakePictureIntent(Activity ctx, Uri savingUri) {

        if (savingUri == null) {
            throw new NullPointerException("Uri cannot be null");
        }

        final List<Intent> cameraIntents = new ArrayList<Intent>();
        final Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        final PackageManager packageManager = ctx.getPackageManager();
        final List<ResolveInfo> listCam = packageManager.queryIntentActivities(captureIntent, 0);
        for (ResolveInfo res : listCam) {
            final String packageName = res.activityInfo.packageName;
            final Intent intent = new Intent(captureIntent);
            intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
            intent.setPackage(packageName);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, savingUri);
            cameraIntents.add(intent);
        }

        // Filesystem.
        final Intent galleryIntent = new Intent();
        galleryIntent.setType("image/*");
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);

        // Chooser of filesystem options.
        final Intent chooserIntent = Intent.createChooser(galleryIntent, "Select Source");

        // Add the camera options.
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, cameraIntents.toArray(new Parcelable[]{}));

        return chooserIntent;
    }

    /**
     * Creates external content:// scheme uri to save the images at. The image saved at this
     * {@link Uri} will be visible via the gallery application on the device.
     **/
    @Nullable
    public Uri createImageUri(Context ctx) throws IOException {

        if (ctx == null) {
            throw new NullPointerException("Context cannot be null");
        }

        Uri imageUri = null;

        ContentValues values = new ContentValues();
        values.put(MediaStore.MediaColumns.TITLE, "");
        values.put(MediaStore.Images.ImageColumns.DESCRIPTION, "");
        imageUri = ctx.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

        return imageUri;
    }

    /**
     * Creates external content:// scheme uri to save the videos at.
     *
     * @return uri of video
     **/
    @Nullable
    public Uri createVideoUri(Context ctx) throws IOException {

        if (ctx == null) {
            throw new NullPointerException("Context cannot be null");
        }

        Uri imageUri = null;

        ContentValues values = new ContentValues();
        values.put(MediaStore.MediaColumns.TITLE, "");
        values.put(MediaStore.Images.ImageColumns.DESCRIPTION, "");
        imageUri = ctx.getContentResolver().insert(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, values);

        return imageUri;
    }

    /**
     * Gets media type from the Uri.
     */
    @Nullable
    public String getMediaType(Uri uri) {
        if (uri == null) {
            return null;
        }

        String uriStr = uri.toString();

        if (uriStr.contains("video")) {
            return "video";
        } else if (uriStr.contains("audio")) {
            return "audio";
        } else if (uriStr.contains("image")) {
            return "image";
        } else {
            return null;
        }
    }

    /**
     * Gets media file name.
     **/
    public String getMediaFileName(Context ctx, Uri mediaUri) {
        String colName = MediaStore.MediaColumns.DISPLAY_NAME;
        Cursor cur = ctx.getContentResolver().query(mediaUri, new String[]{colName}, null, null, null);
        String dispName = null;

        try {
            if (cur != null && cur.getCount() > 0) {
                while (cur.moveToNext()) {
                    dispName = cur.getString(cur.getColumnIndex(colName));

                    // for unknown reason, the image size for image was found to
                    // be 0
                    // Log.v( TAG, "#getMediaFileName byte.size: " + size );

                    if (TextUtils.isEmpty(colName)) {
                        Andy.LOG.printInAllLogFormat("Media file name", "#getMediaFileName The file name is blank or null. Reason: UNKNOWN");
                    }

                } // end while
            } else if (cur != null && cur.getCount() == 0) {
                Andy.LOG.printInAllLogFormat("Media file name", "#getMediaFileName File may not exist");
            } else {
                Andy.LOG.printInAllLogFormat("Media file name", "#getMediaFileName cur is null");
            }
        } finally {
            if (cur != null && !cur.isClosed()) {
                cur.close();
            }
        }

        return dispName;
    }

    /**
     * Gets the size of the media resource pointed to by the parameter mediaUri.
     * <p/>
     * Known bug: for unknown reason, the image size for some images was found to be 0
     *
     * @param mediaUri uri to the media resource. For e.g. content://media/external/images/media/45490 or
     *                 content://media/external/video/media/45490
     * @return Size in bytes
     **/
    public long getMediaSize(Context context, Uri mediaUri) {
        Cursor cur = context.getContentResolver().query(mediaUri, new String[]{MediaStore.Images.Media.SIZE}, null, null, null);
        long size = -1;

        try {
            if (cur != null && cur.getCount() > 0) {
                while (cur.moveToNext()) {
                    size = cur.getLong(cur.getColumnIndex(MediaStore.Images.Media.SIZE));

                    // for unknown reason, the image size for image was found to
                    // be 0
                    // Log.v( TAG, "#getSize byte.size: " + size );

                    if (size == 0)
                        Andy.LOG.printInAllLogFormat("Media size", "#getSize The media size was found to be 0. Reason: UNKNOWN");

                } // end while
            } else if (cur.getCount() == 0) {
                Andy.LOG.printInAllLogFormat("Media size", "#getMediaSize cur size is 0. File may not exist");
            } else {
                Andy.LOG.printInAllLogFormat("Media size", "#getMediaSize cur is null");
            }
        } finally {
            if (cur != null && !cur.isClosed()) {
                cur.close();
            }
        }

        return size;
    }

    /**
     * Gets the media data from the one of the following media {@link android.content.ContentProvider} This method
     * should not be called from the main thread of the application. Calling this method may have
     * performance issues as this may allocate a huge memory array.
     * <ul>
     * <li>{@link MediaStore.Images.Media}</li>
     * <li>{@link MediaStore.Audio.Media}</li>
     * <li>{@link MediaStore.Video.Media}</li>
     * </ul>
     *
     * @param ctx Context object
     * @param uri Media content uri of the image, audio or video resource
     */
    @Nullable
    public byte[] getMediaData(Context ctx, Uri uri) {
        if (uri == null) {
            throw new NullPointerException("Uri cannot be null");
        }

        Cursor cur = ctx.getContentResolver().query(uri, new String[]{MediaStore.Images.Media.DATA}, null, null, null);
        byte[] data = null;

        try {
            if (cur != null && cur.getCount() > 0) {
                if (cur.moveToNext()) {
                    String path = cur.getString(cur.getColumnIndex(MediaStore.Images.Media.DATA));

                    try {
                        File f = new File(path);
                        FileInputStream fis = new FileInputStream(f);
                        data = readStreamToBytes(fis);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    // Log.v( TAG, "#getVideoData byte.size: " + data.length );
                } // end while
            } else
                Andy.LOG.printInAllLogFormat("Media size", "#getMediaData cur is null or blank");
        } finally {
            if (cur != null && !cur.isClosed()) {
                cur.close();
            }
        }

        return data;
    }

    /**
     * Returns true if the mime type is a standard image mime type
     */
    public boolean isImage(String mimeType) {
        if (mimeType != null) {
            if (mimeType.startsWith("image/"))
                return true;
            else
                return false;
        } else
            return false;
    }

    /**
     * Returns true if the mime type is a standard audio mime type
     */
    public boolean isAudio(String mimeType) {
        if (mimeType != null) {
            if (mimeType.startsWith("audio/"))
                return true;
            else
                return false;
        } else
            return false;
    }

    /**
     * Returns true if the mime type is a standard video mime type
     */
    public boolean isVideo(String mimeType) {
        if (mimeType != null) {
            if (mimeType.startsWith("video/"))
                return true;
            else
                return false;
        } else {
            return false;
        }
    }

    /**
     * Identifies if the content represented by the parameter mimeType is media. Image, Audio and
     * Video is treated as media by this method. You can refer to standard MIME type here. <a
     * href="http://www.iana.org/assignments/media-types/media-types.xhtml" >Standard MIME
     * types.</a>
     *
     * @param mimeType standard MIME type of the data.
     */
    public boolean isMedia(String mimeType) {
        boolean isMedia = false;

        if (mimeType != null) {
            if (mimeType.startsWith("image/") || mimeType.startsWith("video/") || mimeType.startsWith("audio/")) {
                isMedia = true;
            }
        } else {
            isMedia = false;
        }

        return isMedia;
    }

    /**
     * Get the type of the media. Audio, Video or Image.
     *
     * @return Lower case string for one of above listed media type
     */
    public String getMediaType(String contentType) {
        if (isMedia(contentType)) {
            if (isVideo(contentType))
                return "video";
            else if (isAudio(contentType))
                return "audio";
            else if (isImage(contentType))
                return "image";
            else
                return null;
        } else {
            return null;
        }
    }

    /**
     * Writes the given image to the external storage of the device. If external storage is not
     * available, the image is written to the application private directory
     *
     * @param ctx           context of activity
     * @param imageData     byte[] image data
     * @param directoryPath path in which it is to be stored
     * @return Path of the image file that has been written successfully.
     **/
    public String writeImage(Context ctx, byte[] imageData, File directoryPath) {

        final String FILE_NAME = "photograph.jpeg";
        File dir = null;
        String filePath = null;
        OutputStream imageFileOS;

        dir = directoryPath;

        // dir.mkdirs();
        File f = new File(dir, FILE_NAME);

        // File f = getFile( FILE_NAME );

        try {
            imageFileOS = new FileOutputStream(f);
            imageFileOS.write(imageData);
            imageFileOS.flush();
            imageFileOS.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        filePath = f.getAbsolutePath();

        return filePath;
    }

    /**
     * Convert {@linkplain InputStream} to byte array.
     *
     * @throws NullPointerException If input parameter {@link InputStream} is null
     **/
    public byte[] readStreamToBytes(InputStream inputStream) {

        if (inputStream == null) {
            throw new NullPointerException("InputStream is null");
        }

        byte[] bytesData = null;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(inputStream));
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();

            int nRead;
            byte[] data = new byte[16384];

            while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, nRead);
            }

            buffer.flush();

            bytesData = buffer.toByteArray();

            // Log.d( TAG, "#readStream data: " + data );
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (reader != null) {
                try {
                    reader.close();

                    if (inputStream != null)
                        inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }    // finally

        return bytesData;
    }
}
