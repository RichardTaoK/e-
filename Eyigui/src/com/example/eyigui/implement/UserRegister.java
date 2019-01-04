package com.example.eyigui.implement;

import android.R.string;

public interface UserRegister {
	//用户注册接口，里面可能会用到的函数
	//访问数据库是否有这样的一个用户，有的话那注册就不成功isUserRegister()
	//没有的话那就去注册的函数goToRegister()
	//创建一个实体类User
	/**
	 * public  class User{
	 * public String userID;
	 * public String userName;
	 * public  String pwd;
	 * public  String email;
	 * public  String phoneNumber;
	 * public String  gender;//这是性别的单词
	 *然后的创建一些那些比较基本的getter还有setter函数
	 * }
	 * @return
	 */
	//根据上面说的去定义需要的函数
	public String  isUserRegister(String username,String pwd);
	public String checkUserName(String username);
	public String  goToRegister(String string,String string2,String string3,String string4,String string5);
}
