package com.example.xumengyin.mypractice.net;

import android.content.SharedPreferences;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class PackageData
{
	public static RequestBody init(String cityName) {
		JSONObject root = new JSONObject();
		JSONObject params = new JSONObject();
		try {
			root.put("cmd", "init");
			root.put("params", params);

			params.put("ua","ff");
			params.put("sdk","4.5");
			params.put("imei", "");
			params.put("imsi", "");
			params.put("appVersion", "4.7");
			params.put("mapType", "google");
			params.put("appName", "xfinder4personal");
			params.put("platformType", "android");
			params.put("cityName", cityName);
			return createRequestBody(root.toString());
		} catch (JSONException e) {

			e.printStackTrace();
			return null;
		}
	}
	public static RequestBody createRequestBody(String data)
	{
		return RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), data);
	}
}
