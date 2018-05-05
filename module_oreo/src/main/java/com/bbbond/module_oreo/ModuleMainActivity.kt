package com.bbbond.module_oreo

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.bbbond.module_common.BaseActivity

@Route(path = "/oreo/main_activity")
class ModuleMainActivity : BaseActivity() {

    private val TAG = ModuleMainActivity::class.java.simpleName

    @Autowired
    @JvmField
    var back: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ARouter.getInstance().inject(this)
        setContentView(R.layout.module_main_activity)

        initNavigation(back)
    }
}