package com.bbbond.androidStudyKT.home

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.widget.Toast
import com.bbbond.androidStudyKT.data.ActItem
import com.bbbond.androidStudyKT.data.source.ActItemDataSource
import com.bbbond.androidStudyKT.data.source.ActItemRepository
import com.bbbond.androidStudyKT.data.source.local.ActItemLocalDataSource
import com.bbbond.module_common.utils.LogUtil

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val TAG: String = MainViewModel::class.java.simpleName

    private var actItemList: MutableLiveData<List<ActItem>> = MutableLiveData<List<ActItem>>()
    private val mActItemRepository: ActItemRepository = ActItemRepository.getInstance(ActItemLocalDataSource.getInstance(getApplication()))

    fun getActItemList(): LiveData<List<ActItem>> {
        return actItemList
    }

    fun loadActItemList() {
        mActItemRepository.getActItems(object : ActItemDataSource.LoadActItemsCallback {
            override fun onActItemsLoaded(actItems: List<ActItem>) {
                actItemList.value = actItems
            }

            override fun onLoadErr(exception: Exception) {
                LogUtil.e(TAG, exception.message)
                Toast.makeText(getApplication(), exception.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}