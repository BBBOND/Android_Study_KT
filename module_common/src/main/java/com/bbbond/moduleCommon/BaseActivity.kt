package com.bbbond.moduleCommon

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.widget.Toast
import androidx.core.net.toUri
import com.alibaba.android.arouter.launcher.ARouter

open class BaseActivity : AppCompatActivity() {

    fun initNavigation(hasBack: Boolean) {
        supportActionBar?.setDisplayHomeAsUpEnabled(hasBack)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> finish()
        }
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    protected fun jumpTo(path: String) {
        ARouter
                .getInstance()
                .build(path.toUri())
                .withTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                .navigation()
    }

    protected fun toast(msg: String?) {
        Toast.makeText(this@BaseActivity, "$msg", Toast.LENGTH_SHORT).show()
    }
}