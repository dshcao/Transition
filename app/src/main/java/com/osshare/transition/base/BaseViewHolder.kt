package com.osshare.framework.base

import android.util.SparseArray
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var views: SparseArray<View> = SparseArray()

    inline fun <reified V : View> getView(id: Int): V? {
        var v: View? = views.get(id)
        if (v == null) {
            v = itemView.findViewById(id)
            if (v != null) {
                views.put(id, v)
            }
        }
        return v as V?
    }
}