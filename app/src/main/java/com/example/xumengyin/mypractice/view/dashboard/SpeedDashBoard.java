package com.example.xumengyin.mypractice.view.dashboard;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.example.xumengyin.mypractice.R;

public class SpeedDashBoard extends BaseDashBoard
{
	//0刻度的旋转角度  36度一格,4格
	private int initDegree=-144;
	public SpeedDashBoard(@NonNull Context context, @Nullable AttributeSet attrs)
	{
		super(context, attrs);


	}

	@Override
	int inflateLayoutId()
	{
		return R.layout.speed_dashboard_layout;
	}

	@Override
	float convertFromDataToDegrees(float data)
	{
		//速度 0-240
		float degrees=0;
		if(data<=0)
		{
			data=0;
		}else if(data>240)
		{
			data=240;
		}
		degrees=data/240*(360-72)+initDegree;
		return degrees;
	}

}
