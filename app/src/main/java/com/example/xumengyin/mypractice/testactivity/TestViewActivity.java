package com.example.xumengyin.mypractice.testactivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;

import com.example.xumengyin.mypractice.R;
import com.example.xumengyin.mypractice.view.VerScrollText;

import butterknife.BindView;
import butterknife.OnClick;

public class TestViewActivity extends BaseActivity
{
	@BindView(R.id.image)
	ImageView image;
	String picData ="";
	@BindView(R.id.text)
	VerScrollText text;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		text.setText("111");
	}

	@Override
	protected int getcontentView()
	{
		return R.layout.activity_test_view;
	}

	@OnClick(R.id.button)
	public void startAni(View view)
	{
		text.startAni();
		testParseData();
	}
	//解析base64 图片
	private void testParseData()
	{
		byte[] bitmapData = Base64.decode(picData, Base64.DEFAULT);
		Bitmap bitmap = BitmapFactory.decodeByteArray(bitmapData, 0, bitmapData.length);
		image.setImageBitmap(bitmap);
	}
}
