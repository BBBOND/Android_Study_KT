package com.bbbond.androidStudyKT.home

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.AdapterView
import androidx.core.net.toUri
import com.alibaba.android.arouter.launcher.ARouter
import com.bbbond.androidStudyKT.R
import com.bbbond.androidStudyKT.data.ActItem
import com.bbbond.module_common.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast

class MainActivity : BaseActivity(), MainContract.View {

    private var mAdapter: ViewHolderAdapter? = null
    private var mPresenter: MainContract.Presenter? = null
    private val actItems: ArrayList<ActItem> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        initEvent()
        initNavigation(false)

        mPresenter = MainPresenter(this)
    }

    override fun onResume() {
        super.onResume()
        mPresenter?.loadActItems()
    }

    private fun initView() {
        lv.emptyView = tv_no_data
        mAdapter = ViewHolderAdapter(this, actItems)
        lv.adapter = mAdapter
    }

    private fun initEvent() {
        lv.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val actItem = mAdapter?.getItem(position) as ActItem
            mPresenter?.jump(actItem)
        }
    }

    override fun showActItems(acts: List<ActItem>) {
        actItems.clear()
        actItems.addAll(acts)
        mAdapter?.notifyDataSetChanged()
    }
}
