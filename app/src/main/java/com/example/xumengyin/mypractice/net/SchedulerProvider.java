package com.example.xumengyin.mypractice.net;

import io.reactivex.ObservableTransformer;

public interface SchedulerProvider
{
	<T> ObservableTransformer<T, T> applySchedulers();
}
