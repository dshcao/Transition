package com.osshare.transition.base

import android.content.Intent
import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)

        window.allowEnterTransitionOverlap = false
        window.allowReturnTransitionOverlap = true
    }

    override fun onBackPressed() {
        super.onBackPressed()
//        finishAfterTransition()
    }

    override fun onActivityReenter(resultCode: Int, data: Intent?) {
        super.onActivityReenter(resultCode, data)
    }
}