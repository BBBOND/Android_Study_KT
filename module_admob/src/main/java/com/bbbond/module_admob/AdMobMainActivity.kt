package com.bbbond.module_admob

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.bbbond.module_common.BaseActivity
import com.bbbond.module_common.adapter.CommonActionRecyclerAdapter
import com.bbbond.module_common.data.ActItem
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.module_admob_main_activity.*

@Route(path = "/admob/main_activity")
class AdMobMainActivity : BaseActivity() {

    private val TAG = AdMobMainActivity::class.java.simpleName

    @Autowired
    @JvmField
    var back: Boolean = false

    private var mAdapter: CommonActionRecyclerAdapter? = null
    private val actItems: ArrayList<ActItem> = ArrayList()
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ARouter.getInstance().inject(this)
        setContentView(R.layout.module_admob_main_activity)

        initNavigation(back)
        initData()
        initView()
        MobileAds.initialize(this, "ca-app-pub-7782501838134680~2692272483")
    }

    private fun initData() {
        actItems.add(ActItem().set("banner", "/admob/banner_activity?back=true"))
        actItems.add(ActItem().set("interstitial", "/admob/interstitial_activity?back=true"))
        actItems.add(ActItem().set("native", "/admob/native_activity?back=true"))
        actItems.add(ActItem().set("rewarded", "/admob/rewarded_activity?back=true"))
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
}