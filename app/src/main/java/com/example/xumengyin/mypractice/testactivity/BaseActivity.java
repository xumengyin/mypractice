package com.example.xumengyin.mypractice.testactivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.xumengyin.mypractice.net.IBaseView;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/12/27.
 */ 

public abstract class BaseActivity extends AppCompatActivity implements IBaseView
{
	ProgressDialog dialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(getcontentView());
		ButterKnife.bind(this);
		//getLifecycle()

	}
	protected abstract int getcontentView();

	protected void logMsg(String msg)
	{
		Log.e(getClass().getSimpleName(),msg);
	}

	@Override
	protected void onDestroy()
	{
		super.onDestroy();
	}

	@Override
	public void showLoading()
	{
		//ProgressDialog
		dialog=new ProgressDialog(this);
		dialog.show();
	}

	@Override
	public void hideLoading()
	{
		if(dialog!=null&&dialog.isShowing())
		{
			dialog.dismiss();
			dialog=null;
		}
	}
}
