package com.example.xumengyin.mypractice.net;

import com.google.gson.annotations.SerializedName;

/**
 * 接口返回封装的类,T为需要关注的数据
 * @param <T>
 */
public class ApiResponse<T>
{

	/**
	 * cmd : init
	 * result : 0
	 * resultNote : Success
	 * totalRecordNum : 1
	 * pages : 1
	 * pageNo : 0
	 */

	public String cmd;
	public int result;
	public String resultNote;
	public int totalRecordNum;
	public int pages;
	public int pageNo;
	@SerializedName("detail")
	T data;


}
