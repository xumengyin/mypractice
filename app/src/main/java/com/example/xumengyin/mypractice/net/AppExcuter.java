package com.example.xumengyin.mypractice.net;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppExcuter
{

	private final Executor diskIO;

	private final Executor networkIO;

	private final Executor mainThread;
	private static AppExcuter mAppExecutors;

	public static AppExcuter getInstance()
	{
		if (mAppExecutors == null)
		{
			synchronized (AppExcuter.class)
			{
				mAppExecutors = new AppExcuter();
			}
		}
		return mAppExecutors;

	}

	public AppExcuter(Executor diskIO, Executor networkIO, Executor mainThread)
	{
		this.diskIO = diskIO;
		this.networkIO = networkIO;
		this.mainThread = mainThread;
	}

	private AppExcuter()
	{
		this(Executors.newSingleThreadExecutor(), Executors.newFixedThreadPool(3),
				new MainThreadExecutor());
	}

	public Executor diskIO()
	{
		return diskIO;
	}

	public Executor networkIO()
	{
		return networkIO;
	}

	public Executor mainThread()
	{
		return mainThread;
	}

	private static class MainThreadExecutor implements Executor
	{
		private Handler mainThreadHandler = new Handler(Looper.getMainLooper());

		@Override
		public void execute(@NonNull Runnable command)
		{
			mainThreadHandler.post(command);
		}
	}
}
