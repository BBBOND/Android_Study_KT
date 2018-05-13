package com.bbbond.module_common.data.source

import com.bbbond.module_common.data.ActItem

interface ActItemDataSource {

    interface LoadActItemsCallback {

        fun onActItemsLoaded(actItems: List<ActItem>)

        fun onLoadErr(exception: Exception)
    }

    fun getActItems(fileName: String, callback: LoadActItemsCallback)

}