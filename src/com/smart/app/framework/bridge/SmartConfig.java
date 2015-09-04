/*?
* SmartConfig.java?
*?ËµÃ÷£º
*?Created?by Sky on??2015/8/5
*?Copyright?(c)?2015 Sky All?Rights?Reserved
*/
package com.smart.app.framework.bridge;

import com.smart.app.framework.ui.activity.SmartCommonActivity;
import com.smart.app.framework.ui.activity.SmartFullScreenActivity;
import com.smart.app.framework.ui.activity.SmartSearchActivity;
import com.smart.app.framework.ui.activity.SmartTabActivity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/8/5.
 */
public class SmartConfig {

    private static Map<String,Class> bridgeMapping = new HashMap<String, Class>(8);

    private static Map<String,Class> pageMapping = new HashMap<String, Class>(8);


    static {
        bridgeMapping.put("SmartNativeAPI", SmartNativeAPI.class);
        pageMapping.put("common", SmartCommonActivity.class);
        pageMapping.put("tab", SmartTabActivity.class);
        pageMapping.put("search", SmartSearchActivity.class);
        pageMapping.put("fullscreen", SmartFullScreenActivity.class);
    }

    public static Class getTargetPage(String target){
        return pageMapping.get(target);
    }

    public static void registerPageTarget(String target, Class clz) {
        pageMapping.put(target, clz);
    }

    public static Class getBridgeClass(String bridgeName){
        return bridgeMapping.get(bridgeName);
    }

    /**
     * ×¢²áJSBridgeNative
     *
     * @param className
     * @param clz
     */
    public static void registerBridgeClass(String className, Class clz) {
        bridgeMapping.put(className, clz);
    }
}
