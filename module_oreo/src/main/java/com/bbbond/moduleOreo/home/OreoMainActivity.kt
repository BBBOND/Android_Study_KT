package com.bbbond.moduleOreo.home

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.bbbond.moduleCommon.BaseActivity
import com.bbbond.moduleCommon.adapter.CommonActionRecyclerAdapter
import com.bbbond.moduleCommon.data.ActItem
import com.bbbond.moduleOreo.R
import kotlinx.android.synthetic.main.module_oreo_main_activity.*

@Route(path = "/oreo/main_activity")
class OreoMainActivity : BaseActivity(), OreoMainContract.View {

    private val TAG = OreoMainActivity::class.java.simpleName

    @Autowired
    @JvmField
    var back: Boolean = false

    private var mAdapter: CommonActionRecyclerAdapter? = null
    private var mPresenter: OreoMainContract.Presenter? = null
    private val actItems: ArrayList<ActItem> = ArrayList()
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ARouter.getInstance().inject(this)
        setContentView(R.layout.module_oreo_main_activity)

        initNavigation(back)
        initView()

        mPresenter = OreoMainPresenter(this)
    }

    override fun onResume() {
        super.onResume()
        mPresenter?.loadActItems()
    }

    private fun initView() {
        mAdapter = CommonActionRecyclerAdapter(actItems, object : CommonActionRecyclerAdapter.OnItemClickListener {
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