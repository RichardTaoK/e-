package com.example.eyigui;

import android.net.ConnectivityManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;

public class WelcomeActivity extends Activity {
	private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        imageView=(ImageView) findViewById(R.id.iv);
        AlphaAnimation alphaAnimation=new AlphaAnimation(0.1f, 1.0f);
        alphaAnimation.setDuration(2000);
        imageView.setAnimation(alphaAnimation);
        alphaAnimation.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationRepeat(Animation arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationEnd(Animation arg0) {
				// TODO Auto-generated method stub
				isWifi();
				finish();
			}
		});
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.welcome, menu);
        return true;
    }
	public void isWifi() {
		if (isNet(WelcomeActivity.this)) {
			Intent intent = new Intent(this, MainActivity.class);
			startActivity(intent);
		} else {
			Intent intent = new Intent(
					android.provider.Settings.ACTION_WIFI_SETTINGS);
			startActivity(intent);
		}
	}

	public boolean isNet(Activity activity) {
		Context context = activity.getApplicationContext();
		ConnectivityManager connectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivityManager != null) {
			return true;
		}
		return false;
	}
}
