package com.example.eyigui;

import java.io.File;

import org.apache.http.cookie.Cookie;

import com.example.eyigui.bean.User;
import com.example.eyigui.implementDemo.UserLandDemo;
import com.example.eyigui.implementDemo.UserRegisterDemo;
import com.example.eyigui.util.JsonUtil;
import com.example.eyigui.util.WriteJson;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.JsResult;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

@SuppressLint({ "SetJavaScriptEnabled", "JavascriptInterface" }) 
public class MainActivity extends Activity {
	private WebView webView;
	private final static int FILECHOOSER_RESULTCODE = 1;
	private String mCameraFilePath = null;
	private ValueCallback<Uri> mUploadMessage;
	private Uri imageUri;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		webView =(WebView) findViewById(R.id.webview);
		
		WebSettings settings = webView.getSettings();
		settings.setJavaScriptEnabled(true);
		settings.setAllowFileAccess(true);
		settings.setAppCacheEnabled(true);
		settings.setCacheMode(MODE_PRIVATE);
		settings.setDomStorageEnabled(true);
		WebChromeClient chromeClient = new WebChromeClientImpl();
	    webView.setWebChromeClient(chromeClient);
		webView.requestFocusFromTouch();
		webView.setWebViewClient(new WebViewClient(){
		
			
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			// TODO Auto-generated method stub
			view.loadUrl(url);
			return true;
		}
		
	});
		webView.addJavascriptInterface(new UserLandDemo(webView,this), "land");
		webView.addJavascriptInterface(new UserRegisterDemo(webView,this), "register1");
		webView.addJavascriptInterface(new User(), "user");
		webView.addJavascriptInterface(new JsonUtil(), "jsonutil");
		webView.addJavascriptInterface(new WriteJson(), "writejson");
		webView .addJavascriptInterface(new Tonext(this), "next");
		webView.loadUrl("file:///android_asset/denglu.html");
		CookieManager instance = CookieManager.getInstance();
		String cookie = instance.getCookie("");
		instance.setCookie("", "");
		CookieSyncManager cookieSyncManager = CookieSyncManager.getInstance();
		
	}
	/*
	 * (non-Javadoc)
	 * @see android.app.Activity#onKeyDown(int, android.view.KeyEvent)
	 *÷ÿ–¥onkeydown
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode==KeyEvent.KEYCODE_BACK&&webView.canGoBack()) {
			webView.goBack();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
private class WebChromeClientImpl extends WebChromeClient{

	public void openFileChooser(ValueCallback<Uri> uploadMsg) {
	                mUploadMessage = uploadMsg;

	                File imageStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "TestApp");
	                if (!imageStorageDir.exists()) {
	                    imageStorageDir.mkdirs();
	                }
	                File file = new File(imageStorageDir + File.separator + "IMG_" + String.valueOf(System.currentTimeMillis()) + ".jpg");
	                imageUri = Uri.fromFile(file); // save to the private variable

	                final Intent captureIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
	                captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);

	                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
	                i.addCategory(Intent.CATEGORY_OPENABLE);
	                i.setType("image/*");

	                Intent Photo = new Intent(Intent.ACTION_PICK,
	                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

	                Intent chooserIntent = Intent.createChooser(i, "Image Chooser");
	                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Parcelable[] { captureIntent, Photo });

	               startActivityForResult(chooserIntent, FILECHOOSER_RESULTCODE);
	            }

	            // For Android 3.0+
	            public void openFileChooser(ValueCallback uploadMsg, String acceptType) {
	                openFileChooser(uploadMsg);
	            }

	            // For Android 4.1
	            public void openFileChooser(ValueCallback<Uri> uploadMsg,
	                                        String acceptType, String capture) {
	                openFileChooser(uploadMsg);
	            }
}
@Override


public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == FILECHOOSER_RESULTCODE) {
            if (null == this.mUploadMessage) {
                return;
            }
            Uri result;
            if (resultCode != Activity.RESULT_OK) {
                result = null;
            } else {
                result = intent == null ? this.imageUri : intent.getData(); 
            }
            this.mUploadMessage.onReceiveValue(result);
            this.mUploadMessage = null;
        }
    }
}
