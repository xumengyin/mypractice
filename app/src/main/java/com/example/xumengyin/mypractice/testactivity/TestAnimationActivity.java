package com.example.xumengyin.mypractice.testactivity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Path;
import android.support.animation.DynamicAnimation;
import android.support.animation.SpringAnimation;
import android.support.animation.SpringForce;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.PathInterpolator;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xumengyin.mypractice.R;


import butterknife.BindView;
import butterknife.OnClick;

/**
 * 动画 见https://developer.android.com/training/animation
 */
public class TestAnimationActivity extends BaseActivity
{
	@BindView(R.id.image)
	ImageView lol;
	@BindView(R.id.move_text)
	TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
	}

	@Override
	protected int getcontentView()
	{
		return R.layout.activity_test_animation;
	}

	@OnClick(R.id.move_text)
	public void change(View view)
	{
		textView.setText("111", TextView.BufferType.EDITABLE);
	}
	@OnClick(R.id.button2)
	public void animate2(View view)
	{
		Animator anim = ViewAnimationUtils.createCircularReveal(lol, lol.getWidth()/2, lol.getHeight()/2, 0f, 200);
		anim.start();
	}
	@OnClick(R.id.button3)
	public void animate3(View view)
	{
		//spring 弹簧动画
		SpringAnimation animation =new SpringAnimation(lol, DynamicAnimation.TRANSLATION_Y,700);
		animation.setStartValue(0);
		animation.setMaxValue(1920);
		animation.getSpring().setStiffness(SpringForce.STIFFNESS_LOW);
		animation.start();
		//animation.animateToFinalPosition(700);
//		VelocityTracker tracker=VelocityTracker.obtain();
//		tracker.computeCurrentVelocity(1000);
	}


	@OnClick(R.id.button1)
	public void animate1(View view)
	{
		Path path=new Path();
		//path.addArc(0,0,1024,500,0,360);
		//path.moveTo(0,0);
		//path.arcTo(0f, 0f, 600, 600, 270, -180, true);
//		path.lineTo(1000,0);
//		path.lineTo(1000,300);
//		path.close();
		/**
		 * 直接用path的arcTo动画不行 曲线用quadTo可以，使用Pathmeasure
		 *
		 */
		//android.R.integer.config_shortAnimTime"
		path.moveTo(0,600);
		path.quadTo(300, 0, 600, 600);
		ObjectAnimator animator=ObjectAnimator.ofFloat(textView,"x","y",path);
		animator.setDuration(2000);
//		PathInterpolator pathInterpolator = new PathInterpolator(path);
//		animator.setInterpolator(pathInterpolator);
		animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
		{
			@Override
			public void onAnimationUpdate(ValueAnimator animation)
			{
				//animation.getAnimatedFraction()
				logMsg("value: x--"+animation.getAnimatedValue("x")+"---y:"+animation.getAnimatedValue("y"));
			}
		});
		//animator.setRepeatCount(-1);
		animator.start();
	}
}
