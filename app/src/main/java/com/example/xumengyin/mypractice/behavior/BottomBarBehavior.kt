package com.example.xumengyin.mypractice.behavior

import android.content.Context
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CoordinatorLayout
import android.support.v4.view.ViewCompat
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.example.xumengyin.mypractice.R
import com.example.xumengyin.mypractice.kotlin.logmsg
import com.example.xumengyin.mypractice.view.CardListView
import kotlin.math.log

class BottomBarBehavior() : CoordinatorLayout.Behavior<View>() {



    constructor(context: Context, attrs: AttributeSet) : this() {

    }


    override fun onDependentViewChanged(parent: CoordinatorLayout, child: View, dependency: View): Boolean {
        dependency.apply {
            logmsg(""+this?.y)
        }
        return true
    }

    override fun layoutDependsOn(parent: CoordinatorLayout, child: View, dependency: View): Boolean {
        return dependency.id==R.id.anchorview
    }


}