package com.example.xumengyin.mypractice.view.dashboard;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.example.xumengyin.mypractice.R;

public class VoltageDashBoard extends BaseDashBoard
{
	int initDegree = -70;

	public VoltageDashBoard(@NonNull Context context, @Nullable AttributeSet attrs)
	{
		super(context, attrs);


	}

	@Override
	int inflateLayoutId()
	{
		return R.layout.dashboard_voatage_layout;
	}

	@Override
	float convertFromDataToDegrees(float data)
	{
		float realData = 35;
		//  电压范围11.0～15.0
		if (data < 11) {
			data = 11;
		} else if (data > 15) {
			data = 15;
		}
		realData = (float) (140 * ((data - 11) / (15.0 - 11.0)))
				+ initDegree;// 140是电压表的弧度
		return realData;
	}

	@Override
	protected void setTextValue(float data)
	{
		vTextView.setText(((int)data+"V"));
	}
}
