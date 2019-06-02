package com.menasr.andy;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;

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
    public static AndyResources RES;
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
    public static Layout LAYOUT;
    public static AndyNumber NUMBER;

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

    //Method to initialize things
    private void initializeThings() {
        //initializing metrices
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(METRICS);

        DATE_TIME = new DateTime();   //initialized Date_Time Object
        TOAST = new AndyToast(context.getApplicationContext());           //initialized Toast Object
        SNACKBAR = new Snack();  //initialized Snack Object
        FILES = new Files();  //initialized FILES Object
        RES = new AndyResources(context.getApplicationContext());  //initialized Resources Object
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
        LAYOUT = new Layout();
        NUMBER=new AndyNumber();
    }
}
