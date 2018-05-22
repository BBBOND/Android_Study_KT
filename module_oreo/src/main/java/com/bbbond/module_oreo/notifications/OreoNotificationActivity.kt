package com.bbbond.module_oreo.notifications

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.bbbond.module_common.BaseActivity
import com.bbbond.module_oreo.R

@Route(path = "/oreo/notification_activity")
class OreoNotificationActivity : BaseActivity() {

    private val TAG = OreoNotificationActivity::class.java.simpleName

    @Autowired
    @JvmField
    var back: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ARouter.getInstance().inject(this)
        setContentView(R.layout.module_oreo_notification_activity)

        initNavigation(back)
    }
}
