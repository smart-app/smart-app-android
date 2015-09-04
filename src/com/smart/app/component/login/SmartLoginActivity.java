package com.smart.app.component.login;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.smart.app.example.CodeHelpMainActivity;
import com.smart.app.example.MainActivity;
import com.smart.app.framework.R;

public class SmartLoginActivity extends Activity implements OnClickListener{

	private Button mBtnRegister;
	private Button mBtnLogin;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.smart_user_login);
       
        initView();
    }
    
    
    public void initView()
    {

    	mBtnRegister = (Button) findViewById(R.id.regist);
    	mBtnRegister.setOnClickListener(this);
    	
    	mBtnLogin = (Button) findViewById(R.id.login);
    	mBtnLogin.setOnClickListener(this);
    }
    


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch(v.getId())
		{

		case R.id.regist:
			goRegisterActivity();
			break;
		case R.id.login:
			showRequestDialog();
			break;
			default:
				break;
		}
	}
    
    public void goRegisterActivity()
    {
    	Intent intent = new Intent();
    	intent.setClass(this, SmartRegisterActivity.class);
    	startActivity(intent);
    }
	   

	private Dialog mDialog = null;
	private void showRequestDialog()
	{
		if (mDialog != null)
		{
			mDialog.dismiss();
			mDialog = null;
		}
		//mDialog = SmartDialogFactory.creatRequestDialog(this, "正在验证账号...");
		//mDialog.show();
		Intent intent = new Intent(this, CodeHelpMainActivity.class);
		startActivity(intent);
	}
	
	
}
