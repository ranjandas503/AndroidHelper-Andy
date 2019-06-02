package com.menasr.andy;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.LinearLayout;

import java.security.SignatureException;
import java.util.LinkedList;
import java.util.List;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class Andy extends Application {
    @SuppressLint("StaticFieldLeak")
    private static Andy instance;
    @SuppressLint("StaticFieldLeak")
    private static Context context;

    public static DisplayMetrics METRICS = new DisplayMetrics();


    public static DateTime DATE_TIME;
    @SuppressLint("StaticFieldLeak")
    public static AndyToast TOAST;
    public static Snack SNACKBAR;
    public static Files FILES;
    @SuppressLint("StaticFieldLeak")
    public static Res RES;
    public static Img IMAGE;
    public static Internet INTERNET;
    public static DeviceUtils DEVICE;
    public static LogUtil LOG;
    public static AndyWindow WINDOW;
    public static Colour COLOR;
    public static NavigationBar NAV_BAR;
    public static StatusBar STATUS_BAR;
    public static Converter CONVERTER;
    public static StringHelper STRING;
    public static SoftInput SOFT_INPUT;
    public static AndyToolbar TOOLBAR;
    public static AndyAnimation ANIMATION;
    public static YouTubeUtils YOU_TUBE;
    public static Media MEDIA;
    public static IntentUtil INTENT;
    public static RegexConst REGEX;
    public static ShellUtils SHELL;
    public static Maps MAPS;
    public static Algorithm ALGORITHM;

    private Andy(Context context) {
        Andy.context = context;
        initializeThings();
    }

    protected static Context getContext() {
        return context;
    }

    /**
     * Initialize it for first time somewhere, just once
     *
     * @param context context of Activity
     */
    public static void init(@NonNull Context context) {
        if (instance == null)
            instance = new Andy(context);
    }

    /*public static Andy getInstance() {
        return instance;
    }*/

    //Method to initialize things
    private void initializeThings() {
        //initializing metrices
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(METRICS);

        DATE_TIME = new DateTime();   //initialized Date_Time Object
        TOAST = new AndyToast(context.getApplicationContext());           //initialized Toast Object
        SNACKBAR = new Snack();  //initialized Snack Object
        FILES = new Files();  //initialized FILES Object
        RES = new Res(context.getApplicationContext());  //initialized Resources Object
        IMAGE = new Img();  //initialized Image Object
        INTERNET = new Internet();  //initialized Internet Object
        DEVICE = new DeviceUtils();  //initialized Internet Object
        LOG = new LogUtil();  //initialized LogUtil Object
        WINDOW = new AndyWindow();
        COLOR = new Colour();
        NAV_BAR = new NavigationBar();
        STATUS_BAR = new StatusBar();
        CONVERTER = new Converter();
        STRING = new StringHelper();
        SOFT_INPUT = new SoftInput();
        TOOLBAR = new AndyToolbar();
        ANIMATION = new AndyAnimation();
        YOU_TUBE = new YouTubeUtils();
        MEDIA = new Media();
        INTENT = new IntentUtil();
        REGEX = new RegexConst();
        SHELL = new ShellUtils();
        MAPS = new Maps();
        ALGORITHM = new Algorithm();
    }

    /**
     * This method filter the value in array, which is two frequent
     *
     * @param input  input array to filter
     * @param output output array to filter
     * @param filter factor to filter input and output(best is 0.05 to 0.25)
     * @return output array on basis of filter
     */
    public static float[] lowPassFilter(float[] input, float[] output, float filter) {
        if (output == null) return input;

        for (int i = 0; i < input.length; i++) {
            output[i] = output[i] + filter * (input[i] - output[i]);
        }
        return output;
    }

    /**
     * Function to get Progress percentage
     *
     * @param currentDuration current progress duration
     * @param totalDuration total duration
     */
    public static int getProgressPercentage(long currentDuration, long totalDuration) {
        Double percentage = (double) 0;

        long currentSeconds = (int) (currentDuration / 1000);
        long totalSeconds = (int) (totalDuration / 1000);

        // calculating percentage
        percentage = (((double) currentSeconds) / totalSeconds) * 100;
        return percentage.intValue();
    }

    /**
     * Method which provides divider between Tab items
     *
     * @param tabLayout          just pass your tab layout
     * @param color              int color of line
     * @param width              width of margin
     * @param height             height of margin
     * @param paddingFromDivider padding from divider, it will applied to both side of margin
     */
    public static void addMarginInTabLayout(TabLayout tabLayout, int color, int width, int height,
                                            int paddingFromDivider) {
        LinearLayout linearLayout = (LinearLayout) tabLayout.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(color);
        drawable.setSize(width, height);
        linearLayout.setDividerPadding(paddingFromDivider);
        linearLayout.setDividerDrawable(drawable);
    }

    /**
     * Get base context from a view
     *
     * @param view view from which baseContext is required
     * @return context object
     */
    @NonNull
    protected static Context getBaseContext(@NonNull View view) {
        Context context = view.getContext();
        if (view.getContext() instanceof ContextThemeWrapper) {
            context = ((ContextThemeWrapper) view.getContext()).getBaseContext();
        }
        return context;
    }

    /***
     * Computes RFC 2104-compliant HMAC signature. This can be used to sign the Amazon S3
     * request urls
     *
     * @param data The data to be signed.
     * @param key  The signing key.
     * @return The Base64-encoded RFC 2104-compliant HMAC signature.
     * @throws SignatureException when signature generation fails
     */
    public static String getHMac(String data, String key) throws SignatureException {

        if (data == null) {
            throw new NullPointerException("Data to be signed cannot be null");
        }

        String result = null;
        try {

            final String HMAC_SHA1_ALGORITHM = "HmacSHA1";

            // get an hmac_sha1 key from the raw key bytes
            SecretKeySpec signingKey = new SecretKeySpec(key.getBytes(), HMAC_SHA1_ALGORITHM);

            // get an hmac_sha1 Mac instance &
            // initialize with the signing key
            Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
            mac.init(signingKey);

            // compute the hmac on input data bytes
            byte[] digest = mac.doFinal(data.getBytes());

            if (digest != null) {
                // Base 64 Encode the results
                result = Base64.encodeToString(digest, Base64.NO_WRAP);
            }

        } catch (Exception e) {
            throw new SignatureException("Failed to generate HMAC : " + e.getMessage());
        }

        return result;
    }

    /**
     * set recyclerview's default vertial scrolling property
     * it will only set <b>layoutmanager</b> and <b>ItemAnimator</b> to default you have to set
     * set adapter later
     *
     * @param recyclerView pass your recyclerview
     */
    public static void setLayoutListVertical(RecyclerView recyclerView) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(recyclerView.getContext());
        recyclerView.setLayoutManager(linearLayoutManager); // set LayoutManager to RecyclerView
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    /**
     * set recyclerview's default Grid scrolling property
     * it will only set <b>layoutmanager</b> and <b>ItemAnimator</b> to default you have to set
     * set adapter later
     *
     * @param recyclerView pass your recyclerview
     * @param itemsPerRow  send no. of items in rows you want
     */
    public static void setLayoutGridVertical(RecyclerView recyclerView, int itemsPerRow) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(recyclerView.getContext(), itemsPerRow);
        recyclerView.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    /**
     * set recyclerview's default Horizontal scrolling property
     * it will only set <b>layoutmanager</b> and <b>ItemAnimator</b> to default you have to set
     * set adapter later
     *
     * @param recyclerView pass your recyclerview
     */
    public static void setLayoutListHorizontal(RecyclerView recyclerView) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(recyclerView.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL); // set Horizontal Orientation
        recyclerView.setLayoutManager(linearLayoutManager); // set LayoutManager to RecyclerView
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    /**
     * set recyclerview's Grid scrolling property to Horizontal
     * it will only set <b>layoutmanager</b> and <b>ItemAnimator</b> to default you have to set
     * set adapter later
     *
     * @param recyclerView pass your recyclerview
     * @param itemsPerRow  send no. of items in rows you want
     */
    public static void setLayoutGridHorizontal(RecyclerView recyclerView, int itemsPerRow) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(recyclerView.getContext(), itemsPerRow);
        gridLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL); // set Horizontal Orientation
        recyclerView.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }


    /**
     * Get factorial of a number
     */
    public static long getFatorial(int number) {
        if (number == 0)
            return 0;
        else if (number == 1)
            return 1;
        else return number * getFatorial(number - 1);
    }

    /**
     * Get Fibonacci series upto total no of digits
     */
    public static List<Integer> getFibonacci(int totalDigits) {
        List<Integer> allFibo = new LinkedList<>();
        int t1 = 0, t2 = 1;
        for (int i = 1; i <= totalDigits; ++i) {
            allFibo.add(t1);

            int sum = t1 + t2;
            t1 = t2;
            t2 = sum;
        }
        return allFibo;
    }
}
