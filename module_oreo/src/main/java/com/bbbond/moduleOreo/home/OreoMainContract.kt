package com.bbbond.moduleOreo.home

import com.bbbond.moduleCommon.data.ActItem

interface OreoMainContract {
    interface View {
        fun showActItems(acts: List<ActItem>)
    }

    interface Presenter {

        fun loadActItems()
        fun jump(actItem: ActItem)
    }
}