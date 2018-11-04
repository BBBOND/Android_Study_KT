package com.bbbond.moduleCommon.data.source

import com.bbbond.moduleCommon.data.ActItem

interface ActItemDataSource {

    interface LoadActItemsCallback {

        fun onActItemsLoaded(actItems: List<ActItem>)

        fun onLoadErr(exception: Exception)
    }

    fun getActItems(fileName: String, callback: LoadActItemsCallback)

}