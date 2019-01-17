package com.example.xumengyin.mypractice;

import android.content.Context;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View; 
import android.widget.Button;
import android.widget.TextView;

/**
 * 自定义behavior，拦截布局和触摸事件
 */

public class MyBehavior extends CoordinatorLayout.Behavior<View>
{
	Rect outRect =new Rect();
	public MyBehavior(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	@Override
	public boolean onLayoutChild(CoordinatorLayout parent, View child, int layoutDirection)
	{
		View preView=getPreView(parent,child);
		parent.onLayoutChild(child,layoutDirection);
		if(preView!=null)
		{
			CoordinatorLayout.LayoutParams params= (CoordinatorLayout.LayoutParams) child.getLayoutParams();
			ViewCompat.offsetTopAndBottom(child,preView.getTop()+preView.getMeasuredHeight()+params.topMargin);
		}
		return true;
	}
	private View getPreView(CoordinatorLayout parent, View child)
	{
		int index=parent.indexOfChild(child);
		View preView=null;
		if(index-1>=0)
			preView= parent.getChildAt(index-1);
		return preView;
	}

	@Override
	public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency)
	{
		return dependency instanceof Button;
	}

	Point p=new Point();
	@Override
	public boolean onTouchEvent(CoordinatorLayout parent, View child, MotionEvent ev)
	{
		child.getGlobalVisibleRect(outRect);
		if(!outRect.contains((int) ev.getRawX(),(int) ev.getRawY()))
		{
			return false;
		}
		if(ev.getAction()==MotionEvent.ACTION_MOVE)
		{
			for(View view:parent.getDependencies(child))
			{
				ViewCompat.offsetTopAndBottom(view, (int) (ev.getY()-p.y));
				ViewCompat.offsetLeftAndRight(view, (int) (ev.getX()-p.x));
			}

			p.set((int) ev.getX(),(int) ev.getY());
		}else if(ev.getAction()==MotionEvent.ACTION_DOWN)
		{
			p.set((int) ev.getX(),(int) ev.getY());
		}
		return true;
	}

	@Override
	public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency)
	{
//		dependency.gett
		ViewCompat.offsetTopAndBottom(child,10);
		return true;
	}

	@Override
	public boolean onInterceptTouchEvent(CoordinatorLayout parent, View child, MotionEvent ev)
	{
		boolean intercept=false;
		child.getGlobalVisibleRect(outRect);
	//	child.getDrawingRect(outRect);
	//	child.getLocalVisibleRect(outRect);
		if(outRect.contains((int) ev.getRawX(),(int) ev.getRawY()))
			intercept=true;
		else intercept=false;
		return intercept;
	}
}
