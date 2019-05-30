package com.menasr.org;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

/** This is custom Toast class*/
public class Tost {
    Context context = Andy.getContext();

    /**
     * @param stringId pass String id like <b>R.string.'String name'</b>
     */
    public void toastShort(@NonNull int stringId) {
        Toast.makeText(context, Andy.RES.getString(stringId), Toast.LENGTH_SHORT).show();
    }

    /**
     * @param msg "Message" pass String id like <b>R.string.'String name'</b>
     */
    public void toastShort(@NonNull String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * @param stringId pass String id like <b>R.string.'String name'</b>
     */
    public void toastLong(@NonNull int stringId) {
        Toast.makeText(context, Andy.RES.getString(stringId), Toast.LENGTH_LONG).show();
    }

    /**
     * @param msg "Message" pass String id like <b>R.string.'String name'</b>
     */
    public void toastLong(@NonNull String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }


}
