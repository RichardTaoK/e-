package com.example.eyigui.implement;

import android.R.string;

public interface UserRegister {
	//�û�ע��ӿڣ�������ܻ��õ��ĺ���
	//�������ݿ��Ƿ���������һ���û����еĻ���ע��Ͳ��ɹ�isUserRegister()
	//û�еĻ��Ǿ�ȥע��ĺ���goToRegister()
	//����һ��ʵ����User
	/**
	 * public  class User{
	 * public String userID;
	 * public String userName;
	 * public  String pwd;
	 * public  String email;
	 * public  String phoneNumber;
	 * public String  gender;//�����Ա�ĵ���
	 *Ȼ��Ĵ���һЩ��Щ�Ƚϻ�����getter����setter����
	 * }
	 * @return
	 */
	//��������˵��ȥ������Ҫ�ĺ���
	public String  isUserRegister(String username,String pwd);
	public String checkUserName(String username);
	public String  goToRegister(String string,String string2,String string3,String string4,String string5);
}
