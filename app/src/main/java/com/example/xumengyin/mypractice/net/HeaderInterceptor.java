package com.example.xumengyin.mypractice.net;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HeaderInterceptor implements Interceptor
{
	@Override
	public Response intercept(Chain chain) throws IOException
	{
		Request request = chain.request()
			.newBuilder()
			.addHeader("fuck", "fuckkkkk1")

			.build();
		return chain.proceed(request);
	}
}
