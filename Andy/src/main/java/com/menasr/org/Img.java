package com.menasr.org;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ThumbnailUtils;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import static android.support.v4.view.ViewCompat.setBackground;

public class Img {

    /**
     * Method to generate Bitmap
     *
     * @param context  context of Activity
     * @param layoutId name of layout like <b>R.layout.'layout name'</b>
     * @return bitmap
     */
    public Bitmap generateBitmapFromLayout(Context context, int layoutId) {
        Bitmap bitmap = null;
        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //Inflate the layout into a view and configure it the way you like
        RelativeLayout view = new RelativeLayout(context);
        try {
            mInflater.inflate(layoutId, view, true);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Provide it with a layout params. It should necessarily be wrapping the
        //content as we not really going to have a parent for it.
        view.setLayoutParams(new ViewGroup.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT));

        //Pre-measure the view so that height and width don't remain null.
        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));

        //Assign a size and position to the view and all of its descendants
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());

        //Create the bitmap
        bitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(), Bitmap.Config.ARGB_8888);

        //Create a canvas with the specified bitmap to draw into
        Canvas c = new Canvas(bitmap);

        //Render this view (and all of its children) to the given Canvas
        view.draw(c);

        return bitmap;
    }

    /**
     * reduces the size of the image
     *
     * @param image   uncompressed image
     * @param maxSize max size of image to compress
     * @return new bitmap
     */
    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }

    /**
     * reduces the size of the image
     *
     * @param image  uncompressed image
     * @param reduce how much to reduce in kb
     * @return new bitmap
     */
    public Bitmap reduceBitmap(Bitmap image, int reduce) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width -= reduce;
            height = (int) (width / bitmapRatio);
        } else {
            height -= reduce;
            width = (int) (height * bitmapRatio);
        }
        if (width > 0 && height > 0)
            return Bitmap.createScaledBitmap(image, width, height, true);
        else
            return image;
    }

    /**
     * Converts and bitmap image to circular bitmap
     *
     * @param bitmap_image bitmap image
     **/
    public Bitmap getCircleBitmap(Context context, Bitmap bitmap_image) {
        Bitmap output = BitmapFactory.decodeResource(context.getResources(), R.drawable.tranparent_view);
        try {
            int sice = Math.min((bitmap_image.getWidth()), (bitmap_image.getHeight()));
            Bitmap bitmap = ThumbnailUtils.extractThumbnail(bitmap_image, sice, sice);
            output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(output);

            final int color = 0xffff0000;
            final Paint paint = new Paint();
            final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
            final RectF rectF = new RectF(rect);

            paint.setAntiAlias(true);
            paint.setDither(true);
            paint.setFilterBitmap(true);
            canvas.drawARGB(0, 0, 0, 0);
            paint.setColor(color);
            canvas.drawOval(rectF, paint);

            paint.setColor(Color.BLUE);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth((float) 4);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            canvas.drawBitmap(bitmap, rect, rect, paint);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return output;
    }

    /**
     * Get drawable and generate bitmap
     *
     * @param context context of acitivity
     * @param resId   resId of Drawable
     * @return Bitmap
     */
    @Nullable
    public Bitmap toBitmap(@NonNull Context context, @DrawableRes int resId) {
        Drawable drawable = getDrawable(context, resId);
        return toBitmap(drawable);
    }

    /**
     * Pass the drawable to get bitmap
     *
     * @param drawable drawable
     * @return Bitmam
     * <p>
     * In case of OutofMemoryException you will get "null"
     */
    @Nullable
    public Bitmap toBitmap(@NonNull Drawable drawable) {
        try {
            return ((BitmapDrawable) drawable).getBitmap();
        } catch (OutOfMemoryError e) {
            return null;
        }
    }

    /**
     * Method to get Drawable
     *
     * @param context context to get drawable
     * @param resId   id of drawable like R.drawable.'drawable name'
     * @return drawable
     */
    @NonNull
    public Drawable getDrawable(@NonNull Context context, @DrawableRes int resId) {
        Drawable drawable = ContextCompat.getDrawable(context, resId);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            drawable = (DrawableCompat.wrap(drawable)).mutate();
        }
        return drawable;
    }


    /**
     * Get id of drawable from name
     *
     * @param context context of activity
     * @param resName name on which id is to found
     * @return drawable int id
     * In case of exception it will return -1
     */
    public int getResourceId(@NonNull Context context, String resName) {
        try {
            return context.getResources().getIdentifier(
                    resName, "drawable", context.getPackageName());
        } catch (Exception ignored) {
        }
        return -1;
    }

    /**
     * Get id of drawable from name
     *
     * @param context context of activity
     * @param resId   name on which id is to found
     * @return drawable int id
     * In case of exception it will return -1
     */
    @NonNull
    public Drawable get(@NonNull Context context, @DrawableRes int resId) {
        Drawable drawable = getDrawable(context, resId);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            drawable = (DrawableCompat.wrap(drawable)).mutate();
        }
        return drawable;
    }

    /**
     * Get tinted drawable
     *
     * @param context context of activity
     * @param color   resource color id
     * @param resId   of drawable
     * @return tinted drawable
     */
    @Nullable
    public Drawable getTintedDrawable(@NonNull Context context, @DrawableRes int resId, @ColorInt int color) {
        Drawable drawable = get(context, resId);
        return getTintedDrawable(drawable, color);
    }

    /**
     * Get tinted drawable
     *
     * @param color    resource color id
     * @param drawable drawable file
     * @return tinted drawable
     * return <b>null</b> in case of Out of memory exception
     */
    @Nullable
    public Drawable getTintedDrawable(@NonNull Drawable drawable, @ColorInt int color) {
        try {
            DrawableCompat.setTint(drawable, color);
            return drawable.mutate();
        } catch (OutOfMemoryError e) {
            return null;
        }
    }

    @Nullable
    public Drawable getResizedDrawable(@NonNull Context context, @NonNull Drawable drawable, float sizeInDp) {
        try {
            int size = Math.round(Andy.CONVERTER.toPixel(context, sizeInDp));

            Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                    drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);

            return new BitmapDrawable(context.getResources(),
                    Bitmap.createScaledBitmap(bitmap, size, size, true));
        } catch (OutOfMemoryError e) {
            return null;
        }
    }

    @Nullable
    public Drawable toDrawable(@NonNull Context context, @NonNull Bitmap bitmap) {
        try {
            return new BitmapDrawable(context.getResources(), bitmap);
        } catch (OutOfMemoryError e) {
            return null;
        }
    }

    /**
     * Call this method to resize an image to a specified width and height.
     *
     * @param targetWidth  The width to resize to.
     * @param targetHeight The height to resize to.
     * @returns The resized image as a Bitmap.
     */
    public Bitmap resizeImage(byte[] imageData, int targetWidth, int targetHeight) {
        // First decode with inJustDecodeBounds=true to check dimensions
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(imageData, 0, imageData.length, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, targetWidth, targetHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        Bitmap reducedBitmap = BitmapFactory.decodeByteArray(imageData, 0, imageData.length, options);
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(reducedBitmap, targetWidth, targetHeight, false);

        return resizedBitmap;
    }

    /**
     * Resize image by maintaining the aspect Ration
     *
     * @param imageData         byte[] of image
     * @param shorterSideTarget size
     * @return image
     */
    public Bitmap resizeImageMaintainAspectRatio(byte[] imageData, int shorterSideTarget) {
        Pair<Integer, Integer> dimensions = getDimensions(imageData);

        // Determine the aspect ratio (width/height) of the image
        int imageWidth = dimensions.first;
        int imageHeight = dimensions.second;
        float ratio = (float) dimensions.first / dimensions.second;

        int targetWidth;
        int targetHeight;

        // Determine portrait or landscape
        if (imageWidth > imageHeight) {
            // Landscape image. ratio (width/height) is > 1
            targetHeight = shorterSideTarget;
            targetWidth = Math.round(shorterSideTarget * ratio);
        } else {
            // Portrait image. ratio (width/height) is < 1
            targetWidth = shorterSideTarget;
            targetHeight = Math.round(shorterSideTarget / ratio);
        }
        return resizeImage(imageData, targetWidth, targetHeight);
    }

    /**
     * Get Dimentions of Image
     *
     * @param imageData pass byte[] image
     * @return Pair object
     */
    public Pair<Integer, Integer> getDimensions(byte[] imageData) {
        // Use BitmapFactory to decode the image
        BitmapFactory.Options options = new BitmapFactory.Options();

        // Only decode the bounds of the image, not the whole image, to get the dimensions
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(imageData, 0, imageData.length, options);

        return new Pair<Integer, Integer>(options.outWidth, options.outHeight);
    }

    /**
     * Calculate sample size of Image
     *
     * @param options   bitmap factory options
     * @param reqWidth  width which is req.
     * @param reqHeight height which is req.
     * @return int sample size of image
     */
    public int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }


    /**
     * Tiles the background of a view
     *
     * @param ctx         context of activity
     * @param viewId      int view id
     * @param resIdOfTile drawable res_id of tile background
     * @throws NullPointerException if view is null
     */
    public void tileBackground(Context ctx, int viewId, int resIdOfTile) {

        try {
            // Tiling the background.
            Bitmap bmp = BitmapFactory.decodeResource(ctx.getResources(), resIdOfTile);
            BitmapDrawable bitmapDrawable = new BitmapDrawable(ctx.getResources(), bmp);
            bitmapDrawable.setTileModeXY(Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
            View view = ((Activity) ctx).findViewById(viewId);

            if (view == null) {
                throw new NullPointerException("View to which the tile has to be applied should not be null");
            } else {
                setBackground(view, bitmapDrawable);
            }
        } catch (Exception e) {
            Andy.LOG.printInAllLogFormat("Tile Background", "#tileBackground Exception while tiling the background of the view");
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
    public static void tileBackground(Context ctx, int layoutId, View viewToTileBg, int resIdOfTile) {
        try {
            // Tiling the background.
            Bitmap bmp = BitmapFactory.decodeResource(ctx.getResources(), resIdOfTile);
            // deprecated constructor
            // BitmapDrawable bitmapDrawable = new BitmapDrawable(bmp);
            BitmapDrawable bitmapDrawable = new BitmapDrawable(ctx.getResources(), bmp);
            bitmapDrawable.setTileModeXY(Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
            View view = viewToTileBg.findViewById(layoutId);

            if (view != null) {
                setBackground(view, bitmapDrawable);
            }

        } catch (Exception e) {
            Andy.LOG.printInAllLogFormat("Tile Background", "Exception while tiling the background of the view");
        }
    }
}
