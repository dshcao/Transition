package com.osshare.transition

import android.app.ActivityManager
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.osshare.framework.base.BaseAdapter
import com.osshare.framework.base.BaseViewHolder
import com.osshare.framework.base.SimpleOnItemListener
import com.osshare.transition.base.BaseActivity
import com.osshare.transition.dm.Item
import kotlinx.android.synthetic.main.activity_list.*

class ListActivity : BaseActivity() {

    lateinit var adapter: ListAdapter
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("uuuuuu", "onItemClickxx:" + (window.transitionManager == null))
        setContentView(R.layout.activity_list)
        Log.i("uuuuuu", "onItemClickyy:" + (window.transitionManager == null))

        adapter = ListAdapter(this, null)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addOnItemTouchListener(object : SimpleOnItemListener() {
            override fun onItemClick(view: View, holder: RecyclerView.ViewHolder) {
//                adapter.swap(holder.adapterPosition - 1, holder.adapterPosition)
//                adapter.remove(holder.adapterPosition)

                Log.i("uuuuuu", "onItemClick:" + (window.transitionManager == null))
                val ivImg = view.findViewById<ImageView>(R.id.ivImg)
                val tvText = view.findViewById<TextView>(R.id.tvText)
                val options = ActivityOptions.makeSceneTransitionAnimation(
                    this@ListActivity,
                    android.util.Pair<View, String>(ivImg, DetailActivity.VIEW_NAME_HEADER_IMAGE),
                    android.util.Pair<View, String>(tvText, DetailActivity.VIEW_NAME_HEADER_TITLE)
                )
//                val options = ActivityOptions.makeTaskLaunchBehind()

                val itemData = adapter.getData()?.get(holder.adapterPosition)
                val intent = Intent(this@ListActivity, DetailActivity::class.java)
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT)
//                intent.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
//                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
                intent.putExtra(DetailActivity.EXTRA_PARAM_ID, itemData?.id)

                startActivity(intent, options.toBundle())
//                startActivity(intent)


//                val am = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
//                am.appTasks.first().startActivity(applicationContext, intent, options.toBundle())
            }
        })

        recyclerView.adapter = adapter


        val list = mutableListOf<Item>()
        for (item in Item.ITEMS) {
            list.add(item)
        }
        adapter.setData(list)

    }


    class ListAdapter(context: Context, data: List<Item>?) : BaseAdapter<Item>(context, data) {
        override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
            val itemData = getItem(position)
            val ivImg = holder.getView<ImageView>(R.id.ivImg)
            val tvText = holder.getView<TextView>(R.id.tvText)
            tvText?.text = itemData?.name
            ivImg?.let { Glide.with(context).load(itemData!!.thumbnailUrl).into(it) }
        }

        override fun getItemView(parent: ViewGroup, viewType: Int): View {
            return inflater.inflate(R.layout.item_list, parent, false)
        }

    }
}