package com.example.xumengyin.mypractice.net;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class NetSchedulers implements SchedulerProvider
{
	private static NetSchedulers instance;

	@Override
	public <T> ObservableTransformer<T, T> applySchedulers()
	{
		return new ObservableTransformer<T, T>()
		{
			@Override
			public ObservableSource<T> apply(Observable<T> upstream)
			{
				return upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
			}
		};
	}

	public static NetSchedulers getInstance()
	{
		if (instance == null)
			instance = new NetSchedulers();
		return instance;
	}


}
