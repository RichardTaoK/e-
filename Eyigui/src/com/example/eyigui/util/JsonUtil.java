package com.example.eyigui.util;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.example.eyigui.bean.Info;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class JsonUtil {
	public JsonUtil(){}
	public List<?> StringFromJson(String stringJson) {
		Type listtype = new TypeToken<List<?>>() {}.getType();
		Gson gson=new Gson();
		ArrayList<?> list = gson.fromJson(stringJson, listtype);
		return list; 
	}
	public List<Info> StringFromJson1(String stringJson){
		Type listtype = new TypeToken<List<Info>>() {}.getType();
		Gson gson=new Gson();
		List<Info> list = gson.fromJson(stringJson, listtype);
		return list;
	}
}
