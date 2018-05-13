package com.bbbond.module_oreo.home

import com.bbbond.module_common.data.ActItem

interface OreoMainContract {
    interface View {
        fun showActItems(acts: List<ActItem>)
    }

    interface Presenter {

        fun loadActItems()
        fun jump(actItem: ActItem)
    }
}