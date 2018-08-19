package com.bbbond.module_admob.rewarded

import android.os.Bundle
import android.util.Log
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.bbbond.android_study_kt.R
import com.bbbond.module_common.BaseActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.reward.RewardItem
import com.google.android.gms.ads.reward.RewardedVideoAd
import com.google.android.gms.ads.reward.RewardedVideoAdListener
import kotlinx.android.synthetic.main.module_admob_rewarded_activity.*

@Route(path = "/admob/rewarded_activity")
class AdMobRewardedActivity : BaseActivity(), RewardedVideoAdListener {

    private val TAG = AdMobRewardedActivity::class.java.simpleName

    private lateinit var mRewardedVideoAd: RewardedVideoAd

    @Autowired
    @JvmField
    var back: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ARouter.getInstance().inject(this)
        setContentView(R.layout.module_admob_rewarded_activity)
        initNavigation(back)

        initAd()
        initEvent()
    }

    private fun initAd() {
        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this)
        mRewardedVideoAd.loadAd("ca-app-pub-7782501838134680/9250490714",
                AdRequest.Builder()
                        .addTestDevice("A60DFB23A39A9388BF39405D6F1B8BE0")
                        .addTestDevice("021F0CB79B33A837F8EB9531649973AA")
                        .build())
        mRewardedVideoAd.rewardedVideoAdListener = this
    }

    private fun initEvent() {
        btn_rewarded.setOnClickListener {
            if (mRewardedVideoAd.isLoaded) {
                mRewardedVideoAd.show()
            } else {
                Log.d(TAG, "The rewarded wasn't loaded yet.")
            }
        }
    }

    override fun onPause() {
        super.onPause()
        mRewardedVideoAd.pause(this)
    }

    override fun onResume() {
        super.onResume()
        mRewardedVideoAd.resume(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        mRewardedVideoAd.destroy(this)
    }

    override fun onRewardedVideoAdClosed() {
        toast("onRewardedVideoAdClosed")
        Log.d(TAG, "onRewardedVideoAdClosed")
    }

    override fun onRewardedVideoAdLeftApplication() {
        toast("onRewardedVideoAdLeftApplication")
        Log.d(TAG, "onRewardedVideoAdLeftApplication")
    }

    override fun onRewardedVideoAdLoaded() {
        toast("onRewardedVideoAdLoaded")
        Log.d(TAG, "onRewardedVideoAdLoaded")
    }

    override fun onRewardedVideoAdOpened() {
        toast("onRewardedVideoAdOpened")
        Log.d(TAG, "onRewardedVideoAdOpened")
    }

    override fun onRewardedVideoCompleted() {
        toast("onRewardedVideoCompleted")
        Log.d(TAG, "onRewardedVideoCompleted")
    }

    override fun onRewarded(reward: RewardItem?) {
        toast("onRewarded --> amount: ${reward?.amount}, type: ${reward?.type}")
        Log.d(TAG, "onRewarded --> amount: ${reward?.amount}, type: ${reward?.type}")
    }

    override fun onRewardedVideoStarted() {
        toast("onRewardedVideoStarted")
        Log.d(TAG, "onRewardedVideoStarted")
    }

    override fun onRewardedVideoAdFailedToLoad(errorCode: Int) {
        var msg = "ERROR_CODE_INTERNAL_ERROR"
        when (errorCode) {
            AdRequest.ERROR_CODE_INTERNAL_ERROR -> msg = "ERROR_CODE_INTERNAL_ERROR"
            AdRequest.ERROR_CODE_INVALID_REQUEST -> msg = "ERROR_CODE_INVALID_REQUEST"
            AdRequest.ERROR_CODE_NETWORK_ERROR -> msg = "ERROR_CODE_NETWORK_ERROR"
            AdRequest.ERROR_CODE_NO_FILL -> msg = "ERROR_CODE_NO_FILL"
        }

        toast("onRewardedVideoAdFailedToLoad code: $errorCode msg: $msg")
        Log.e(TAG, "onRewardedVideoAdFailedToLoad code: $errorCode msg: $msg")
    }
}