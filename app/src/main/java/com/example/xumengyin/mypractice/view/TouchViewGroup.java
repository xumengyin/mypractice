package com.example.xumengyin.mypractice.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import static com.example.xumengyin.mypractice.view.TouchView.TEST_TAG;

public class TouchViewGroup extends LinearLayout 
{
	private static final String TAG = "TouchViewGroup";

	public TouchViewGroup(Context context, @Nullable AttributeSet attrs)
	{
		super(context, attrs);
	}


	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		if (event.getAction() == MotionEvent.ACTION_DOWN)
		{
			Log.d(TEST_TAG, TAG + "onTouchEvent: ACTION_DOWN");
		} else if (event.getAction() == MotionEvent.ACTION_MOVE)
		{
			Log.d(TEST_TAG, TAG + "onTouchEvent: ACTION_MOVE");
		} else if (event.getAction() == MotionEvent.ACTION_UP)
		{
			Log.d(TEST_TAG, TAG + "onTouchEvent: ACTION_UP");
		} else if (event.getAction() == MotionEvent.ACTION_CANCEL)
		{
			Log.d(TEST_TAG, TAG + "onTouchEvent: ACTION_CANCEL");
		}
		return super.onTouchEvent(event);
	}


}
