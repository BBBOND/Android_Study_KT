package com.bbbond.androidStudyKT.home

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import com.bbbond.androidStudyKT.R
import com.bbbond.module_common.BaseActivity
import com.bbbond.module_common.adapter.CommonActionAdapter
import com.bbbond.module_common.data.ActItem
import com.bbbond.module_oreo.home.OreoMainActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), MainContract.View {

    private var mAdapter: CommonActionAdapter? = null
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
        mAdapter = CommonActionAdapter(this, actItems)
        lv.adapter = mAdapter
    }

    private fun initEvent() {
        lv.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val actItem = mAdapter?.getItem(position) as ActItem
            mPresenter?.jump(actItem)
//            startActivity(Intent(this, OreoMainActivity::class.java))
        }
    }

    override fun showActItems(acts: List<ActItem>) {
        actItems.clear()
        actItems.addAll(acts)
        mAdapter?.notifyDataSetChanged()
    }
}
