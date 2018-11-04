package com.bbbond.moduleCommon.service

import android.content.Context
import android.net.Uri
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.facade.service.PathReplaceService
import com.bbbond.moduleCommon.utils.LogUtil

@Route(path = "/askt/pathreplaceservice")
class PathReplaceServiceImpl: PathReplaceService {
    override fun forString(path: String?): String {
        LogUtil.d("PathReplaceServiceImpl", "forString $path")
        return path!!
    }

    override fun forUri(uri: Uri?): Uri {
        LogUtil.d("PathReplaceServiceImpl", "uri ${uri?.toString()}")
        return uri!!
    }

    override fun init(context: Context?) {
        LogUtil.d("PathReplaceServiceImpl", "init")
    }
}