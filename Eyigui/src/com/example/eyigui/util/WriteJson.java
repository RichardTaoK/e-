package com.example.eyigui.util;

import java.util.List;

import android.webkit.JavascriptInterface;

import com.example.eyigui.bean.User;
import com.google.gson.Gson;

public class WriteJson {
	public WriteJson(){}
	@JavascriptInterface
	public String getJsonData(List<?> list){
		Gson gson=new Gson();
		String string=gson.toJson(list);
		return string;

	}
@JavascriptInterface
	public String getJson(User user){
		Gson gson = new Gson();
		String json = gson.toJson(user);
		return  json;
	}
}
