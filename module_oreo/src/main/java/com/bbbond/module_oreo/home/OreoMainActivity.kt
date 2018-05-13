package com.bbbond.module_oreo.home

import android.os.Bundle
import android.widget.AdapterView
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.bbbond.module_common.BaseActivity
import com.bbbond.module_common.adapter.CommonActionAdapter
import com.bbbond.module_common.data.ActItem
import com.bbbond.module_oreo.R
import kotlinx.android.synthetic.main.module_oreo_main_activity.*

@Route(path = "/oreo/main_activity")
class OreoMainActivity : BaseActivity(), OreoMainContract.View {

    private val TAG = OreoMainActivity::class.java.simpleName

    @Autowired
    @JvmField
    var back: Boolean = false

    private var mAdapter: CommonActionAdapter? = null
    private var mPresenter: OreoMainContract.Presenter? = null
    private val actItems: ArrayList<ActItem> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ARouter.getInstance().inject(this)
        setContentView(R.layout.module_oreo_main_activity)

        initView()
        initEvent()
        initNavigation(back)

        mPresenter = OreoMainPresenter(this)
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
        }
    }

    override fun showActItems(acts: List<ActItem>) {
        actItems.clear()
        actItems.addAll(acts)
        mAdapter?.notifyDataSetChanged()
    }
}