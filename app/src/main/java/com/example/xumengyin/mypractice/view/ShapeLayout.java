package com.example.xumengyin.mypractice.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.FrameLayout;


public class ShapeLayout extends FrameLayout
{
	Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
	Path path = new Path();
	PorterDuffXfermode mode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);

	public ShapeLayout(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		init();
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right, int bottom)
	{
		super.onLayout(changed, left, top, right, bottom);
//		if (changed) {
//			postInvalidate();
//		}
	}

	private void init()
	{
		setWillNotDraw(false);
		setDrawingCacheEnabled(true);
		paint.setColor(Color.BLUE);
		paint.setStyle(Paint.Style.FILL);
		paint.setXfermode(mode);
		setLayerType(LAYER_TYPE_SOFTWARE, null);
	}

	@Override
	protected void dispatchDraw(Canvas canvas)
	{
		super.dispatchDraw(canvas);
		setOutlineProvider(getOutlineProvider());
		path.reset();
		path.addCircle(getMeasuredWidth() / 2, getMeasuredHeight() / 2, Math.min(getMeasuredWidth() / 2, getMeasuredHeight() / 2)-30, Path.Direction.CW);
		canvas.drawPath(path, paint);
		setLayerType(LAYER_TYPE_HARDWARE, null);
	}

	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	@Override
	public ViewOutlineProvider getOutlineProvider()
	{
		return new ViewOutlineProvider()
		{
			@Override
			public void getOutline(View view, Outline outline)
			{
					Path p=new Path();
					p.addCircle(getMeasuredWidth() / 2, getMeasuredHeight() / 2, Math.min(getMeasuredWidth() / 2, getMeasuredHeight() / 2), Path.Direction.CW);
					outline.setConvexPath(p);



			}
		};
	}
}
