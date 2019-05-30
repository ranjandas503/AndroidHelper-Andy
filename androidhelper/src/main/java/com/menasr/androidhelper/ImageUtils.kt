package com.menasr.androidhelper

import android.app.Activity
import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.media.ThumbnailUtils
import android.os.Build
import android.support.annotation.ColorInt
import android.support.annotation.DrawableRes
import android.support.v4.content.ContextCompat
import android.support.v4.graphics.drawable.DrawableCompat
import android.support.v4.view.ViewCompat.setBackground
import android.util.Pair
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout

class ImageUtils {

    /**
     * Method to generate Bitmap
     *
     * @param context  context of Activity
     * @param layoutId name of layout like **R.layout.'layout name'**
     * @return bitmap
     */
    fun generateBitmapFromLayout(context: Context, layoutId: Int): Bitmap {
        var bitmap: Bitmap? = null
        val mInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        //Inflate the layout into a view and configure it the way you like
        val view = RelativeLayout(context)
        try {
            mInflater.inflate(layoutId, view, true)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        //Provide it with a layout params. It should necessarily be wrapping the
        //content as we not really going to have a parent for it.
        view.layoutParams = ViewGroup.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT)

        //Pre-measure the view so that height and width don't remain null.
        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED))

        //Assign a size and position to the view and all of its descendants
        view.layout(0, 0, view.measuredWidth, view.measuredHeight)

        //Create the bitmap
        bitmap = Bitmap.createBitmap(view.measuredWidth, view.measuredHeight, Bitmap.Config.ARGB_8888)

        //Create a canvas with the specified bitmap to draw into
        val c = Canvas(bitmap!!)

        //Render this view (and all of its children) to the given Canvas
        view.draw(c)

        return bitmap
    }

    /**
     * reduces the size of the image
     *
     * @param image   uncompressed image
     * @param maxSize max size of image to compress
     * @return new bitmap
     */
    fun getResizedBitmap(image: Bitmap, maxSize: Int): Bitmap {
        var width = image.width
        var height = image.height

        val bitmapRatio = width.toFloat() / height.toFloat()
        if (bitmapRatio > 1) {
            width = maxSize
            height = (width / bitmapRatio).toInt()
        } else {
            height = maxSize
            width = (height * bitmapRatio).toInt()
        }
        return Bitmap.createScaledBitmap(image, width, height, true)
    }

    /**
     * reduces the size of the image
     *
     * @param image  uncompressed image
     * @param reduce how much to reduce in kb
     * @return new bitmap
     */
    fun reduceBitmap(image: Bitmap, reduce: Int): Bitmap {
        var width = image.width
        var height = image.height

        val bitmapRatio = width.toFloat() / height.toFloat()
        if (bitmapRatio > 1) {
            width -= reduce
            height = (width / bitmapRatio).toInt()
        } else {
            height -= reduce
            width = (height * bitmapRatio).toInt()
        }
        return if (width > 0 && height > 0)
            Bitmap.createScaledBitmap(image, width, height, true)
        else
            image
    }

    /**
     * Converts and bitmap image to circular bitmap
     *
     * @param bitmap_image bitmap image
     */
    fun getCircleBitmap(context: Context, bitmap_image: Bitmap): Bitmap {
        var output = BitmapFactory.decodeResource(context.resources, R.drawable.tranparent_view)
        try {
            val sice = Math.min(bitmap_image.width, bitmap_image.height)
            val bitmap = ThumbnailUtils.extractThumbnail(bitmap_image, sice, sice)
            output = Bitmap.createBitmap(bitmap.width, bitmap.height, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(output)

            val color = -0x10000
            val paint = Paint()
            val rect = Rect(0, 0, bitmap.width, bitmap.height)
            val rectF = RectF(rect)

            paint.isAntiAlias = true
            paint.isDither = true
            paint.isFilterBitmap = true
            canvas.drawARGB(0, 0, 0, 0)
            paint.color = color
            canvas.drawOval(rectF, paint)

            paint.color = Color.BLUE
            paint.style = Paint.Style.STROKE
            paint.strokeWidth = 4.toFloat()
            paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
            canvas.drawBitmap(bitmap, rect, rect, paint)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return output
    }

    /**
     * Get drawable and generate bitmap
     *
     * @param context context of acitivity
     * @param resId   resId of Drawable
     * @return Bitmap
     */
    fun toBitmap(context: Context, @DrawableRes resId: Int): Bitmap? {
        val drawable = getDrawable(context, resId)
        return toBitmap(drawable)
    }

    /**
     * Pass the drawable to get bitmap
     *
     * @param drawable drawable
     * @return Bitmam
     *
     *
     * In case of OutofMemoryException you will get "null"
     */
    fun toBitmap(drawable: Drawable): Bitmap? {
        try {
            return (drawable as BitmapDrawable).bitmap
        } catch (e: OutOfMemoryError) {
            return null
        }

    }

    /**
     * Method to get Drawable
     *
     * @param context context to get drawable
     * @param resId   id of drawable like R.drawable.'drawable name'
     * @return drawable
     */
    fun getDrawable(context: Context, @DrawableRes resId: Int): Drawable {
        var drawable = ContextCompat.getDrawable(context, resId)
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            drawable = DrawableCompat.wrap(drawable!!).mutate()
        }
        return drawable!!
    }


    /**
     * Get id of drawable from name
     *
     * @param context context of activity
     * @param resName name on which id is to found
     * @return drawable int id
     * In case of exception it will return -1
     */
    fun getResourceId(context: Context, resName: String): Int {
        try {
            return context.resources.getIdentifier(
                resName, "drawable", context.packageName)
        } catch (ignored: Exception) {
        }

        return -1
    }

    /**
     * Get id of drawable from name
     *
     * @param context context of activity
     * @param resId   name on which id is to found
     * @return drawable int id
     * In case of exception it will return -1
     */
    operator fun get(context: Context, @DrawableRes resId: Int): Drawable {
        var drawable = getDrawable(context, resId)
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            drawable = DrawableCompat.wrap(drawable).mutate()
        }
        return drawable
    }

    /**
     * Get tinted drawable
     *
     * @param context context of activity
     * @param color   resource color id
     * @param resId   of drawable
     * @return tinted drawable
     */
    fun getTintedDrawable(context: Context, @DrawableRes resId: Int, @ColorInt color: Int): Drawable? {
        val drawable = get(context, resId)
        return getTintedDrawable(drawable, color)
    }

    /**
     * Get tinted drawable
     *
     * @param color    resource color id
     * @param drawable drawable file
     * @return tinted drawable
     * return **null** in case of Out of memory exception
     */
    fun getTintedDrawable(drawable: Drawable, @ColorInt color: Int): Drawable? {
        try {
            DrawableCompat.setTint(drawable, color)
            return drawable.mutate()
        } catch (e: OutOfMemoryError) {
            return null
        }

    }

    fun getResizedDrawable(context: Context, drawable: Drawable, sizeInDp: Float): Drawable? {
        try {
            val size = Math.round(Andy.converter.toPixel(context, sizeInDp))

            val bitmap = Bitmap.createBitmap(drawable.intrinsicWidth,
                drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)
            drawable.setBounds(0, 0, canvas.width, canvas.height)
            drawable.draw(canvas)

            return BitmapDrawable(context.resources,
                Bitmap.createScaledBitmap(bitmap, size, size, true))
        } catch (e: OutOfMemoryError) {
            return null
        }

    }

    fun toDrawable(context: Context, bitmap: Bitmap): Drawable? {
        try {
            return BitmapDrawable(context.resources, bitmap)
        } catch (e: OutOfMemoryError) {
            return null
        }

    }

    /**
     * Call this method to resize an image to a specified width and height.
     *
     * @param targetWidth  The width to resize to.
     * @param targetHeight The height to resize to.
     * @returns The resized image as a Bitmap.
     */
    fun resizeImage(imageData: ByteArray, targetWidth: Int, targetHeight: Int): Bitmap {
        // First decode with inJustDecodeBounds=true to check dimensions
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeByteArray(imageData, 0, imageData.size, options)

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, targetWidth, targetHeight)

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false
        val reducedBitmap = BitmapFactory.decodeByteArray(imageData, 0, imageData.size, options)

        return Bitmap.createScaledBitmap(reducedBitmap, targetWidth, targetHeight, false)
    }

    /**
     * Resize image by maintaining the aspect Ration
     *
     * @param imageData         byte[] of image
     * @param shorterSideTarget size
     * @return image
     */
    fun resizeImageMaintainAspectRatio(imageData: ByteArray, shorterSideTarget: Int): Bitmap {
        val dimensions = getDimensions(imageData)

        // Determine the aspect ratio (width/height) of the image
        val imageWidth = dimensions.first
        val imageHeight = dimensions.second
        val ratio = dimensions.first as Float / dimensions.second

        val targetWidth: Int
        val targetHeight: Int

        // Determine portrait or landscape
        if (imageWidth > imageHeight) {
            // Landscape image. ratio (width/height) is > 1
            targetHeight = shorterSideTarget
            targetWidth = Math.round(shorterSideTarget * ratio)
        } else {
            // Portrait image. ratio (width/height) is < 1
            targetWidth = shorterSideTarget
            targetHeight = Math.round(shorterSideTarget / ratio)
        }
        return resizeImage(imageData, targetWidth, targetHeight)
    }

    /**
     * Get Dimentions of ImageUtils
     *
     * @param imageData pass byte[] image
     * @return Pair object
     */
    fun getDimensions(imageData: ByteArray): Pair<Int, Int> {
        // Use BitmapFactory to decode the image
        val options = BitmapFactory.Options()

        // Only decode the bounds of the image, not the whole image, to get the dimensions
        options.inJustDecodeBounds = true
        BitmapFactory.decodeByteArray(imageData, 0, imageData.size, options)

        return Pair(options.outWidth, options.outHeight)
    }

    /**
     * Calculate sample size of ImageUtils
     *
     * @param options   bitmap factory options
     * @param reqWidth  width which is req.
     * @param reqHeight height which is req.
     * @return int sample size of image
     */
    fun calculateInSampleSize(options: BitmapFactory.Options, reqWidth: Int, reqHeight: Int): Int {
        // Raw height and width of image
        val height = options.outHeight
        val width = options.outWidth
        var inSampleSize = 1

        if (height > reqHeight || width > reqWidth) {

            val halfHeight = height / 2
            val halfWidth = width / 2

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while (halfHeight / inSampleSize > reqHeight && halfWidth / inSampleSize > reqWidth) {
                inSampleSize *= 2
            }
        }

        return inSampleSize
    }


    /**
     * Tiles the background of a view
     *
     * @param ctx         context of activity
     * @param viewId      int view id
     * @param resIdOfTile drawable res_id of tile background
     * @throws NullPointerException if view is null
     */
    fun tileBackground(ctx: Context, viewId: Int, resIdOfTile: Int) {

        try {
            // Tiling the background.
            val bmp = BitmapFactory.decodeResource(ctx.resources, resIdOfTile)
            val bitmapDrawable = BitmapDrawable(ctx.resources, bmp)
            bitmapDrawable.setTileModeXY(Shader.TileMode.REPEAT, Shader.TileMode.REPEAT)
            val view = (ctx as Activity).findViewById<View>(viewId)

            if (view == null) {
                throw NullPointerException("View to which the tile has to be applied should not be null")
            } else {
                setBackground(view, bitmapDrawable)
            }
        } catch (e: Exception) {
            Andy.log.printInAllLogFormat("Tile Background", "#tileBackground Exception while tiling the background of the view")
        }

    }

    /**
     * Tiles the background of a layout.
     *
     * @param ctx         context of activity
     * @param layoutId      int view id
     * @param resIdOfTile drawable res_id of tile background
     * @throws NullPointerException if view is null
     */
    fun tileBackground(ctx: Context, layoutId: Int, viewToTileBg: View, resIdOfTile: Int) {
        try {
            // Tiling the background.
            val bmp = BitmapFactory.decodeResource(ctx.resources, resIdOfTile)
            // deprecated constructor
            // BitmapDrawable bitmapDrawable = new BitmapDrawable(bmp);
            val bitmapDrawable = BitmapDrawable(ctx.resources, bmp)
            bitmapDrawable.setTileModeXY(Shader.TileMode.REPEAT, Shader.TileMode.REPEAT)
            val view = viewToTileBg.findViewById<View>(layoutId)

            if (view != null) {
                setBackground(view, bitmapDrawable)
            }

        } catch (e: Exception) {
            Andy.log.printInAllLogFormat("Tile Background", "Exception while tiling the background of the view")
        }

    }

}