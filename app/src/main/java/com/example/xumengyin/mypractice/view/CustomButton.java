package com.example.xumengyin.mypractice.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.Gravity;

import com.example.xumengyin.mypractice.R;
//test
public class CustomButton extends AppCompatButton
{
	float allWidth;
	float textWidth;
	Drawable drawable;
	int defaultMargin=100;
	public CustomButton(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		drawable=getResources().getDrawable(R.mipmap.ic_launcher);
		drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right, int bottom)
	{
		super.onLayout(changed, left, top, right, bottom);
		textWidth =getPaint().measureText(getText().toString());
		float drawableWidth=drawable.getIntrinsicWidth();
		allWidth=defaultMargin+drawableWidth+textWidth;
		setPadding(0,0,(int) ((getWidth()-textWidth)),0);
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
//		canvas.save();
//		canvas.translate(defaultMargin+drawable.getIntrinsicWidth(),0);
		super.onDraw(canvas);
		//canvas.restore();
		//drawable.draw(canvas);

	}
}
