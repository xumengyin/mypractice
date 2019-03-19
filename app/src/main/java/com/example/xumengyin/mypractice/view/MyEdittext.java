package com.example.xumengyin.mypractice.view;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.widget.EditText;

public class MyEdittext extends AppCompatEditText
{
	public MyEdittext(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

	@Override
	public void setText(CharSequence text, BufferType type)
	{
		super.setText(text, type);
	}
}
