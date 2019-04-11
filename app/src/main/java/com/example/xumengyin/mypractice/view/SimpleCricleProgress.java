package com.example.xumengyin.mypractice.view;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

import com.example.xumengyin.mypractice.R;

/**
 * Created by xu on 2018/3/7.简单的圆形进度
 */

public class SimpleCricleProgress extends View
{

	// Properties
	private float progress = 0;
	//    private float strokeWidth = getResources().getDimension(R.dimen.dimen_4);
//    private float backgroundStrokeWidth = getResources().getDimension(R.dimen.dimen_4);
	private float strokeWidth = 16;
	private float backgroundStrokeWidth = 16;
	private int color = Color.BLACK;
	private int endColor = Color.RED;
	private int backgroundColor = Color.GRAY;

	// Object used to draw
	private int startAngle = -0;
	private RectF rectF;
	private Paint backgroundPaint;
	private Paint foregroundPaint;
	int[] backGroundcolors;
	int[] fongroundcolors;

	//region Constructor & Init Method
	public SimpleCricleProgress(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		init(context, attrs);
	}

	private void init(Context context, AttributeSet attrs)
	{
		rectF = new RectF();
		TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CircularProgressBar, 0, 0);
		//Reading values from the XML layout
		try
		{
			// Value
			progress = typedArray.getFloat(R.styleable.CircularProgressBar_cpb_progress, progress);
			// StrokeWidth
			strokeWidth = typedArray.getDimension(R.styleable.CircularProgressBar_cpb_progressbar_width, strokeWidth);
			backgroundStrokeWidth = typedArray.getDimension(R.styleable.CircularProgressBar_cpb_background_progressbar_width, strokeWidth);
			// Color
			color = typedArray.getInt(R.styleable.CircularProgressBar_cpb_progressbar_color, color);
			endColor = typedArray.getInt(R.styleable.CircularProgressBar_cpb_progressbar_endcolor, endColor);
			backgroundColor = typedArray.getInt(R.styleable.CircularProgressBar_cpb_background_progressbar_color, backgroundColor);
		} finally
		{
			typedArray.recycle();
		}

		// Init Background
		backgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		backgroundPaint.setColor(backgroundColor);
		backgroundPaint.setStyle(Paint.Style.STROKE);
		backgroundPaint.setStrokeCap(Paint.Cap.ROUND);
		backgroundPaint.setStrokeWidth(backgroundStrokeWidth);
		initForeGroundPaint();

	}
	//endregion

	private void initForeGroundPaint()
	{
		// Init Foreground
		foregroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		//foregroundPaint.setColor(color);
		foregroundPaint.setStyle(Paint.Style.STROKE);
		foregroundPaint.setStrokeCap(Paint.Cap.ROUND);
		foregroundPaint.setStrokeWidth(strokeWidth);
	}

	//region Draw Method
	@Override
	protected void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
		canvas.rotate(-90, getMeasuredWidth() / 2, getMeasuredHeight() / 2);
		canvas.drawOval(rectF, backgroundPaint);
		float angle = 360 * progress / 100;
		canvas.drawArc(rectF, startAngle, angle, false, foregroundPaint);
	}
	//endregion

	//region Mesure Method
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
		final int height = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);
		final int width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
		final int min = Math.min(width, height);
		setMeasuredDimension(min, min);//
		float highStroke = (strokeWidth > backgroundStrokeWidth) ? strokeWidth : backgroundStrokeWidth;
		rectF.set(0 + highStroke / 2, 0 + highStroke / 2, min - highStroke / 2, min - highStroke / 2);
		//foregroundPaint.setShader(new SweepGradient(getMeasuredWidth()/2,getMeasuredHeight()/2,new int[]{color,endColor,endColor},new float[]{0f,0.4f,0.9f}));
		//fde43a~57d91a~ec2004~fede03
		//foregroundPaint.setShader(new SweepGradient((min - highStroke) / 2, (min - highStroke) / 2, new int[]{0XFFfde43a, 0XFF57d91a,0XFFec2004,0XFFfede03}, null));
		//foregroundPaint.setShader(new LinearGradient(0,min/2,min,min/2,color, endColor, Shader.TileMode.MIRROR));
		if (fongroundcolors != null)
			foregroundPaint.setShader(new SweepGradient(getWidth() / 2f, getHeight() / 2f, fongroundcolors, null));
		if (backGroundcolors != null)
			backgroundPaint.setShader(new SweepGradient(getWidth() / 2f, getHeight() / 2f, backGroundcolors, null));
	}

	public void setStrokeGradintColor(int[] backGroundcolors, int[] fongroundcolors)
	{
		this.backGroundcolors=backGroundcolors;
		this.fongroundcolors=fongroundcolors;
	}

	//region Method Get/Set
	public float getProgress()
	{
		return progress;
	}

	public void setProgress(float progress)
	{
		this.progress = (progress <= 100) ? progress : 100;
		//this.progress = 100;
		invalidate();
	}

	public float getProgressBarWidth()
	{
		return strokeWidth;
	}

	public void setProgressBarWidth(float strokeWidth)
	{
		this.strokeWidth = strokeWidth;
		foregroundPaint.setStrokeWidth(strokeWidth);
		requestLayout();//Because it should recalculate its bounds
		invalidate();
	}

	public float getBackgroundProgressBarWidth()
	{
		return backgroundStrokeWidth;
	}

	public void setBackgroundProgressBarWidth(float backgroundStrokeWidth)
	{
		this.backgroundStrokeWidth = backgroundStrokeWidth;
		backgroundPaint.setStrokeWidth(backgroundStrokeWidth);
		requestLayout();//Because it should recalculate its bounds
		invalidate();
	}

	public int getColor()
	{
		return color;
	}

	public void setColor(int color)
	{
		this.color = color;
		foregroundPaint.setColor(color);
		invalidate();
		requestLayout();
	}

	public int getBackgroundColor()
	{
		return backgroundColor;
	}

	public void setBackgroundColor(int backgroundColor)
	{
		this.backgroundColor = backgroundColor;
		backgroundPaint.setColor(backgroundColor);
		invalidate();
		requestLayout();
	}
	//endregion

	//region Other Method

	/**
	 * Set the progress with an animation.
	 * Note that the {@link ObjectAnimator} Class automatically set the progress
	 * so don't call the  CircularProgressBar#setProgress(float)} directly within this method.
	 *
	 * @param progress The progress it should animate to it.
	 */
	public void setProgressWithAnimation(float progress)
	{
		setProgressWithAnimation(progress, 1500);
	}

	/**
	 * Set the progress with an animation.
	 * Note that the {@link ObjectAnimator} Class automatically set the progress
	 * so don't call the  CircularProgressBar#setProgress(float)} directly within this method.
	 *
	 * @param progress The progress it should animate to it.
	 * @param duration The length of the animation, in milliseconds.
	 */
	public void setProgressWithAnimation(float progress, int duration)
	{
		ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(this, "progress", progress);
		objectAnimator.setDuration(duration);
		objectAnimator.setInterpolator(new DecelerateInterpolator());
		objectAnimator.start();
	}
	//endregion
}
