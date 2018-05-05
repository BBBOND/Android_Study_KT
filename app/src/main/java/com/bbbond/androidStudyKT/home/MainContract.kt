package com.bbbond.androidStudyKT.home

import com.bbbond.androidStudyKT.data.ActItem

interface MainContract {
    interface View {
        fun showActItems(acts: List<ActItem>)
    }

    interface Presenter {

        fun loadActItems()
        fun jump(actItem: ActItem)
    }
}