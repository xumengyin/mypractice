package com.example.xumengyin.mypractice.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.xumengyin.mypractice.R;

/**
 * 4glite的布局
 */
public class FourLiteView extends FrameLayout
{
	private ImageView vDrawer;
	private View vBtnLayout;
	public boolean isExpend = false;
	private ObjectAnimator animator;
	long duration = 1 * 1000;
	private int contentHeight;
	State mState = State.READY;
	PanelCallBack callBack;
	private enum State
	{
		ANIMATING, READY
	}

	public void setPanelCallBack(PanelCallBack callBack)
	{
		this.callBack = callBack;
	}

	public FourLiteView(Context context)
	{
		super(context);
		init();
	}

	public FourLiteView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		init();
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right, int bottom)
	{
		super.onLayout(changed, left, top, right, bottom);
		contentHeight = vBtnLayout.getMeasuredHeight();
	}

	private void init()
	{
		View v = LayoutInflater.from(getContext()).inflate(R.layout.fourlite_layout, this, true);
		vDrawer = v.findViewById(R.id.drawer);
		vBtnLayout = v.findViewById(R.id.btn_layout);
		vDrawer.setOnTouchListener(new OnTouchListener()
		{
			@Override
			public boolean onTouch(View v, MotionEvent event)
			{
				if (event.getActionMasked() == MotionEvent.ACTION_UP)
				{
					post(animateRun);
				}
				return true;
			}
		});
		this.setTranslationY(-150*3);
	}

	Runnable animateRun = new Runnable()
	{
		@Override
		public void run()
		{
			doanimate();
		}
	};

	private void doanimate()
	{

		if (mState == State.ANIMATING||(animator!=null&&animator.isRunning()))
		{
			return;
		}
		float startValue = 0, endValue = 0;
		if (isExpend)
		{
			startValue = 0;
			endValue = -contentHeight;
		} else
		{
			startValue = -contentHeight;
			endValue = 0;
		}
		animator = ObjectAnimator.ofFloat(this,"TranslationY",startValue,endValue);
		animator.setDuration(duration);
		animator.addListener(new AnimatorListenerAdapter()
		{
			@Override
			public void onAnimationStart(Animator animation)
			{
				super.onAnimationStart(animation);
				mState = State.ANIMATING;
			}

			@Override
			public void onAnimationEnd(Animator animation)
			{
				super.onAnimationEnd(animation);
				isExpend = !isExpend;
				animator = null;
				mState = State.READY;
				if(isExpend)
				{
					callBack.onPanelOpened();
				}
				else
				{
					callBack.onPanelClosed();
				}
			}
			});

		animator.start();
	}
	public interface PanelCallBack
	{
		public void onPanelClosed();

		public void onPanelOpened();
	}
}
