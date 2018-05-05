package com.bbbond.module_common

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.jude.swipbackhelper.SwipeBackHelper

open class BaseActivity : AppCompatActivity() {

    fun initNavigation(hasBack: Boolean) {
        supportActionBar?.setDisplayHomeAsUpEnabled(hasBack)
        SwipeBackHelper.getCurrentPage(this).setSwipeBackEnable(hasBack)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> finish()
        }
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.NoTranslucent)
        super.onCreate(savedInstanceState)
        SwipeBackHelper.onCreate(this)
        SwipeBackHelper.getCurrentPage(this).setSwipeEdge(200)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        SwipeBackHelper.onPostCreate(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        SwipeBackHelper.onDestroy(this)
    }
}