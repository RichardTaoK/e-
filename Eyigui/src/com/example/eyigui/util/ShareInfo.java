package com.example.eyigui.util;

import android.content.Context;
import android.content.SharedPreferences.Editor;

public class ShareInfo {
	public String getInfoID(Context context) {
		return context.getSharedPreferences("", Context.MODE_PRIVATE).edit()
				.putString("", "").toString();
	}

	public void saveInfo(Context context,String username,String userid) {
		Editor edit = context.getSharedPreferences("",Context.MODE_PRIVATE).edit();
		edit.putString("username", username);
		edit.putString("userid", userid);
		edit.commit();
	}
}
