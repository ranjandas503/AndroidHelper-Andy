package com.menasr.andyktx

import android.app.Activity
import android.content.ComponentName
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Parcelable
import android.provider.MediaStore
import android.text.TextUtils

import java.io.BufferedReader
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.io.OutputStream
import java.util.ArrayList

class Media {

    /**
     * Get total duration of video uri
     *
     * @param ctx      context of activity
     * @param mediaUri uri of video file
     * @return long duration
     */
    fun getDuration(ctx: Context, mediaUri: Uri): Long {
        val cur = ctx.contentResolver.query(mediaUri, arrayOf(MediaStore.Video.Media.DURATION), null, null, null)
        var duration: Long = -1

        try {
            if (cur != null && cur.count > 0) {
                while (cur.moveToNext()) {
                    duration = cur.getLong(cur.getColumnIndex(MediaStore.Video.Media.DURATION))

                    if (duration == 0L)
                        Andy.LOG.printInAllLogFormat("Get Duration", "#getMediaDuration The image size was found to be 0. Reason: UNKNOWN")

                }    // end while
            } else if (cur!!.count == 0) {
                Andy.LOG.printInAllLogFormat("Get Duration", "#getMediaDuration cur size is 0. File may not exist")
            } else {
                Andy.LOG.printInAllLogFormat("Get Duration", "#getMediaDuration cur is null")
            }
        } finally {
            if (cur != null && !cur.isClosed) {
                cur.close()
            }
        }

        return duration
    }

    /**
     * Checks if the parameter [Uri] is a Media content uri.
     */
    fun isMediaContentUri(uri: Uri): Boolean {
        if (!uri.toString().contains("content://media/")) {
            Andy.LOG.printInAllLogFormat("Media content uri", "#isContentUri The uri is not a media content uri")
            return false
        } else {
            return true
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
    fun createTakeVideoIntent(ctx: Activity, savingUri: Uri?, durationInSeconds: Int): Intent {

        if (savingUri == null) {
            throw NullPointerException("Uri cannot be null")
        }

        val cameraIntents = ArrayList<Intent>()
        val captureIntent = Intent(MediaStore.ACTION_VIDEO_CAPTURE)
        val packageManager = ctx.packageManager
        val listCam = packageManager.queryIntentActivities(captureIntent, 0)
        for (res in listCam) {
            val packageName = res.activityInfo.packageName
            val intent = Intent(captureIntent)
            intent.component = ComponentName(res.activityInfo.packageName, res.activityInfo.name)
            intent.setPackage(packageName)
            intent.putExtra(MediaStore.EXTRA_OUTPUT, savingUri)
            intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, durationInSeconds)
            cameraIntents.add(intent)
        }

        // Filesystem.
        val galleryIntent = Intent()
        galleryIntent.type = "video/*"
        galleryIntent.action = Intent.ACTION_GET_CONTENT

        // Chooser of filesystem options.
        val chooserIntent = Intent.createChooser(galleryIntent, "Select Source")

        // Add the camera options.
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, cameraIntents.toTypedArray<Parcelable>())

        return chooserIntent
    }

    /**
     * Creates a ACTION_IMAGE_CAPTURE photo & ACTION_GET_CONTENT intent. This intent will be
     * aggregation of intents required to take picture from Gallery and Camera at the minimum. The
     * intent will also be directed towards the apps that are capable of sourcing the image data.
     * For e.g. Dropbox, Astro file manager.
     *
     * @param savingUri Uri to store a high resolution image at. If the user takes the picture using the
     * camera the image will be stored at this uri.
     */
    fun createTakePictureIntent(ctx: Activity, savingUri: Uri?): Intent {

        if (savingUri == null) {
            throw NullPointerException("Uri cannot be null")
        }

        val cameraIntents = ArrayList<Intent>()
        val captureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val packageManager = ctx.packageManager
        val listCam = packageManager.queryIntentActivities(captureIntent, 0)
        for (res in listCam) {
            val packageName = res.activityInfo.packageName
            val intent = Intent(captureIntent)
            intent.component = ComponentName(res.activityInfo.packageName, res.activityInfo.name)
            intent.setPackage(packageName)
            intent.putExtra(MediaStore.EXTRA_OUTPUT, savingUri)
            cameraIntents.add(intent)
        }

        // Filesystem.
        val galleryIntent = Intent()
        galleryIntent.type = "image/*"
        galleryIntent.action = Intent.ACTION_GET_CONTENT

        // Chooser of filesystem options.
        val chooserIntent = Intent.createChooser(galleryIntent, "Select Source")

        // Add the camera options.
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, cameraIntents.toTypedArray<Parcelable>())

        return chooserIntent
    }

    /**
     * Creates external content:// scheme uri to save the images at. The image saved at this
     * [Uri] will be visible via the gallery application on the device.
     */
    @Throws(IOException::class)
    fun createImageUri(ctx: Context?): Uri? {

        if (ctx == null) {
            throw NullPointerException("Context cannot be null")
        }

        var imageUri: Uri? = null

        val values = ContentValues()
        values.put(MediaStore.MediaColumns.TITLE, "")
        values.put(MediaStore.Images.ImageColumns.DESCRIPTION, "")
        imageUri = ctx.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)

        return imageUri
    }

    /**
     * Creates external content:// scheme uri to save the videos at.
     *
     * @return uri of video
     */
    @Throws(IOException::class)
    fun createVideoUri(ctx: Context?): Uri? {

        if (ctx == null) {
            throw NullPointerException("Context cannot be null")
        }

        var imageUri: Uri? = null

        val values = ContentValues()
        values.put(MediaStore.MediaColumns.TITLE, "")
        values.put(MediaStore.Images.ImageColumns.DESCRIPTION, "")
        imageUri = ctx.contentResolver.insert(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, values)

        return imageUri
    }

    /**
     * Gets media type from the Uri.
     */
    fun getMediaType(uri: Uri?): String? {
        if (uri == null) {
            return null
        }

        val uriStr = uri.toString()

        return if (uriStr.contains("video")) {
            "video"
        } else if (uriStr.contains("audio")) {
            "audio"
        } else if (uriStr.contains("image")) {
            "image"
        } else {
            null
        }
    }

    /**
     * Gets media file name.
     */
    fun getMediaFileName(ctx: Context, mediaUri: Uri): String? {
        val colName = MediaStore.MediaColumns.DISPLAY_NAME
        val cur = ctx.contentResolver.query(mediaUri, arrayOf(colName), null, null, null)
        var dispName: String? = null

        try {
            if (cur != null && cur.count > 0) {
                while (cur.moveToNext()) {
                    dispName = cur.getString(cur.getColumnIndex(colName))

                    // for unknown reason, the image size for image was found to
                    // be 0
                    // Log.v( TAG, "#getMediaFileName byte.size: " + size );

                    if (TextUtils.isEmpty(colName)) {
                        Andy.LOG.printInAllLogFormat("Media file name", "#getMediaFileName The file name is blank or null. Reason: UNKNOWN")
                    }

                } // end while
            } else if (cur != null && cur.count == 0) {
                Andy.LOG.printInAllLogFormat("Media file name", "#getMediaFileName File may not exist")
            } else {
                Andy.LOG.printInAllLogFormat("Media file name", "#getMediaFileName cur is null")
            }
        } finally {
            if (cur != null && !cur.isClosed) {
                cur.close()
            }
        }

        return dispName
    }

    /**
     * Gets the size of the media resource pointed to by the parameter mediaUri.
     *
     *
     * Known bug: for unknown reason, the image size for some images was found to be 0
     *
     * @param mediaUri uri to the media resource. For e.g. content://media/external/images/media/45490 or
     * content://media/external/video/media/45490
     * @return Size in bytes
     */
    fun getMediaSize(context: Context, mediaUri: Uri): Long {
        val cur = context.contentResolver.query(mediaUri, arrayOf(MediaStore.Images.Media.SIZE), null, null, null)
        var size: Long = -1

        try {
            if (cur != null && cur.count > 0) {
                while (cur.moveToNext()) {
                    size = cur.getLong(cur.getColumnIndex(MediaStore.Images.Media.SIZE))

                    // for unknown reason, the image size for image was found to
                    // be 0
                    // Log.v( TAG, "#getSize byte.size: " + size );

                    if (size == 0L)
                        Andy.LOG.printInAllLogFormat("Media size", "#getSize The media size was found to be 0. Reason: UNKNOWN")

                } // end while
            } else if (cur!!.count == 0) {
                Andy.LOG.printInAllLogFormat("Media size", "#getMediaSize cur size is 0. File may not exist")
            } else {
                Andy.LOG.printInAllLogFormat("Media size", "#getMediaSize cur is null")
            }
        } finally {
            if (cur != null && !cur.isClosed) {
                cur.close()
            }
        }

        return size
    }

    /**
     * Gets the media data from the one of the following media [android.content.ContentProvider] This method
     * should not be called from the main thread of the application. Calling this method may have
     * performance issues as this may allocate a huge memory array.
     *
     *  * [MediaStore.Images.Media]
     *  * [MediaStore.Audio.Media]
     *  * [MediaStore.Video.Media]
     *
     *
     * @param ctx Context object
     * @param uri Media content uri of the image, audio or video resource
     */
    fun getMediaData(ctx: Context, uri: Uri?): ByteArray? {
        if (uri == null) {
            throw NullPointerException("Uri cannot be null")
        }

        val cur = ctx.contentResolver.query(uri, arrayOf(MediaStore.Images.Media.DATA), null, null, null)
        var data: ByteArray? = null

        try {
            if (cur != null && cur.count > 0) {
                if (cur.moveToNext()) {
                    val path = cur.getString(cur.getColumnIndex(MediaStore.Images.Media.DATA))

                    try {
                        val f = File(path)
                        val fis = FileInputStream(f)
                        data = readStreamToBytes(fis)
                    } catch (e: FileNotFoundException) {
                        e.printStackTrace()
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }

                    // Log.v( TAG, "#getVideoData byte.size: " + data.length );
                } // end while
            } else
                Andy.LOG.printInAllLogFormat("Media size", "#getMediaData cur is null or blank")
        } finally {
            if (cur != null && !cur.isClosed) {
                cur.close()
            }
        }

        return data
    }

    /**
     * Returns true if the mime type is a standard image mime type
     */
    fun isImage(mimeType: String?): Boolean {
        return if (mimeType != null) {
            if (mimeType.startsWith("image/"))
                true
            else
                false
        } else
            false
    }

    /**
     * Returns true if the mime type is a standard audio mime type
     */
    fun isAudio(mimeType: String?): Boolean {
        return if (mimeType != null) {
            if (mimeType.startsWith("audio/"))
                true
            else
                false
        } else
            false
    }

    /**
     * Returns true if the mime type is a standard video mime type
     */
    fun isVideo(mimeType: String?): Boolean {
        return if (mimeType != null) {
            if (mimeType.startsWith("video/"))
                true
            else
                false
        } else {
            false
        }
    }

    /**
     * Identifies if the content represented by the parameter mimeType is media. Image, Audio and
     * Video is treated as media by this method. You can refer to standard MIME type here. [Standard MIME
 * types.](http://www.iana.org/assignments/media-types/media-types.xhtml)
     *
     * @param mimeType standard MIME type of the data.
     */
    fun isMedia(mimeType: String?): Boolean {
        var isMedia = false

        if (mimeType != null) {
            if (mimeType.startsWith("image/") || mimeType.startsWith("video/") || mimeType.startsWith("audio/")) {
                isMedia = true
            }
        } else {
            isMedia = false
        }

        return isMedia
    }

    /**
     * Get the type of the media. Audio, Video or Image.
     *
     * @return Lower case string for one of above listed media type
     */
    fun getMediaType(contentType: String): String? {
        return if (isMedia(contentType)) {
            if (isVideo(contentType))
                "video"
            else if (isAudio(contentType))
                "audio"
            else if (isImage(contentType))
                "image"
            else
                null
        } else {
            null
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
     */
    fun writeImage(ctx: Context, imageData: ByteArray, directoryPath: File): String {

        val FILE_NAME = "photograph.jpeg"
        var dir: File? = null
        var filePath: String? = null
        val imageFileOS: OutputStream

        dir = directoryPath

        // dir.mkdirs();
        val f = File(dir, FILE_NAME)

        // File f = getFile( FILE_NAME );

        try {
            imageFileOS = FileOutputStream(f)
            imageFileOS.write(imageData)
            imageFileOS.flush()
            imageFileOS.close()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        filePath = f.absolutePath

        return filePath
    }

    /**
     * Convert [InputStream] to byte array.
     *
     * @throws NullPointerException If input parameter [InputStream] is null
     */
    fun readStreamToBytes(inputStream: InputStream?): ByteArray? {

        if (inputStream == null) {
            throw NullPointerException("InputStream is null")
        }

        var bytesData: ByteArray? = null
        var reader: BufferedReader? = null
        try {
            reader = BufferedReader(InputStreamReader(inputStream))
            val buffer = ByteArrayOutputStream()

            val data = ByteArray(16384)
            val nRead: Int = inputStream.read(data, 0, data.size)

            while (nRead != -1) {
                buffer.write(data, 0, nRead)
            }

            buffer.flush()

            bytesData = buffer.toByteArray()

            // Log.d( TAG, "#readStream data: " + data );
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {

            if (reader != null) {
                try {
                    reader.close()

                    inputStream?.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
        }    // finally

        return bytesData
    }
}
