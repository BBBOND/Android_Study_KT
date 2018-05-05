package com.bbbond.androidStudyKT.data.source

import android.support.annotation.VisibleForTesting
import com.bbbond.androidStudyKT.data.source.local.ActItemLocalDataSource

class ActItemRepository private constructor(
        var actItemLocalDataSource: ActItemLocalDataSource
) : ActItemDataSource {

    override fun getActItems(callback: ActItemDataSource.LoadActItemsCallback) {
        actItemLocalDataSource.getActItems(callback)
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