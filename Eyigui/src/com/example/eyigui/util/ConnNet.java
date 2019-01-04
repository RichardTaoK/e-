package com.example.eyigui.util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.client.methods.HttpPost;


public class ConnNet {
	public ConnNet(){}
	private static ConnNet connNet=new ConnNet();
	public static ConnNet getInstance(){
		return connNet;
	} 
	final static String urlsString = "http://159bw07957.iask.in/EClothesClient";

	public  HttpURLConnection getConnection(String pathString) {
	
		HttpURLConnection connection = null;
		try {
			URL url = new URL(urlsString+pathString);
			connection = (HttpURLConnection) url.openConnection();
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setDefaultUseCaches(false);
			connection.setRequestMethod("POST");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	public  HttpPost getPost(String string) {
		HttpPost httpPost = new HttpPost(urlsString + string);
		return httpPost;
	}
}
