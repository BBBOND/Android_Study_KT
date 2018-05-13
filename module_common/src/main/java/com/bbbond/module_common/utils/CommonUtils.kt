package com.bbbond.module_common.utils

import android.content.Context
import java.io.InputStreamReader

object CommonUtils {

    fun readAssetsText(context: Context, fileName: String): String? {
        val inputStream = context.assets.open(fileName)
        val reader = InputStreamReader(inputStream)
        val assetsText = reader.readText()
        reader.close()
        inputStream.close()
        return assetsText
    }
}