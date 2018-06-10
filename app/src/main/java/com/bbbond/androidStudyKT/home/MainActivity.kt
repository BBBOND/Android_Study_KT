package com.bbbond.androidStudyKT.home

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.bbbond.androidStudyKT.R
import com.bbbond.module_common.BaseActivity
import com.bbbond.module_common.adapter.CommonActionRecyclerAdapter
import com.bbbond.module_common.data.ActItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), MainContract.View {

    private var mAdapter: CommonActionRecyclerAdapter? = null
    private var mPresenter: MainContract.Presenter? = null
    private val actItems: ArrayList<ActItem> = ArrayList()
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        initNavigation(false)

        mPresenter = MainPresenter(this)
    }

    override fun onResume() {
        super.onResume()
        mPresenter?.loadActItems()
    }

    private fun initView() {
        mAdapter = CommonActionRecyclerAdapter(actItems, object : CommonActionRecyclerAdapter.OnItemClickListener{
            override fun onClick(view: View, position: Int, actItem: ActItem) {
                jumpTo(actItem.path)
            }
        })
        viewManager = LinearLayoutManager(this)
        rv_main.apply {
            adapter = mAdapter
            layoutManager = viewManager
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        }
    }

    override fun showActItems(acts: List<ActItem>) {
        actItems.clear()
        actItems.addAll(acts)
        mAdapter?.notifyDataSetChanged()
    }
}
