package com.example.xumengyin.mypractice;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;

public class TestFramLayout extends FrameLayout
{
	public TestFramLayout(Context context, AttributeSet attrs) 
	{
		super(context, attrs);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		Log.d("xuxu", "TestFramLayout onMeasure: ");
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right, int bottom)
	{
		super.onLayout(changed, left, top, right, bottom);
		Log.d("xuxu", "TestFramLayout onLayout: ");
	}

}
