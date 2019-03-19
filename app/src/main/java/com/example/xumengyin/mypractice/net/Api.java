package com.example.xumengyin.mypractice.net;

import com.example.xumengyin.mypractice.bean.GutHubUser;
import com.example.xumengyin.mypractice.bean.InitBean;
import com.example.xumengyin.mypractice.bean.Repo;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Api
{

	@GET("users/{user}/repos")
	Observable<ApiResponse<List<Repo>>> listRepos(@Path("user") String user);

	@GET("users/{user}")
	Observable<ApiResponse<GutHubUser>> getUser(@Path("user") String user);

	//@Headers({"Content-Type: application/json", "Accept: application/json"})
	@POST("/saasapi/saasapi")
	Observable<ApiResponse<InitBean>> cpsDnaInit(@Body RequestBody data);


	//1 @Header和@Headers 添加head
	//2 动态添加header  Call<List<Data>> getData(@Header("Content-Range") String contentRange);
	//3通过拦截器添加
}
