package com.bbbond.module_common.data.source.local

import android.annotation.SuppressLint
import android.content.Context
import android.support.annotation.VisibleForTesting
import com.alibaba.fastjson.JSONArray
import com.bbbond.module_common.data.ActItem
import com.bbbond.module_common.data.source.ActItemDataSource
import com.bbbond.module_common.utils.CommonUtils

class ActItemLocalDataSource private constructor(
        var context: Context
) : ActItemDataSource {

    private val TAG = ActItemLocalDataSource::class.java.simpleName

    override fun getActItems(fileName: String, callback: ActItemDataSource.LoadActItemsCallback) {
        try {
            val text = CommonUtils.readAssetsText(context, fileName)
            val actItems = JSONArray.parseArray(text, ActItem::class.java)
            callback.onActItemsLoaded(actItems)
        } catch (e: Exception) {
            callback.onLoadErr(e)
        }
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        private var INSTANCE: ActItemLocalDataSource? = null

        @JvmStatic
        fun getInstance(context: Context): ActItemLocalDataSource {
            if (INSTANCE == null) {
                synchronized(ActItemLocalDataSource::javaClass) {
                    INSTANCE = ActItemLocalDataSource(context.applicationContext)
                }
            }
            return INSTANCE!!
        }

        @VisibleForTesting
        fun clearInstance() {
            INSTANCE = null
        }
    }
}