package com.example.xumengyin.mypractice.view.dashboard;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.example.xumengyin.mypractice.R;
import com.example.xumengyin.mypractice.view.SimpleCricleProgress;

public class OilDashBoard extends BaseDashBoard
{
	int initDegree = -70;
	SimpleCricleProgress progress;
	public OilDashBoard(@NonNull Context context, @Nullable AttributeSet attrs)
	{
		super(context, attrs);
		progress=getView().findViewById(R.id.simpleProgress);
		progress.setStrokeGradintColor(new int[]{0X4cfde43a, 0X4c57d91a,0X4cec2004,0X4cfede03},new int[]{0XFFfde43a, 0XFF57d91a,0XFFec2004,0XFFfede03});

	}

	@Override
	int inflateLayoutId()
	{
		return R.layout.oil_dash_layout;
	}

	@Override
	float convertFromDataToDegrees(float data)
	{

		return data*100;
	}

	@Override
	public void setValueData(float data)
	{
		setTextValue(data);
		progress.setProgressWithAnimation(convertFromDataToDegrees(data));
	}

	@Override
	protected void setTextValue(float data)
	{
		vTextView.setText(data+"%");
	}
}
