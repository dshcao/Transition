package com.osshare.framework.base

import android.graphics.Canvas
import android.graphics.Rect
import android.util.Log
import android.view.GestureDetector
import android.view.GestureDetector.OnGestureListener
import android.view.MotionEvent
import android.view.View
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

open class OnItemCallback(callback: AdapterCallback, dragDirs: Int, swipeDirs: Int) :
    ItemTouchHelper.SimpleCallback(dragDirs, swipeDirs) {
    var adapterCallback = callback

    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        super.onSelectedChanged(viewHolder, actionState)
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }

    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        super.clearView(recyclerView, viewHolder)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        val position = viewHolder.adapterPosition
        val targetPosition = target.adapterPosition
        adapterCallback.swap(position, targetPosition)
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        adapterCallback.remove(viewHolder.adapterPosition)
    }
}

abstract class OnItemListener : RecyclerView.SimpleOnItemTouchListener() {

    private var gestureDetector: GestureDetector? = null
    override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
        gestureEvent(rv, e)
        return false
    }

    override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {

    }

    override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
    }

    private fun gestureEvent(rv: RecyclerView, e: MotionEvent): Boolean {
        if (gestureDetector == null) {
            gestureDetector = GestureDetector(rv.context, object : OnGestureListener {
                override fun onShowPress(e: MotionEvent?) {

                }

                override fun onSingleTapUp(e: MotionEvent): Boolean {
                    val view = rv.findChildViewUnder(e.x, e.y)
                    if (view != null) {
                        val holder = rv.getChildViewHolder(view)
                        onItemClick(view, holder)
                        return true
                    }
                    return true
                }

                override fun onDown(e: MotionEvent?): Boolean {
                    return false
                }

                override fun onFling(
                    e1: MotionEvent?,
                    e2: MotionEvent?,
                    velocityX: Float,
                    velocityY: Float
                ): Boolean {
                    return false
                }

                override fun onScroll(
                    e1: MotionEvent?,
                    e2: MotionEvent?,
                    distanceX: Float,
                    distanceY: Float
                ): Boolean {
                    return false
                }

                override fun onLongPress(e: MotionEvent) {
                    val view = rv.findChildViewUnder(e.x, e.y)
                    if (view != null) {
                        val holder = rv.getChildViewHolder(view)
                        onItemLongPress(view, holder)
                    }
                }

            })
        }
        return gestureDetector!!.onTouchEvent(e)
    }

    abstract fun onItemClick(view: View, holder: RecyclerView.ViewHolder)
    abstract fun onItemLongPress(view: View, holder: RecyclerView.ViewHolder)
}

open class SimpleOnItemListener : OnItemListener() {
    override fun onItemClick(view: View, holder: RecyclerView.ViewHolder) {
    }

    override fun onItemLongPress(view: View, holder: RecyclerView.ViewHolder) {
    }

}

interface AdapterCallback {
    fun swap(position: Int, toPosition: Int)
    fun remove(position: Int)
}


class OnItemSwipeAction : RecyclerView.SimpleOnItemTouchListener() {
    private var gestureDetector: GestureDetector? = null
    override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
        gestureEvent(rv, e)
        return false
    }

    override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {

    }

    override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
    }

    private fun gestureEvent(rv: RecyclerView, e: MotionEvent): Boolean {
        if (gestureDetector == null) {
            gestureDetector = GestureDetector(rv.context, object : OnGestureListener {
                override fun onShowPress(e: MotionEvent?) {

                }

                override fun onSingleTapUp(e: MotionEvent): Boolean {
                    return false
                }

                override fun onDown(e: MotionEvent?): Boolean {
                    return false
                }

                override fun onFling(
                    e1: MotionEvent?,
                    e2: MotionEvent?,
                    velocityX: Float,
                    velocityY: Float
                ): Boolean {
                    return false
                }

                override fun onScroll(
                    e1: MotionEvent?,
                    e2: MotionEvent?,
                    distanceX: Float,
                    distanceY: Float
                ): Boolean {
                    val view = rv.findChildViewUnder(e.x, e.y)
                    if (view != null) {

                        view.scrollBy(distanceX.toInt(), 0)
                        val scrollX = view.scrollX


                        val rect = Rect()
                        view.getGlobalVisibleRect(rect)


                        Log.i("ffffff", "scrollX:" + scrollX + "  right:" + view.right)
                        return true
                    }
                    return false
                }

                override fun onLongPress(e: MotionEvent) {

                }

            })
        }
        return gestureDetector!!.onTouchEvent(e)
    }
}