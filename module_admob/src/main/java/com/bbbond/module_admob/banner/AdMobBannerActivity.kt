package com.bbbond.module_admob.banner

import android.os.Bundle
import android.util.Log
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.bbbond.android_study_kt.R
import com.bbbond.module_common.BaseActivity
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import kotlinx.android.synthetic.main.module_admob_banner_activity.*

@Route(path = "/admob/banner_activity")
class AdMobBannerActivity : BaseActivity() {

    private val TAG = AdMobBannerActivity::class.java.simpleName

    @Autowired
    @JvmField
    var back: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ARouter.getInstance().inject(this)
        setContentView(R.layout.module_admob_banner_activity)
        initNavigation(back)

        initAd()
        switch_banner.setOnCheckedChangeListener { _, isChecked ->
            showHideAd(isChecked)
        }
    }

    private fun initAd() {
        val adRequest = AdRequest.Builder()
                .addTestDevice("A60DFB23A39A9388BF39405D6F1B8BE0")
                .addTestDevice("021F0CB79B33A837F8EB9531649973AA")
                .build()
        ad_banner.loadAd(adRequest)
        ad_banner.adListener = object : AdListener() {
            override fun onAdLoaded() {
                toast("onAdLoaded")
                Log.d(TAG, "onAdLoaded")
            }

            override fun onAdFailedToLoad(errorCode: Int) {
                var msg = "ERROR_CODE_INTERNAL_ERROR"
                when(errorCode) {
                    AdRequest.ERROR_CODE_INTERNAL_ERROR -> msg = "ERROR_CODE_INTERNAL_ERROR"
                    AdRequest.ERROR_CODE_INVALID_REQUEST -> msg = "ERROR_CODE_INVALID_REQUEST"
                    AdRequest.ERROR_CODE_NETWORK_ERROR -> msg = "ERROR_CODE_NETWORK_ERROR"
                    AdRequest.ERROR_CODE_NO_FILL -> msg = "ERROR_CODE_NO_FILL"
                }

                toast("onAdFailedToLoad code: $errorCode msg: $msg")
                Log.e(TAG, "onAdFailedToLoad code: $errorCode msg: $msg")
            }

            override fun onAdOpened() {
                toast("onAdOpened")
                Log.d(TAG, "onAdOpened")
            }

            override fun onAdLeftApplication() {
                toast("onAdLeftApplication")
                Log.d(TAG, "onAdLeftApplication")
            }

            override fun onAdClosed() {
                toast("onAdClosed")
                Log.d(TAG, "onAdClosed")
            }
        }
    }

    private fun showHideAd(show: Boolean) {
        ad_banner.visibility = if (show) View.VISIBLE else View.GONE
    }
}