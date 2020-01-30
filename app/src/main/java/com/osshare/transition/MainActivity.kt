package com.osshare.transition

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.transition.*
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.SharedElementCallback
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentTransaction
import com.osshare.transition.base.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startActivity(Intent(this, ListActivity::class.java))

    }
}
