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
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.example.eyigui.implement.UserRegister;
import com.example.eyigui.util.ConnNet;

import android.R.integer;
import android.R.string;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.AsyncTask;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

public class UserRegisterDemo implements UserRegister {
	private WebView webView;
	private Context context;
	public UserRegisterDemo() {
	}
	public UserRegisterDemo(WebView webView ,Context context){
		this.webView =webView;
		this.context=context;
	}
	@JavascriptInterface
	@Override
	public String isUserRegister(String username, String pwd) {
		// TODO Auto-generated method stub
		String string = null;
		String urlString = "";// 地址
		ConnNet connNet = ConnNet.getInstance();
		List<BasicNameValuePair> list = new ArrayList<BasicNameValuePair>();
		list.add(new BasicNameValuePair("username", username));
		try {
			HttpEntity httpEntity = new UrlEncodedFormEntity(list, HTTP.UTF_8);
			HttpPost httpPost = connNet.getPost(urlString);
			httpPost.setEntity(httpEntity);
			HttpClient httpClient = new DefaultHttpClient();
			HttpResponse httpResponse = httpClient.execute(httpPost);
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				string = EntityUtils
						.toString(httpResponse.getEntity(), "utf-8");
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

		return string;
	}

	@JavascriptInterface
	@Override
	// 这里是注册时实现的功能
	public String goToRegister(String string,String string2,String string3,String string4,String string5){
		new MAsyncTask().execute(string,string2,string3,string4,string5);
		return null;
	}
	/*public String goToRegister(final String jsonString) {
		new Thread() {
			public void run() {
				String uripath = "/Register";
				String result = null;
				List<NameValuePair> list = new ArrayList<NameValuePair>();
				NameValuePair nvp = new BasicNameValuePair("jsonstring",
						jsonString);
				list.add(nvp);
				ConnNet connNet = ConnNet.getInstance();
				HttpPost httpPost = connNet.getPost(uripath);
				try {
					HttpEntity entity = new UrlEncodedFormEntity(list,
							HTTP.UTF_8);
					httpPost.setEntity(entity);
					HttpClient client = new DefaultHttpClient();
					HttpResponse httpResponse = client.execute(httpPost);
					if (httpResponse.getStatusLine().getStatusCode() == 200) {
						result = EntityUtils.toString(httpResponse.getEntity(),
								"utf-8");
						System.out.println("resu" + result);
					} else {
						result = "f";
					}
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (ParseException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			};
		}.start();
		return null;
	}*/

	@JavascriptInterface
	@Override
	public String checkUserName(String username) {
		// TODO Auto-generated method stub

		String url = "/Check";
		String result = null;
		ConnNet connNet = ConnNet.getInstance();
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("username", username));
		try {
			HttpEntity entity = new UrlEncodedFormEntity(params, HTTP.UTF_8);
			HttpPost httpPost = connNet.getPost(url);
			System.out.println(httpPost.toString());
			httpPost.setEntity(entity);
			HttpClient client = new DefaultHttpClient();
			HttpResponse httpResponse = client.execute(httpPost);
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				result = EntityUtils
						.toString(httpResponse.getEntity(), "utf-8");
				System.out.println("resu" + result);
			}
		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		} catch (ClientProtocolException e) {

			e.printStackTrace();
		} catch (ParseException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		return result;
	}
class MAsyncTask extends AsyncTask<String,Void,String>{

	@Override
	protected String doInBackground(String... arg0) {
		HttpURLConnection connection =null;
		try {
			
			URL url=new URL("http://159bw07957.iask.in/EClothesClient/Register?username="
					 +arg0[0]+"&userpwd="+arg0[1]+"&useremail="+arg0[2]+"&userphone="+arg0[3]+"&usergender="+arg0[4]);
			connection =(HttpURLConnection) url.openConnection();
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setConnectTimeout(1000);
			connection.setDefaultUseCaches(false);
			connection.setRequestMethod("GET");
			connection.connect();
			InputStream inputStream = connection.getInputStream();
			int len=0;
			byte[] buffer =new byte[1024];
			len=inputStream.read(buffer);
			String stringresult=new String(buffer, 0, len);
			inputStream.close();
			return stringresult;
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		if("t".equals(result)){
			AlertDialog.Builder builder =new AlertDialog.Builder(context);
				builder.setTitle("提示").setMessage("注册成功")
						.setPositiveButton("确定", new OnClickListener() {

							public void onClick(DialogInterface arg0, int arg1) {
								// TODO Auto-generated method stub
								arg0.dismiss();
							}
						}).create().show();
		}
		else{
			AlertDialog.Builder builder=new AlertDialog.Builder(context);
			builder.setTitle("提示").setMessage("注册失败").setPositiveButton("确定", new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface arg0, int arg1) {
					// TODO Auto-generated method stub
					arg0.dismiss();
				}
			}).create().show();
		}
		
	}
	
}
}
