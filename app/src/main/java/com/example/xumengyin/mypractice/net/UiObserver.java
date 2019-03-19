package com.example.xumengyin.mypractice.net;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;

import com.example.xumengyin.mypractice.util.L;

public abstract class UiObserver<T> implements Observer<LiveDataResponse<T>>
{
	IBaseView uiView;
	public UiObserver()
	{
		this(null);
	}
	public UiObserver(IBaseView view)
	{
		this.uiView=view;
	}

	@Override
	public void onChanged(@Nullable LiveDataResponse<T> tLiveDataResponse)
	{
		if (tLiveDataResponse.status == Status.LOADING)
		{
			uiLoading();
		} else if (tLiveDataResponse.status == Status.SUCCESS)
		{
			uiSuccess(tLiveDataResponse.data);
		} else if (tLiveDataResponse.status == Status.THROWABLE)
		{
			uiFailed(tLiveDataResponse.mException);
		}else if(tLiveDataResponse.status == Status.COMPLETE)
		{
			uiFinish();
		}else if(tLiveDataResponse.status == Status.CACHE)
		{
			uiCache(tLiveDataResponse.data);
		}
	}

	public abstract void uiSuccess(T data);
	public void uiCache(T data)
	{

	}

	public void uiFailed(Throwable throwable)
	{
		L.logD("net",throwable.getMessage());
	}
	public void uiLoading()
	{
		if(uiView!=null)
		{
			uiView.showLoading();
		}
	}
	public void uiFinish()
	{
		if(uiView!=null)
		{
			uiView.hideLoading();
		}
	}

}
