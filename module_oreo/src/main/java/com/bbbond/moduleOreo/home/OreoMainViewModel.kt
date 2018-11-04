package com.bbbond.moduleOreo.home

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.widget.Toast
import com.bbbond.moduleCommon.data.ActItem
import com.bbbond.moduleCommon.data.source.ActItemDataSource
import com.bbbond.moduleCommon.data.source.ActItemRepository
import com.bbbond.moduleCommon.data.source.local.ActItemLocalDataSource
import com.bbbond.moduleCommon.utils.LogUtil

class OreoMainViewModel(application: Application) : AndroidViewModel(application) {

    private val TAG: String = OreoMainViewModel::class.java.simpleName

    private var actItemList: MutableLiveData<List<ActItem>> = MutableLiveData<List<ActItem>>()
    private val mActItemRepository: ActItemRepository = ActItemRepository.getInstance(ActItemLocalDataSource.getInstance(getApplication()))

    fun getActItemList(): LiveData<List<ActItem>> {
        return actItemList
    }

    fun loadActItemList() {
        mActItemRepository.getActItems("oreo_list.json", object : ActItemDataSource.LoadActItemsCallback {
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