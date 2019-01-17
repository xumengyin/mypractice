package com.example.xumengyin.mypractice.testactivity;

import android.support.graphics.drawable.ArgbEvaluator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.xumengyin.mypractice.R;

public class ArgbTestActivity extends AppCompatActivity
{

	LinearLayout vLayout;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_argb_test);
		vLayout=findViewById(R.id.line);

		for (int i=0;i<sum;i++)
		{
			View v =new View(this);
			v.setLayoutParams(new LinearLayout.LayoutParams(200,100));
			v.setBackgroundColor(getMapColor(i,sum));
			vLayout.addView(v);
		}

	}
	ArgbEvaluator evaluator = new ArgbEvaluator();
	int startColor=0x000000FF;
	int endColor=0xffFF7F00;
	int sum=20;
	public int getMapColor(int index,int sum)
	{
		return (int) evaluator.evaluate(index/(float)sum,startColor,endColor);
	}
}
