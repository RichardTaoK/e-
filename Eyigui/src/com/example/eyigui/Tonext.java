package com.example.eyigui;

import android.content.Context;
import android.content.Intent;
import android.webkit.JavascriptInterface;

public class Tonext {
	public Context context;
	public Tonext(){}
	public Tonext(Context context){
		this.context=context;
	}
	@JavascriptInterface
	public void tonext(){
		//实现跳转，页面跳转到activity上
		Intent intent =new Intent(context, ToShowActivity.class);
		context.startActivity(intent);
		
	}
}
