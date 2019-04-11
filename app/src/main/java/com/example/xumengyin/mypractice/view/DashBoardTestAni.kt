package com.example.xumengyin.mypractice.view

import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.widget.FrameLayout
import com.example.xumengyin.mypractice.R

class DashBoardTestAni(context: Context, attrs: AttributeSet?=null):FrameLayout(context, attrs)
{
    val vIndicator:View
    var mProgress:Float=0f
    init {
      val view=  LayoutInflater.from(context).inflate(R.layout.dashboard_ani_layout,this,true)
        vIndicator= view.findViewById(R.id.indicator)
    }


    fun setProgress(progress:Float)
    {
        mProgress=progress
       // animateProgress()

    }
    fun animateProgress(progress: Float,duration: Int)
    {
        vIndicator.pivotX= (vIndicator.width/2).toFloat()
        vIndicator.pivotY= vIndicator.height.toFloat()
        val objectAnimator = ObjectAnimator.ofFloat(vIndicator, "progress", progress)
        objectAnimator.duration = duration.toLong()
        objectAnimator.interpolator = DecelerateInterpolator()
        objectAnimator.start()
    }
}