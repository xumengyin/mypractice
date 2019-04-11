package com.example.xumengyin.mypractice.view.dashboard;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.xumengyin.mypractice.R;

public abstract class BaseDashBoard extends FrameLayout
{
	protected View vIndicator;
	protected TextView vTextView;
	View view;
	public BaseDashBoard(@NonNull Context context, @Nullable AttributeSet attrs)
	{
		super(context, attrs);
		initDashBoard(attrs);
	}

	private void initDashBoard(AttributeSet attrs)
	{
		view = LayoutInflater.from(getContext()).inflate(inflateLayoutId(), this, true);
		vIndicator = view.findViewById(R.id.indicator);
		vTextView = view.findViewById(R.id.value_text);

	}

	public View getView()
	{
		return view;
	}
	abstract int inflateLayoutId();

	abstract float convertFromDataToDegrees(float data);

	public void setValueData(float data)
	{
		float degrees=convertFromDataToDegrees(data);
		vIndicator.setPivotX(vIndicator.getWidth()/2f);
		vIndicator.setPivotY(vIndicator.getHeight());
		vIndicator.animate().rotation(degrees).setDuration(2000).start();
		setTextValue(data);
	}
	protected void setTextValue(float data)
	{
		vTextView.setText(((int) data)+"");
	}
}
