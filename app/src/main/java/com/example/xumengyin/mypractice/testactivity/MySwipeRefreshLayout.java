package com.example.xumengyin.mypractice.testactivity;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.MotionEvent;

/**
 * Created by Administrator on 2017/12/28.
 */
 
public class MySwipeRefreshLayout extends SwipeRefreshLayout

{
	public MySwipeRefreshLayout(Context context)
	{
		super(context);
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev)
	{
		return super.onTouchEvent(ev);
	}
}
