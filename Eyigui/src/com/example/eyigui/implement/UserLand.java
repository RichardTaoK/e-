package com.example.eyigui.implement;

public interface UserLand {
	//用户登录会用到哪些函数的呢？
	//根据基本的会知道，你去登录的话那就需要知道你输得用户名是否有
	//，有了这个用户名然后再去判断一下用户名了和密码是否匹配的
	public boolean isUser(String userID);
	public void isUser(String userID,String pwd);
}
