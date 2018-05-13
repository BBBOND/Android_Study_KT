package com.bbbond.module_common.data.source

import android.support.annotation.VisibleForTesting
import com.bbbond.module_common.data.source.ActItemDataSource
import com.bbbond.module_common.data.source.local.ActItemLocalDataSource

class ActItemRepository private constructor(
        var actItemLocalDataSource: ActItemLocalDataSource
) : ActItemDataSource {

    override fun getActItems(fileName: String, callback: ActItemDataSource.LoadActItemsCallback) {
        actItemLocalDataSource.getActItems(fileName, callback)
    }

    companion object {
        private var INSTANCE: ActItemRepository? = null

        @JvmStatic
        fun getInstance(actItemLocalDataSource: ActItemLocalDataSource): ActItemRepository {
            if (INSTANCE == null) {
                synchronized(ActItemLocalDataSource::javaClass) {
                    INSTANCE = ActItemRepository(actItemLocalDataSource)
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