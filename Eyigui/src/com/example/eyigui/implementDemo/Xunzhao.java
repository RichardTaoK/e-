package com.example.eyigui.implementDemo;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.example.eyigui.util.ConnNet;

public class Xunzhao {
	//这个方法得到服务器传回来的符合用户要求的数据
	public  String getString(String string){
//		String urlpath="/Collocation";
//		ConnNet connNet=ConnNet.getInstance();
//		String result=null;
//		List<BasicNameValuePair> list =new ArrayList<BasicNameValuePair>();
//		list.add(new BasicNameValuePair("city",string));
//		try {
//			HttpEntity httpEntity=new UrlEncodedFormEntity(list,HTTP.UTF_8);
//			HttpPost httpPost=connNet.getPost(urlpath);
//			httpPost.setEntity(httpEntity);
//			HttpClient httpClient=new DefaultHttpClient();
//			HttpResponse httpResponse=httpClient.execute(httpPost);
//			if(httpResponse.getStatusLine().getStatusCode()==HttpStatus.SC_OK){
//				result=EntityUtils.toString(httpResponse.getEntity(), "utf-8");
//			}
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ClientProtocolException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return result;
		HttpURLConnection connection =null;
		StringBuffer stringBuffer=new StringBuffer();
		try {
			
			URL url=new URL("http://159bw07957.iask.in/EClothesClient/Collocation?city="
					 +string);
			connection =(HttpURLConnection) url.openConnection();
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setConnectTimeout(4000);
			connection.setDefaultUseCaches(false);
			connection.setRequestMethod("GET");
			connection.connect();
			InputStream inputStream = connection.getInputStream();
			int len=0;
			byte[] buffer =new byte[1024];	
			
			while ((len=inputStream.read(buffer))!=-1) {
				String stringresult=new String(buffer, 0, len);
				stringBuffer.append(stringresult);
			}		
			inputStream.close();
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stringBuffer.toString();
		
		
	}
}
