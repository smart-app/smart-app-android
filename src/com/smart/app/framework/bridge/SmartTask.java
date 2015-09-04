package com.smart.app.framework.bridge;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2015/8/8.
 */
public class SmartTask {
    private final static ExecutorService threadPoll = Executors.newCachedThreadPool();
    public static void execute(Runnable runnable){
        threadPoll.execute(runnable);
    }
}
