package com.bbbond.androidStudyKT.home

import com.bbbond.module_common.data.ActItem

interface MainContract {
    interface View {
        fun showActItems(acts: List<ActItem>)
    }

    interface Presenter {

        fun loadActItems()
    }
}