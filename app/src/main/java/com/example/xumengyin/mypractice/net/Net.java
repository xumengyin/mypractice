package com.example.xumengyin.mypractice.net;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public  class Net
{
	private static Net mInstance;
	private static Retrofit retrofit;
	static Object o =new Object();
	public static Net getInstance()
	{
		if(mInstance==null)
		{
			synchronized (o)
			{
				if(mInstance==null)
				{
					mInstance=new Net();
				}
			}
		}
		return mInstance;
	}

	private Net()
	{
		init();
	}
	private void init()
	{
		HttpLoggingInterceptor interceptor=new HttpLoggingInterceptor();
		interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
		OkHttpClient client = new OkHttpClient.Builder()
				.addInterceptor(new HeaderInterceptor())
				.addInterceptor(interceptor)
				.build();

		// 初始化Retrofit
		retrofit = new Retrofit.Builder()
				.client(client)
				.baseUrl(baseUrl())
				.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
				.addConverterFactory(GsonConverterFactory.create())
				.build();
	}
	protected  String baseUrl()
	{
		return "https://test.cpsdna.com/";
	}
	public <T> T request(Class<T>t)
	{
		return retrofit.create(t);
	}
}
