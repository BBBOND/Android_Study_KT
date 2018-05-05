package com.bbbond.androidStudyKT.data.source.local

import android.annotation.SuppressLint
import android.content.Context
import android.support.annotation.VisibleForTesting
import com.alibaba.fastjson.JSONArray
import com.bbbond.androidStudyKT.data.ActItem
import com.bbbond.androidStudyKT.data.source.ActItemDataSource
import com.bbbond.androidStudyKT.units.CommonUtils

class ActItemLocalDataSource private constructor(
        var context: Context
) : ActItemDataSource {

    private val TAG = ActItemLocalDataSource::class.java.simpleName

    override fun getActItems(callback: ActItemDataSource.LoadActItemsCallback) {
        try {
            val text = CommonUtils.readAssetsText(context, "list.json")
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