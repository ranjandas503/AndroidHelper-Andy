package com.menasr.androidhelper

import android.content.Context
import android.support.annotation.NonNull

/**@param appContext pass Application context*/
class Andy(@NonNull appContext: Context) : AndyException() {

    var res: ResourceLoader

    init {
        appContext.let { res = ResourceLoader(appContext) }
    }


}