package com.bbbond.androidStudyKT.home

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import androidx.core.net.toUri
import com.alibaba.android.arouter.launcher.ARouter
import com.bbbond.androidStudyKT.data.ActItem
import org.jetbrains.anko.toast

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

    override fun jump(actItem: ActItem) {
        activity.toast(actItem.path)
        ARouter
                .getInstance()
                .build(actItem.path.toUri())
                .withTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                .navigation()
    }
}