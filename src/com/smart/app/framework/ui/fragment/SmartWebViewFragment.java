package com.smart.app.framework.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.smart.app.framework.bridge.SmartWebView;
import com.smart.app.framework.message.IMessageCallback;
import com.smart.app.framework.message.MessageAction;
import com.smart.app.framework.ui.control.dialog.SmartDialogUtil;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by sky on 15/8/22.
 */
public class SmartWebViewFragment extends Fragment{

    private SmartWebView webView;
    private String url;
    private SmartWebView.ScrollListener scrollListener;
    private IMessageCallback messageCallback;

    public static SmartWebViewFragment newInstance(String url){
        SmartWebViewFragment f = new SmartWebViewFragment();
        Bundle bundle = new Bundle();
        bundle.putString("url", url);
        f.setArguments(bundle);
        return f;
    }

    /** Fragment第一次附属于Activity时调用,在onCreate之前调用 */
    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        scrollListener = (SmartWebView.ScrollListener) activity;
        messageCallback  = (IMessageCallback) activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if(args!=null){
            url = args.getString("url");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        webView = new SmartWebView(SmartWebViewFragment.this.getActivity(), messageCallback, null);
        webView.setVerticalScrollBarEnabled(false);
        webView.setVerticalScrollbarOverlay(false);
        webView.setLayoutParams(layoutParams);
        webView.setScrollListener(scrollListener);
        webView.loadUrl(url);
        return webView;
    }

}
