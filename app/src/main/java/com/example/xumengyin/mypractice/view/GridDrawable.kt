package com.example.xumengyin.mypractice.view

import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import com.example.xumengyin.mypractice.net.PackageData.init

class GridDrawable(context: Context) : Drawable() {

    val paint: Paint
    val gapSize:Int
    val strokeSize=5f
    init {
        paint = Paint().apply {
            color=Color.RED
            strokeWidth=strokeSize
        }
        gapSize=24
    }

    override fun draw(canvas: Canvas) {
        val bound=bounds
        val height =bound.height().toFloat()
        val width=bound.width()
        if (height == 0f)
            return
        val left =bound.left.toFloat()
        val right =bound.right.toFloat()
        val top =bound.top.toFloat()
        val bottom =bound.bottom.toFloat()
        val verticalStep=Math.floor(height.toDouble()/gapSize).toInt()
        val horizontalStep=Math.floor(width.toDouble()/gapSize).toInt()
        var y :Float
        var x :Float

        val lines = mutableListOf<Float>()
        for (i in 0..verticalStep)
        {
            y=top+(gapSize+strokeSize/2)*i
            lines.add(left)
            lines.add(y)
            lines.add(right)
            lines.add(y)
        }
        for (i in 0..horizontalStep)
        {
            x=left+(gapSize+strokeSize/2)*i
            lines.add(x)
            lines.add(top)
            lines.add(x)
            lines.add(bottom)
        }

        canvas.drawLines(lines.toFloatArray(),paint)

    }

    override fun setAlpha(alpha: Int) {
        paint.alpha=alpha
    }

    override fun getOpacity()= PixelFormat.TRANSLUCENT

    override fun setColorFilter(colorFilter: ColorFilter?) {
       paint.colorFilter=colorFilter
    }

}