package com.smart.app.component.login;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import com.smart.app.framework.R;

public class SmartDialogFactory {

	public static Dialog creatRequestDialog(final Context context, String tip){
		
		final Dialog dialog = new Dialog(context, R.style.smart_dialog_loading);
		dialog.setContentView(R.layout.smart_dialog_loading_layout);
		Window window = dialog.getWindow();
		WindowManager.LayoutParams lp = window.getAttributes();	
		int width = getScreenWidth(context);
		lp.width = (int)(0.6 * width);	
		
		TextView titleTxtv = (TextView) dialog.findViewById(R.id.tvLoad);
		if (tip == null || tip.length() == 0)
		{
			titleTxtv.setText("正在发送请求");
		}else{
			titleTxtv.setText(tip);	
		}
		
		return dialog;
	}

	public static int getScreenWidth(Context context) {
		WindowManager manager = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
		Display display = manager.getDefaultDisplay();
		return display.getWidth();
	}
	
}
