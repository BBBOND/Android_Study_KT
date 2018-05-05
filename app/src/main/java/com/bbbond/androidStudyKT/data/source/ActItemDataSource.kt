package com.bbbond.androidStudyKT.data.source

import com.bbbond.androidStudyKT.data.ActItem

interface ActItemDataSource {

    interface LoadActItemsCallback {

        fun onActItemsLoaded(actItems: List<ActItem>)

        fun onLoadErr(exception: Exception)
    }

    fun getActItems(callback: LoadActItemsCallback)

}