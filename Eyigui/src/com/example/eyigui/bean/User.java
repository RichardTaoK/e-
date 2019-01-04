package com.example.eyigui.bean;

import android.webkit.JavascriptInterface;


public class User {
	private String userID;
	private String userName;
	private String userPwd;
	private String userEmail;
	private String userGender;
	private String userTelPhoneNumber;

	public User() {
	}
@JavascriptInterface
	public String getUserID() {
		return userID;
	}
@JavascriptInterface
	public void setUserID(String userID) {
		this.userID = userID;
	}
@JavascriptInterface
	public String getUserName() {
		return userName;
	}
@JavascriptInterface
	public void setUserName(String userName) {
		this.userName = userName;
	}
@JavascriptInterface
	public String getUserPwd() {
		return userPwd;
	}
@JavascriptInterface
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
@JavascriptInterface
	public String getUserEmail() {
		return userEmail;
	}
@JavascriptInterface
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
@JavascriptInterface
	public String getUserGender() {
		return userGender;
	}
@JavascriptInterface
	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}
@JavascriptInterface
	public String getUserTelPhoneNumber() {
		return userTelPhoneNumber;
	}
@JavascriptInterface
	public void setUserTelPhoneNumber(String userTelPhoneNumber) {
		this.userTelPhoneNumber = userTelPhoneNumber;
	}

}
