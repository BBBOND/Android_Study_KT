package com.bbbond.androidStudyKT.home

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import com.bbbond.moduleCommon.data.ActItem

class MainPresenter(
        var activity: MainActivity
) : MainContract.Presenter {

    private var mainViewModel: MainViewModel = ViewModelProviders.of(activity).get(MainViewModel::class.java)

    init {
        mainViewModel.getActItemList().observe(activity, Observer<List<ActItem>> { t ->
            t?.let {
                activity.showActItems(it)
            }
        })
    }

    override fun loadActItems() {
        mainViewModel.loadActItemList()
    }
}