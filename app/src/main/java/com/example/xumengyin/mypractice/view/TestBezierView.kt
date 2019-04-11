package com.example.xumengyin.mypractice.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

class TestBezierView (context:Context,attrs: AttributeSet?=null):View(context,attrs)
{
    private var bezierWidth=0
    private var bezierHeight=0
    val paint:Paint
    val path:Path
    init {
        paint=Paint(Paint.ANTI_ALIAS_FLAG).apply {
            style=Paint.Style.FILL
            color=Color.RED
        }
        path=Path()
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        bezierWidth=right-left
        bezierHeight=bottom-top
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.let {
            drawBezia(it)
        }
    }

    fun drawBezia(canvas: Canvas)
    {

        path.reset()

        /**
         * Start point for drawing
         */
        path.moveTo(0f, bezierHeight.toFloat())

        /**
         * Seth half path of bezier view
         */
        path.cubicTo((bezierWidth / 4).toFloat(), bezierHeight.toFloat(), (bezierWidth / 4).toFloat(), 0f, (bezierWidth / 2).toFloat(), 0f)

        /**
         * Seth second part of bezier view
         */
        path.cubicTo((bezierWidth / 4 * 3).toFloat(), 0f, (bezierWidth / 4 * 3).toFloat(), bezierHeight.toFloat(), bezierWidth.toFloat(), bezierHeight.toFloat())

        /**
         * Draw our bezier view
         */
        canvas.drawPath(path, paint)
    }
}