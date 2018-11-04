package com.bbbond.moduleCommon.utils

import android.util.Log
import com.bbbond.moduleCommon.BuildConfig

object LogUtil {

    fun d(tag: String, msg: String?) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, msg)
        }
    }

    fun e(tag: String, errMsg: String?) {
        if (BuildConfig.DEBUG) {
            Log.e(tag, errMsg)
        }
    }

    fun i(tag: String, info: String?) {
        if (BuildConfig.DEBUG) {
            Log.i(tag, info)
        }
    }
}