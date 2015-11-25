package com.duan.projectmonngon;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class SplashScreenActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_screen);
		Thread thread = new Thread(){
			public void run() {
				try {
					sleep(3000); 
				} catch (InterruptedException e) {
					// TODO: handle exception
					e.printStackTrace();
				}finally{
					Intent intent = new Intent(SplashScreenActivity.this, MainActivityMenu.class);
					startActivity(intent);
				}
			};
		};
		thread.start();
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}

}
