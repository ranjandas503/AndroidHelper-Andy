package com.menasr.andy;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

public class PermissionManager {

    private PermissionListener permissionListener = null;
    private ConfirmationListener confirmationListener = null;
    private Boolean canRequestAgain = false;
    private Boolean closeIfForeverDenied = false;
    private Boolean goToSetting = false;
    private Boolean confirmation = false;

    private Activity activity;
    private List<String> listPermissionsNeeded;

    public PermissionManager(Activity activity, List<String> listPermissionsNeeded) {
        this.activity = activity;
        if (listPermissionsNeeded != null)
            this.listPermissionsNeeded = listPermissionsNeeded;
        else listPermissionsNeeded = allPermissionsInApp();
    }

    private final int PERMISSION_REQ_CODE = 1234;

    //getting all permissions from manifest
    private List<String> allPermissionsInApp() {
        List<String> per = new ArrayList<>();
        try {
            PackageManager pm = activity.getApplicationContext().getPackageManager();
            PackageInfo pi = pm.getPackageInfo(
                    activity.getApplicationContext().getPackageName(),
                    PackageManager.GET_PERMISSIONS
            );
            String[] permissionInfo = pi.requestedPermissions;
            per.addAll(Arrays.asList(permissionInfo));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return per;
    }

    /**
     * Responsible for confirmation callback if user forever-denys all permissions,
     * You can show your own dialog or do some more stuff you want.
     *
     * @param confirmation <b>false</b> to get callback of confirmation,default is <b>true</b>
     *                     Note:- It also requires [goToSetting] value <b>true</b>. So, make sure you pass true in the method
     */
    public PermissionManager confirmationBeforeSetting(Boolean confirmation) {
        this.confirmation = confirmation;
        return this;
    }


    /**
     * Responsible for re-requesting permission after user deny(<b>not forever deny</b>) the request
     *
     * @param canRequestAgain <b>true</b> to request again for permission,default is <b>false</b>
     */
    public PermissionManager canRequestAfterDeny(Boolean canRequestAgain) {
        this.canRequestAgain = canRequestAgain;
        return this;
    }

    /**
     * Responsible for going back, if user forever denies to ask for permission
     *
     * @param closeIfForeverDenied <b>true</b> to close, default is <b>false</b>
     */
    public PermissionManager canCloseActivityIfDeniedForever(Boolean closeIfForeverDenied) {
        this.closeIfForeverDenied = closeIfForeverDenied;
        return this;
    }

    /**
     * Responsible for navigating user to setting screen of the app, where user can provide permission again
     *
     * @param goToSetting <b>true</b> to go to setting,default is <b>false</b>
     */
    public PermissionManager navigateToSettingForForeverDeniedPermission(Boolean goToSetting) {
        this.goToSetting = goToSetting;
        return this;
    }

    /**
     * Responsible for callback according to accept or deny permission
     *
     * @param permissionListener add this [PermissionListener] for callback, and for details please
     *                           refer to the [PermissionListener] class definition i.e., by (ctrl+Q)
     */
    public PermissionManager addPermissionListener(PermissionListener permissionListener) {
        this.permissionListener = permissionListener;
        return this;
    }

    /**
     * Responsible for callback according to accept or deny permission
     *
     * @param confirmationListener add this [ConfirmationListener] for callback, and for details please
     *                             refer to the [ConfirmationListener] class definition i.e., by (ctrl+Q)
     */
    public PermissionManager addConfirmationListener(ConfirmationListener confirmationListener) {
        this.confirmationListener = confirmationListener;
        return this;
    }

    /**
     * To initiate checking permission
     * just pass the context of application
     */
    public Boolean checkAndRequestPermissions() {
        if (Build.VERSION.SDK_INT >= 23) {
            List<String> listPermissionsAssign = new ArrayList<>();
            for (String per : listPermissionsNeeded) {
                if (ContextCompat.checkSelfPermission(
                        activity.getApplicationContext(),
                        per
                ) != PackageManager.PERMISSION_GRANTED
                ) {
                    listPermissionsAssign.add(per);
                }
            }

            if (listPermissionsAssign.size() > 0) {
                ActivityCompat.requestPermissions(
                        activity,
                        (String[]) listPermissionsAssign.toArray(),
                        PERMISSION_REQ_CODE
                );
                return false;
            }
        }
        return true;
    }

    //Method which is responsible to navigate to setting
    public void navigateToSettings() {
        Intent intent = new Intent(
                Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                Uri.fromParts("package", activity.getPackageName(), null)
        );
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }

    /**
     * Responsible for checking the result which is provided by activity,
     * and taking necessary actions according to it
     *
     * @param requestCode  pass your request code which you get in [Activity.onRequestPermissionsResult]
     * @param permissions  pass your permissions string list which you get in [Activity.onRequestPermissionsResult]
     * @param grantResults pass your grantResults int array which you get in [Activity.onRequestPermissionsResult]
     */
    public void checkResult(int requestCode, final String[] permissions, int[] grantResults) {
        if (requestCode == PERMISSION_REQ_CODE) {
            HashMap<String, Integer> perms = new HashMap<>();
            for (String permission : listPermissionsNeeded) {
                perms.put(permission,PackageManager.PERMISSION_GRANTED);
            }
            if (grantResults.length > 0) {
                for (int i = 0; i < permissions.length; i++)
                    perms.put(permissions[i],grantResults[i]);

                boolean isAllGranted = true;

                for (String permission : listPermissionsNeeded) {
                    if (perms.get(permission) == PackageManager.PERMISSION_DENIED) {
                        isAllGranted = false;
                        break;
                    }
                }

                if (isAllGranted) {
                    //User has granted all the permissions,
                    if (permissionListener != null)
                        permissionListener.onAllPermissionGranted();

                } else {
                    boolean shouldRequest = false;
                    List<String> deniedPermissions = new ArrayList<>();
                    List<String> foreverDeniedPermissions = new ArrayList<>();

                    for (String permission : listPermissionsNeeded) {
                        if (ActivityCompat.shouldShowRequestPermissionRationale(
                                activity,
                                permission
                        )
                        ) {
                            if (perms.get(permission) == PackageManager.PERMISSION_DENIED)
                                deniedPermissions.add(permission);

                            shouldRequest = deniedPermissions.size() > 0;
                        } else {
                            if (perms.get(permission) != PackageManager.PERMISSION_GRANTED)
                                foreverDeniedPermissions.add(permission);
                        }
                    }
                    if (shouldRequest) {
                        if (canRequestAgain)
                            checkAndRequestPermissions();
                    } else {
                        if (closeIfForeverDenied)
                            activity.onBackPressed();

                        if (goToSetting && !activity.isFinishing()) {
                            if (confirmation) {
                                if (confirmationListener != null) {
                                    confirmationListener.confirmationBeforeSetting(this::navigateToSettings);
                                }
                            } else navigateToSettings();
                        }
                    }

                    //clearing all fields which are not preset in provided list(i.e., manifest permissions)
                    deniedPermissions.retainAll(listPermissionsNeeded);
                    foreverDeniedPermissions.retainAll(listPermissionsNeeded);

                    //sending list of denied permissions
                    if (permissionListener != null)
                        permissionListener.onPermissionDeny(
                                deniedPermissions,
                                foreverDeniedPermissions
                        );
                }
            }
        }
    }

    /**
     * This interface is responsible for providing permissions according to user selection,
     * if you have attached listener for callback i.e.,this method [addPermissionListener]
     */
    interface PermissionListener {
        /**
         * This method will be invoked when user accepts all the permission
         */
        void onAllPermissionGranted();

        /**
         * This method will be invoked when you user deny/forever-deny permissions,
         * You will get a string list of denied permissions and forever denied permissions
         */
        void onPermissionDeny(
                List<String> deniedPermissions,
                List<String> foreverDeniedPermissions
        );
    }

    /**
     * This is for confirmation callback only, i.e.[addConfirmationListener], if you want permissions callback to, then refer
     * [addPermissionListener] which provided [PermissionListener]
     */
    interface ConfirmationListener {
        /**
         * This will will be invoked when user forever-deny the permission and you want to show any
         * confirmation before sending user to App settings
         *
         * @param function it will return [navigateToSettings] function,
         *                 you just have to invoke it like(<b>function.invoke() for kotlin and run/start for java</b>)
         *                 Note:- Please refer [goToSetting] and [confirmationBeforeSetting] also,
         *                 as it depends on it for callback
         */
        void confirmationBeforeSetting(Runnable function);
    }
}
