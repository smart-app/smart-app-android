package com.smart.app.component.login;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.smart.app.framework.R;

public class SmartRegisterActivity extends Activity implements OnClickListener{

	private Button mBtnRegister;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.smart_user_register);
		
		initView();
		
	}
	
	
	public void initView()
	{
		mBtnRegister = (Button) findViewById(R.id.register_btn);
		mBtnRegister.setOnClickListener(this);
	}
	
	

	private Dialog mDialog = null;
	private void showRequestDialog()
	{
		if (mDialog != null)
		{
			mDialog.dismiss();
			mDialog = null;
		}
		mDialog = SmartDialogFactory.creatRequestDialog(this, "正在注册中...");
		mDialog.show();
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId())
		{
		case R.id.register_btn:
			showRequestDialog();
			break;
			default:
				break;
		}
	}
}
