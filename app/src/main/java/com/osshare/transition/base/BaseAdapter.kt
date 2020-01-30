package com.osshare.framework.base

import android.content.Context
import android.graphics.Rect
import android.util.Log
import android.view.*
import androidx.recyclerview.widget.RecyclerView
import java.util.*

abstract class BaseAdapter<T>(val context: Context, data: List<T>?) :
    RecyclerView.Adapter<BaseViewHolder>(),
    AdapterCallback {
    var inflater: LayoutInflater = LayoutInflater.from(context)
    private var data: MutableList<T>? = data?.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return BaseViewHolder(getItemView(parent, viewType))
    }

    abstract override fun onBindViewHolder(holder: BaseViewHolder, position: Int)

    abstract fun getItemView(parent: ViewGroup, viewType: Int): View

    override fun getItemCount(): Int {
        return if (data == null) 0 else data!!.size
    }

    protected fun getItem(position: Int): T? {
        return if (data == null) null else data!![position]
    }

    override fun swap(position: Int, toPosition: Int) {
        if (position < toPosition) {
            for (i in position until toPosition) {
                Collections.swap(data, i, i + 1)
            }
        } else {
            for (i in position downTo toPosition + 1) {
                Collections.swap(data, i, i - 1)
            }
        }
        notifyItemMoved(position, toPosition)
    }

    override fun remove(position: Int) {
        data!!.removeAt(position)
        notifyItemRemoved(position)
//        notifyItemRangeChanged()
//        notifyItemRangeRemoved(position,0)
    }

    fun addData(list: List<T>?) {
        if (list == null || list.isEmpty()) {
            return
        }
        if (this.data == null) {
            this.data = mutableListOf()
        }
        this.data!!.addAll(list)
        notifyItemRangeInserted(this.data!!.size, list.size)
    }

    fun addData(list: List<T>?, position: Int) {
        if (list == null || list.isEmpty()) {
            return
        }
        if (this.data == null) {
            this.data = mutableListOf()
        }
        this.data!!.addAll(position, list)
        notifyItemRangeInserted(position, list.size)
    }

    fun getData(): MutableList<T>? {
        return data
    }

    fun setData(list: List<T>?) {
        if (list == null) {
            this.data = null
        } else {
            this.data = list.toMutableList()
        }
        notifyDataSetChanged()
    }


}
