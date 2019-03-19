package com.example.xumengyin.mypractice.view;

import android.content.Context;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

public class DragHelperView extends ViewGroup
{
	ViewDragHelper mDragHelp;
	PointF[] pointFS;
	View content;
	View drawer;
	public DragHelperView(Context context, @Nullable AttributeSet attrs)
	{
		super(context, attrs);

		mDragHelp = ViewDragHelper.create(this, new DragcallBack());
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b)
	{
		//super.onLayout(changed, l, t, r, b);

		content =getChildAt(0);
		drawer =getChildAt(1);
		content.layout(-content.getMeasuredWidth(),(getMeasuredWidth()-content.getMeasuredHeight())/2,0,getMeasuredHeight()/2+content.getMeasuredHeight()/2);
		drawer.layout(0,(getMeasuredWidth()-drawer.getMeasuredHeight())/2,drawer.getMeasuredWidth(),getMeasuredHeight()/2+drawer.getMeasuredHeight()/2);
		if (pointFS == null)
		{
			pointFS = new PointF[getChildCount()];
			for (int i = 0; i < getChildCount(); i++)
			{
				View v = getChildAt(i);
				pointFS[i] = new PointF(v.getLeft(), v.getTop());
			}
		}
		drawer.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				mDragHelp.smoothSlideViewTo(drawer,content.getWidth(),drawer.getTop());
				invalidate();
			}
		});
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);
		int heightSize = MeasureSpec.getSize(heightMeasureSpec);

		setMeasuredDimension(widthSize, heightSize);
		final int childCount = getChildCount();
		for (int i = 0; i < childCount; i++)
		{
			final View child = getChildAt(i);

			if (child.getVisibility() == GONE)
			{
				continue;
			}

			measureChildren(widthMeasureSpec,heightMeasureSpec);
		}
	}

	@Override
	protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
		return new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
	}

	@Override
	protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
		return p instanceof LayoutParams
				? new LayoutParams((DragLayoutParams) p)
				: p instanceof ViewGroup.MarginLayoutParams
				? new DragLayoutParams((MarginLayoutParams) p)
				: new DragLayoutParams(p);
	}

	@Override
	protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
		return p instanceof DragLayoutParams && super.checkLayoutParams(p);
	}

	@Override
	public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
		return new DragLayoutParams(getContext(), attrs);
	}
	class DragLayoutParams extends MarginLayoutParams
	{

		public DragLayoutParams(Context c, AttributeSet attrs)
		{
			super(c, attrs);
		}

		public DragLayoutParams(int width, int height)
		{
			super(width, height);
		}

		public DragLayoutParams(MarginLayoutParams source)
		{
			super(source);
		}

		public DragLayoutParams(LayoutParams source)
		{
			super(source);
		}
	}
	@Override
	public void computeScroll()
	{
		if(mDragHelp.continueSettling(true))
		{
			ViewCompat.postInvalidateOnAnimation(this);
		}
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev)
	{
		return mDragHelp.shouldInterceptTouchEvent(ev);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		mDragHelp.processTouchEvent(event);
		return true;
	}

	private class DragcallBack extends ViewDragHelper.Callback
	{

		@Override
		public boolean tryCaptureView(View child, int pointerId)
		{

			return indexOfChild(child)==1;
		}

		@Override
		public int clampViewPositionHorizontal(View child, int left, int dx)
		{
			int x1=Math.max(left,0);
			return Math.min(x1,(content.getWidth()));
		}

		@Override
		public int clampViewPositionVertical(View child, int top, int dy)
		{
			return child.getTop();
		}

		@Override
		public void onViewReleased(View releasedChild, float xvel, float yvel)
		{
			/**
			 * 回到原来的位子
			 */
			int index =indexOfChild(releasedChild);
			if(releasedChild.getLeft()>content.getWidth()/2)
			{
				mDragHelp.settleCapturedViewAt(content.getWidth(),releasedChild.getTop());
			}else
			{
				mDragHelp.settleCapturedViewAt((int) pointFS[index].x,(int)pointFS[index].y);
			}
			invalidate();


		}

		@Override
		public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy)
		{
			if(changedView==drawer)
			{
				ViewCompat.offsetLeftAndRight(content,dx);
			}
		}
	}
}
