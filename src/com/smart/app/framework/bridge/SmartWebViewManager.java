/*?
* WebViewManager.java?
*?˵����
*?Created?by Sky on??2015/7/10
*?Copyright?(c)?2015 Sky All?Rights?Reserved
*/
package com.smart.app.framework.bridge;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/7/10.
 */
public class SmartWebViewManager {

    private static int webViewId = 0;
    private static Map<Integer,WeakReference<SmartWebView>> views = new HashMap<Integer, WeakReference<SmartWebView>>();

    public static SmartWebView getWebView(int webViewId){
        WeakReference<SmartWebView> reference = views.get(webViewId);
        if(reference!=null){
            return reference.get();
        }else{
            return null;
        }
    }

    public static Map<Integer,WeakReference<SmartWebView>> getAllWebView(){
        return views;
    }


    public static void addWebView(SmartWebView view){
        view.setWebViewId(++webViewId);
        views.put(view.getWebViewId(), new WeakReference<SmartWebView>(view));
    }

    public static void removeWebView(int webViewId){
        views.remove(webViewId);
    }

    public static void removeAllView(){
        views.clear();
    }
}
