package com.bbbond.module_oreo.pip

import android.app.PendingIntent
import android.app.PictureInPictureParams
import android.app.RemoteAction
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Rect
import android.graphics.drawable.Icon
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.util.Rational
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.bbbond.module_common.BaseActivity
import com.bbbond.module_oreo.R
import kotlinx.android.synthetic.main.module_oreo_pip_activity.*

@Route(path = "/oreo/pip_activity")
class OreoPIPActivity : BaseActivity() {

    private val TAG = OreoPIPActivity::class.java.simpleName
    private val LIKE_ACTION = "like"

    @Autowired
    @JvmField
    var back: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ARouter.getInstance().inject(this)
        setContentView(R.layout.module_oreo_pip_activity)

        initNavigation(back)
    }

    override fun onStart() {
        super.onStart()
        registerReceiver(likeBroadcastReceiver, IntentFilter(LIKE_ACTION))
    }

    override fun onResume() {
        super.onResume()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Log.d(TAG, "onResume isInPictureInPictureMode: $isInPictureInPictureMode")
            if (isInPictureInPictureMode) {
                btn_pip.visibility = View.GONE
                supportActionBar?.hide()
            } else {
                btn_pip.visibility = View.VISIBLE
                btn_pip.isEnabled = true
                supportActionBar?.show()
            }
        } else {
            Log.d(TAG, "onResume")
        }
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(likeBroadcastReceiver)
    }

    fun toPip(view: View) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            btn_pip.isEnabled = false
            btn_pip.text = getString(R.string.oreo_coming_in_pip)

            val builder = PictureInPictureParams.Builder()
            val actions: ArrayList<RemoteAction> = ArrayList()
            actions.add(RemoteAction(
                    Icon.createWithResource(this, android.R.drawable.btn_star_big_off),
                    getString(R.string.oreo_like),
                    getString(R.string.oreo_like),
                    PendingIntent.getBroadcast(this, 0, Intent(LIKE_ACTION), PendingIntent.FLAG_UPDATE_CURRENT)))
            maxNumPictureInPictureActions
            // 设置画中画模式中的菜单，若长度超出maxNumPictureInPictureActions，则菜单列表会被截断
            builder.setActions(actions)
            // 设置画中画模式宽高比，宽高比限制在 2.39:1 和 1:2.39 之间
            builder.setAspectRatio(Rational(iv_pip.width, iv_pip.height))
            // 设置原控件位置范围，用于显示缩小至画中画窗口的动画（仅在首次进入画中画时才有效）
            builder.setSourceRectHint(Rect(iv_pip.left, iv_pip.top, iv_pip.right, iv_pip.bottom))
            enterPictureInPictureMode(builder.build())
        } else {
            toast(getString(R.string.oreo_not_support_pip_mode))
        }
    }

    private val likeBroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            toast(getString(R.string.oreo_receive_like))
        }
    }
}