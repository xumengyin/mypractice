package com.example.xumengyin.mypractice.net;

import android.arch.core.util.Function;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.Transformations;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.xumengyin.mypractice.bean.GutHubUser;
import com.example.xumengyin.mypractice.bean.InitBean;
import com.example.xumengyin.mypractice.bean.Repo;
import com.example.xumengyin.mypractice.util.L;

import java.util.List;

public class GitHubPrester extends BasePrester implements GithubContract.P
{

	int count=1;
	MutableLiveData <String> liveData =new MutableLiveData<>();

	/**
	 * switchMap 和Map操作 将一个livedata和另一个livedata变换联系起来，艹 真牛逼
	 */
	public LiveData <Integer> liveDataMap= Transformations.switchMap(liveData, new Function<String, LiveData<Integer>>()
	{
		@Override
		public LiveData<Integer> apply(String input)
		{
			MutableLiveData <Integer> liveDatas =new MutableLiveData<>();
			liveDatas.setValue(count++);
			return liveDatas;
		}
	});
	TestDataResponsity responsity ;
	GithubContract.View view;
	public GitHubPrester(LifecycleOwner lifecycleOwner, GithubContract.View baseView)
	{
		super(lifecycleOwner, baseView);
		view=baseView;
		responsity=new TestDataResponsity((Context) lifecycleOwner);
	}

	@Override
	public void getGithubRpno(String name)
	{
		responsity.getGitHUb(name).observe(lifecycleOwner, new UiObserver<List<Repo>>()
		{
			@Override
			public void uiSuccess(List<Repo> data)
			{
				//Log.d("")
				view.rpnoData(data);
			}

			@Override
			public void uiFailed(Throwable throwable)
			{
				super.uiFailed(throwable);
			}
		});
	}

	@Override
	public void getGitHUbUser(String name)
	{
		responsity.getGitHUbUser(name).observe(lifecycleOwner, new UiObserver<GutHubUser>()
		{
			@Override
			public void uiSuccess(GutHubUser data)
			{
				L.logE("net","got it");
			}
		});
	}

	@Override
	public void cpsdnaInit(String data)
	{
		responsity.cpsdnaInit(data).observe(lifecycleOwner, new UiObserver<InitBean>(view)
		{
			@Override
			public void uiSuccess(InitBean data)
			{
				L.logE("net","got it" +data.toString());
			}

			@Override
			public void uiCache(InitBean data)
			{

				L.logE("net","cache data---got it" +data.toString());
			}
		});
		liveData.setValue(data);
	}
}
