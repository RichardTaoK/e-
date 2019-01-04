package com.example.eyigui.implementDemo;

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
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.AsyncTask;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import com.example.eyigui.implement.UserLand;
import com.example.eyigui.util.ConnNet;

public class UserLandDemo implements UserLand {
	public static final String TAG = UserLandDemo.class.getName();
	private WebView webView;
	private Context context;

	public UserLandDemo(WebView webView,Context context) {
		this.context = context;
		this.webView = webView;
	}

	@Override
	@JavascriptInterface
	public boolean isUser(String userID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	@JavascriptInterface
	public void isUser(String userID, String pwd) {
		// TODO Auto-generated method stub

		 new MyUserLandAsyncTask().execute(userID,pwd);

		/*
		if (isUserlandString(userID, pwd).trim().equals(null)
				|| isUserlandString(userID, pwd).trim().equals("f")
				|| isUserlandString(userID, pwd).trim().equals("")) {
			return false;
		} else {
			return true;
		}
		*/
	}

	@JavascriptInterface
	public String isUserlandString(final String userID, final String pwd) {
		new Thread(){
			@Override
			public void run() {
				ConnNet connNet = ConnNet.getInstance();
				List<NameValuePair> list = new ArrayList<NameValuePair>();
				list.add(new BasicNameValuePair("username", userID));
				list.add(new BasicNameValuePair("userpwd", pwd));
				try {
					HttpEntity entity = new UrlEncodedFormEntity(list, "UTF-8");
					HttpPost httpPost = connNet.getPost("/Login");
					httpPost.setEntity(entity);
					HttpClient client = new DefaultHttpClient();
					HttpResponse response = client.execute(httpPost);
					if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
						String b = EntityUtils.toString(response.getEntity(), "UTF-8");
						webView.loadUrl("file:///android_asset/index.html");
						//return b;
					} else {
						
						//return "f";
					}
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}.start();
		
		return null;
	}
	
	
	
	  private class MyUserLandAsyncTask extends AsyncTask<String, Void,String>{
	 
	 @Override protected String doInBackground(String... arg0) {
	 
			 HttpURLConnection connection = null; 
			 try { URL url = new URL("http://159bw07957.iask.in/EClothesClient/Login?username="
					 +arg0[0]+"&userpwd="+arg0[1]); 
			 connection = (HttpURLConnection)url.openConnection();
			 connection.setDoInput(true);
			 connection.setConnectTimeout(1000); //connection.setDoOutput(true);
			 connection.setConnectTimeout(1000);
			 connection.setDefaultUseCaches(false);
			 connection.setRequestMethod("GET"); 
			 connection.connect(); 
			 InputStream iStream = connection.getInputStream(); 
			 int len = 0; 
			 byte[] buffer = new byte[1024]; 
			 len=iStream.read(buffer); 
			 String resultStr = new String(buffer,0,len); 
			 iStream.close(); 
			 return resultStr; 
			 } catch(MalformedURLException e) { // TODO Auto-generated catch block
				 e.printStackTrace(); 
			} catch (IOException e) { // TODO Auto-generated
				e.printStackTrace(); 
			} return null; 
		}

	 
	@Override
	protected void onPostExecute(String result) {
		 if("t".equals(result)){
			 webView.loadUrl("file:///android_asset/index.html");
		 }else{
			 AlertDialog.Builder aBuilder = new AlertDialog.Builder(context);
			 aBuilder.setTitle("错误提示")
			 				.setMessage("用户名或密码错误")
			 				.setPositiveButton("确定", new OnClickListener() {
								
								@Override
								public void onClick(DialogInterface arg0, int arg1) {
									// TODO Auto-generated method stub
									arg0.dismiss();
								}
							})
			 				.create().show();
		 }
	} 
	 
	 
  }
	 
}
