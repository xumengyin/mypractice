package com.example.xumengyin.mypractice.testactivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.xumengyin.mypractice.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class CustomViewAty extends BaseActivity
{
	private String[] mPermission1 = new String[]{Manifest.permission.READ_PHONE_STATE, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE};
	public static final String[] mPermission2 = new String[]{Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_CONTACTS,Manifest.permission.WRITE_CONTACTS,
			Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CALL_PHONE, Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO,
			Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.PROCESS_OUTGOING_CALLS};
	@BindView(R.id.button1)
	Button btn1;
	@BindView(R.id.button2)
	Button btn2;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
	}

	@Override
	protected int getcontentView()
	{
		return R.layout.activity_custom_view_aty;
	}

	@OnClick(R.id.button1)
	public void premission1()
	{
		checkPermission(1);
	}
	@OnClick(R.id.button2)
	public void premission2()
	{
		checkPermission(2);
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
	{
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		for (int result : grantResults)
		{
			if (result != PackageManager.PERMISSION_GRANTED)
			{
				logMsg("fuck no Permissions");
				break;
			}
		}
	}

	private void checkPermission(int type)
	{
		String[] requestPermissions = null;
		if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M)
		{
			boolean needGrantedPermissions = false;

			//每个页面需要的最小权限不一样
			if (type == 1)
			{
				requestPermissions = mPermission1;
			} else if (type == 2)
			{
				requestPermissions = mPermission2;
			}
			List<String> needRequest = new ArrayList<>();
			for (int i = 0, size = requestPermissions.length; i < size; i++)
			{
				if (ActivityCompat.checkSelfPermission(this, requestPermissions[i])
						!= PackageManager.PERMISSION_GRANTED)
				{
					needGrantedPermissions = true;
					needRequest.add(requestPermissions[i]);
				}
			}
			if (needGrantedPermissions)
			{
				requestPermissions = new String[needRequest.size()];
				needRequest.toArray(requestPermissions);
				ActivityCompat.requestPermissions(this, requestPermissions, 111);
			} else
			{
				logMsg("okkkkkkkkkkkkk");
			}
		}
	}


}
