package com.example.xumengyin.mypractice.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.xumengyin.mypractice.R;

/**
 * 垂直滚动的view，重要的是思路
 */
public class VerScrollText extends View
{
	private String mText="";
	private Paint mPaint;
	//SP
	private int DEFAULT_SIZE = 16;
	private int DEFAULT_COLOR = Color.WHITE;
	private int DEFAULT_DURATION =5000;
	private int DEFAULT_TIMES =10;
	Handler handler = new Handler(Looper.getMainLooper());
	float baseLine;
	float curBaseLine;
	float textHeight;
	ValueAnimator animator;
	public VerScrollText(Context context, @Nullable AttributeSet attrs)
	{
		super(context, attrs);
		init();
	}

	private void init()
	{
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		int size = getResources().getDimensionPixelOffset(R.dimen.default_text_size);
		mPaint.setTextSize(size);
		mPaint.setColor(DEFAULT_COLOR);
		baseLine=-mPaint.getFontMetrics().ascent;
		textHeight =baseLine+mPaint.getFontMetrics().descent;
		curBaseLine=baseLine;
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
		//super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		int widthSize = MeasureSpec.getSize(widthMeasureSpec);
		int heightSize = MeasureSpec.getSize(heightMeasureSpec);
		if(heightMode== MeasureSpec.AT_MOST)
		{
			int height=MeasureSpec.makeMeasureSpec((int) (textHeight+0.5),MeasureSpec.EXACTLY);
			setMeasuredDimension(widthMeasureSpec,height);
		}
	}

	public void setText(String msg)
	{
		this.mText = msg;
		invalidate();
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
		drawText(canvas,curBaseLine);
		drawText(canvas,curBaseLine+textHeight);
	}

	private void drawText(Canvas canvas,float baseLineData)
	{
		canvas.drawText(mText,0,baseLineData,mPaint);
	}
	public void startAni()
	{
		animator=ValueAnimator.ofFloat(baseLine,textHeight*DEFAULT_TIMES+mPaint.getFontMetrics().descent);
		animator.setDuration(DEFAULT_DURATION);
		animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
		{
			@Override
			public void onAnimationUpdate(ValueAnimator animation)
			{
				float baselineData= (float) animation.getAnimatedValue();
				curBaseLine=baselineData%textHeight;
				invalidate();
			}
		});
		animator.start();
	}
}
