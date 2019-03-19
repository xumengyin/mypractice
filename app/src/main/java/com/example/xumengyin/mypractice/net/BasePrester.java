package com.example.xumengyin.mypractice.net;

import android.arch.lifecycle.LifecycleOwner;

public class BasePrester<T> implements IBasePrestener
{
	protected LifecycleOwner lifecycleOwner;
	protected T baseView;

	public BasePrester(LifecycleOwner lifecycleOwner, T baseView)
	{
		this.lifecycleOwner = lifecycleOwner;
		this.baseView = baseView;
	}
}
