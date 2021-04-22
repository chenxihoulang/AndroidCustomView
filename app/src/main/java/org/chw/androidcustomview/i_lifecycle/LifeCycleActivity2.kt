package org.chw.androidcustomview.i_lifecycle

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.widget.Button
import org.chw.androidcustomview.BaseActivity
import org.chw.androidcustomview.R

/**
 * @author chaihongwei
 * @date 2021-04-22 08:54:59
 */
class LifeCycleActivity2 : BaseActivity() {
    companion object {
        const val TAG = "lifecycle_tag2"
        const val SAVE_KEY = "save_key2"
    }

    private lateinit var btnNav: Button
    override fun getLayoutId() = R.layout.activity_lifecycle2

    override fun init() {
        btnNav = findViewById(R.id.btnNav)
        btnNav.setOnClickListener {
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e(TAG, "onCreate")

        if (savedInstanceState != null) {
            val key = savedInstanceState[SAVE_KEY]
            Log.e(TAG, "onCreate: $key")
        }
    }

    override fun onRestart() {
        super.onRestart()
        Log.e(TAG, "onRestart")
    }

    override fun onStart() {
        super.onStart()
        Log.e(TAG, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.e(TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.e(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.e(TAG, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG, "onDestroy")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val currentTime = System.currentTimeMillis()
        Log.e(TAG, "onSaveInstanceState:$currentTime")

        outState.putLong(LifeCycleActivity1.SAVE_KEY, currentTime)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        Log.e(TAG, "onRestoreInstanceState")

        if (savedInstanceState != null) {
            val key = savedInstanceState[SAVE_KEY]
            Log.e(TAG, "onRestoreInstanceState: $key")
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        Log.e(TAG, "onConfigurationChanged:" + newConfig.orientation)
    }
}