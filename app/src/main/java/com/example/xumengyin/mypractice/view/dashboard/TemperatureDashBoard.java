package com.example.xumengyin.mypractice.view.dashboard;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.example.xumengyin.mypractice.R;

public class TemperatureDashBoard extends BaseDashBoard
{
	int initDegree = -70;

	public TemperatureDashBoard(@NonNull Context context, @Nullable AttributeSet attrs)
	{
		super(context, attrs);


	}

	@Override
	int inflateLayoutId()
	{
		return R.layout.dashboard_temputre_layout;
	}

	@Override
	float convertFromDataToDegrees(float data)
	{
		float realData = 35;
		// 冷却液温 -40~110
		if (data < -40)
		{
			data = -40;
		} else if (data > 110)
		{
			data = 110;
		}
		realData = (140 * (data + 40) / (110 + 40))
				+ initDegree;

		return realData;
	}

	@Override
	protected void setTextValue(float data)
	{
		vTextView.setText(((int)data+"℃"));
	}
}
