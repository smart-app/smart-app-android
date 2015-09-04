/*?
* NativeJSBridgeWebChromeClient.java?
*?˵����
*?Created?by Sky on??2015/7/8
*?Copyright?(c)?2015 Sky All?Rights?Reserved
*/
package com.smart.app.framework.bridge;

import android.view.View;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;

public class SmartWebChromeClient extends WebChromeClient {

    private ProgressBar progressbar;

    public SmartWebChromeClient(ProgressBar progressBar){
        this.progressbar = progressBar;
    }

    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        if(progressbar!=null){
            if (newProgress == 100) {
                progressbar.setVisibility(View.GONE);
            } else {
                if (progressbar.getVisibility() == View.GONE)
                    progressbar.setVisibility(View.VISIBLE);
                progressbar.setProgress(newProgress);
            }
        }
        super.onProgressChanged(view, newProgress);
    }

    @Override
    public void onCloseWindow(WebView window) {
        super.onCloseWindow(window);
    }

    @Override
    public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
        return super.onJsAlert(view, url, message, result);
    }

    @Override
    public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
        return super.onJsConfirm(view, url, message, result);
    }

    @Override
    public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
        return super.onJsPrompt(view, url, message, defaultValue, result);
    }
}
