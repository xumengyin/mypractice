package com.example.xumengyin.mypractice.net;

public class LiveDataResponse<T>
{
	public  Status status;


	public String message;

	public T data;

	public Throwable mException;


	public LiveDataResponse setLoading()
	{
		status=Status.LOADING;
		return this;
	}
	public LiveDataResponse  setFinish()
	{
		status=Status.COMPLETE;
		return this;
	}
	public LiveDataResponse setSuccess(T data)
	{
		status=Status.SUCCESS;
		this.data=data;
		return this;
	}
	public LiveDataResponse setCache(T data)
	{
		status=Status.CACHE;
		this.data=data;
		return this;
	}
	public LiveDataResponse setError(Throwable data)
	{
		status=Status.THROWABLE;
		this.mException=data;
		return this;
	}
}
