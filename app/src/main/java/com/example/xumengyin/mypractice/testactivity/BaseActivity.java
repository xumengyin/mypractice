package com.example.xumengyin.mypractice.testactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.xumengyin.mypractice.R;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/12/27.
 */

public abstract class BaseActivity extends AppCompatActivity
{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(getcontentView());
		ButterKnife.bind(this);

	}
	protected abstract int getcontentView();

	protected void logMsg(String msg)
	{
		Log.e(getClass().getSimpleName(),msg);
	}
}
