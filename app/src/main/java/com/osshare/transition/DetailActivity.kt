package com.osshare.transition

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.app.SharedElementCallback
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.ImageViewTarget
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.target.ViewTarget
import com.bumptech.glide.request.transition.Transition
import com.osshare.transition.base.BaseActivity
import com.osshare.transition.dm.Item
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : BaseActivity() {
    companion object {
        const val EXTRA_PARAM_ID = "detail:_id"

        const val VIEW_NAME_HEADER_IMAGE = "detail:header:image"
        const val VIEW_NAME_HEADER_TITLE = "detail:header:title"
    }

    var item: Item? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        postponeEnterTransition()

//        setEnterSharedElementCallback(object : SharedElementCallback() {
//            override fun onSharedElementStart(
//                sharedElementNames: MutableList<String>?,
//                sharedElements: MutableList<View>?,
//                sharedElementSnapshots: MutableList<View>?
//            ) {
//                super.onSharedElementStart(sharedElementNames, sharedElements, sharedElementSnapshots)
//                Log.i("uuuuuu","DetailActivity setEnterSharedElementCallback onSharedElementStart")
//            }
//
//            override fun onSharedElementEnd(
//                sharedElementNames: MutableList<String>?,
//                sharedElements: MutableList<View>?,
//                sharedElementSnapshots: MutableList<View>?
//            ) {
//                super.onSharedElementEnd(sharedElementNames, sharedElements, sharedElementSnapshots)
//                Log.i("uuuuuu","DetailActivity setEnterSharedElementCallback onSharedElementEnd")
//            }
//
//            override fun onSharedElementsArrived(
//                sharedElementNames: MutableList<String>?,
//                sharedElements: MutableList<View>?,
//                listener: OnSharedElementsReadyListener?
//            ) {
//                super.onSharedElementsArrived(sharedElementNames, sharedElements, listener)
//                Log.i("uuuuuu","DetailActivity setEnterSharedElementCallback onSharedElementsArrived")
//            }
//        })
//        setExitSharedElementCallback(object : SharedElementCallback() {
//            override fun onSharedElementStart(
//                sharedElementNames: MutableList<String>?,
//                sharedElements: MutableList<View>?,
//                sharedElementSnapshots: MutableList<View>?
//            ) {
//                super.onSharedElementStart(sharedElementNames, sharedElements, sharedElementSnapshots)
//                Log.i("uuuuuu","DetailActivity setExitSharedElementCallback onSharedElementStart")
//            }
//
//            override fun onSharedElementEnd(
//                sharedElementNames: MutableList<String>?,
//                sharedElements: MutableList<View>?,
//                sharedElementSnapshots: MutableList<View>?
//            ) {
//                super.onSharedElementEnd(sharedElementNames, sharedElements, sharedElementSnapshots)
//                Log.i("uuuuuu","DetailActivity setExitSharedElementCallback onSharedElementEnd")
//            }
//
//            override fun onSharedElementsArrived(
//                sharedElementNames: MutableList<String>?,
//                sharedElements: MutableList<View>?,
//                listener: OnSharedElementsReadyListener?
//            ) {
//                super.onSharedElementsArrived(sharedElementNames, sharedElements, listener)
//                Log.i("uuuuuu","DetailActivity setExitSharedElementCallback onSharedElementsArrived")
//            }
//        })

        // Retrieve the correct Item instance, using the ID provided in the Intent
        item = Item.getItem(intent.getIntExtra(EXTRA_PARAM_ID, 0))

        ivImg.transitionName = VIEW_NAME_HEADER_IMAGE
        tvText.transitionName = VIEW_NAME_HEADER_TITLE

//        postponeEnterTransition()
        if (item != null) {
            load()
        }
    }

    fun load() {
        tvText.setText(item!!.name + item!!.author)
        loadFull()
    }

    fun loadThumbnail() {
        Glide.with(this).load(item!!.thumbnailUrl).into(ivImg)
    }

    fun loadFull() {
        Glide.with(this).load(item!!.photoUrl).into(object : ImageViewTarget<Drawable>(ivImg) {
            override fun setResource(resource: Drawable?) {
                Log.i("uuuuuu", "setResource")
                setDrawable(resource)
            }

            override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                Log.i("uuuuuu", "onResourceReady")
                super.onResourceReady(resource, transition)
                Log.i("uuuuuu", "onResourceReady end")
                startPostponedEnterTransition()
            }

            override fun onLoadFailed(errorDrawable: Drawable?) {
                super.onLoadFailed(errorDrawable)
                startPostponedEnterTransition()
            }

        })
//        Glide.with(this).load(item!!.photoUrl).into(ivImg)

    }

}