package com.example.xumengyin.mypractice.testactivity;

import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.TextView;

import com.example.xumengyin.mypractice.R;

import butterknife.BindView; 

/**
 * 使用26兼容库 自动缩放字体大小
 */
public class AutoTextActivity extends BaseActivity
{
	@BindView(R.id.auto_text)
	TextView vAutoText;
	@Override
	protected int getcontentView()
	{
		return R.layout.activity_auto_text;
	}
 
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
//		TextViewCompat.setAutoSizeTextTypeWithDefaults(vAutoText,TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM);
//		TextViewCompat.setAutoSizeTextTypeUniformWithConfiguration(vAutoText,
//				12, 48, 2, TypedValue.COMPLEX_UNIT_SP);
		//vAutoText.setText("afafafafafafafafkkk");
	}
}
