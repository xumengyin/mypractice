package com.example.xumengyin.mypractice.behavior

import android.content.Context
import android.support.design.widget.CoordinatorLayout
import android.support.v4.view.ViewCompat
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.example.xumengyin.mypractice.kotlin.logmsg
import com.example.xumengyin.mypractice.view.CardListView
import kotlin.math.log

class CardBehavior() : CoordinatorLayout.Behavior<CardListView>() {



    constructor(context: Context, attrs: AttributeSet) : this() {

    }
    override fun onMeasureChild(parent: CoordinatorLayout, child: CardListView, parentWidthMeasureSpec: Int, widthUsed: Int, parentHeightMeasureSpec: Int, heightUsed: Int): Boolean {

        val parentHeight=View.MeasureSpec.getSize(parentHeightMeasureSpec)-child.headHeight*(parent.childCount-1)

        child.measure(parentWidthMeasureSpec,View.MeasureSpec.makeMeasureSpec(parentHeight,View.MeasureSpec.EXACTLY))
        return true
    }

    override fun onStartNestedScroll(coordinatorLayout: CoordinatorLayout, child: CardListView, directTargetChild: View, target: View, axes: Int, type: Int): Boolean {
        return axes.and(ViewCompat.SCROLL_AXIS_VERTICAL)!=0&&child==directTargetChild
    }

    override fun onNestedPreScroll(coordinatorLayout: CoordinatorLayout, child: CardListView, target: View, dx: Int, dy: Int, consumed: IntArray, type: Int) {
        this.logmsg("target:"+target+"--dy:"+dy)
        var view :View?=getPreView(coordinatorLayout,child)
        view.let {

            if(it is CardListView)
            {
                if(child.top>it.top+it.headHeight)
                    child.offsetTopAndBottom(-dy)
                consumed[1]=dy
            }
        }


    }

    override fun onNestedScroll(coordinatorLayout: CoordinatorLayout, child: CardListView, target: View, dxConsumed: Int, dyConsumed: Int, dxUnconsumed: Int, dyUnconsumed: Int, type: Int) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type)
    }
    override fun onLayoutChild(parent: CoordinatorLayout, child: CardListView, layoutDirection: Int): Boolean {
        parent.onLayoutChild(child,layoutDirection)
        var index=parent.indexOfChild(child)
        if(index>0)
        child.offsetTopAndBottom(index*child.headHeight)
        return true
    }
    fun getPreView(parent: CoordinatorLayout,child: CardListView):View?
    {
        var view:View?=null
       var index=parent.indexOfChild(child)
        if(index>0)
            view=parent.getChildAt(index-1)
        return view
    }
}