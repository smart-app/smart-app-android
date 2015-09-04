package com.smart.app.component.login;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import com.smart.app.example.CodeHelpMainActivity;
import com.smart.app.framework.R;

public class SmartWelcomeActivity extends Activity{

	private Handler mHandler;
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.smart_splash);
       
        initView();
    }
    
    
    public void initView()
    {
    	mHandler = new Handler();
    	mHandler.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				goLoginActivity();
			}
    	}, 1000);
    }
    
    public void goLoginActivity()
    {
    	Intent intent = new Intent();
    	intent.setClass(this, CodeHelpMainActivity.class);
    	startActivity(intent);
    	finish();
    }
    
}