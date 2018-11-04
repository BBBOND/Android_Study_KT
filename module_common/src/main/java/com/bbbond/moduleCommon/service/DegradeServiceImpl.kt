package com.bbbond.moduleCommon.service

import android.content.Context
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.facade.service.DegradeService
import com.bbbond.moduleCommon.utils.LogUtil

@Route(path = "/askt/degradeservice")
class DegradeServiceImpl: DegradeService {

    override fun onLost(context: Context?, postcard: Postcard?) {
        LogUtil.e("DegradeServiceImpl",  "path: ${postcard?.path}")
        LogUtil.e("DegradeServiceImpl",  "context: ${context == null}")
//        Toast.makeText(context, "未知页面, ${postcard?.path}", Toast.LENGTH_SHORT).show()
    }

    override fun init(context: Context?) {
        LogUtil.d("DegradeServiceImpl", "init")
    }

}