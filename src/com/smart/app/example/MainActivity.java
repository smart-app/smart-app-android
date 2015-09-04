package com.smart.app.example;

import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.smart.app.framework.R;
import com.smart.app.framework.bridge.SmartWebView;
import com.smart.app.framework.message.IMessageCallback;
import com.smart.app.framework.message.MessageAction;
import com.smart.app.framework.ui.activity.SmartBaseActivity;
import com.smart.app.framework.ui.adapter.SmartPagerSlidingAdapter;
import com.smart.app.framework.ui.control.CustomScrollView;
import com.smart.app.framework.ui.control.tab.SmartTabViewPager;
import com.smart.app.framework.ui.control.tab.SmartTabViewPagerAdapter;
import com.smart.app.framework.ui.fragment.SmartWebViewFragment;
import com.smart.app.framework.utils.SmartUI;
import com.smart.app.vendor.pagerslider.PagerSlidingTabStrip;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends FragmentActivity implements SmartWebView.ScrollListener,IMessageCallback {

    private static long TIME_ANIMATION = 500;

    private CustomScrollView scroller;
    private LinearLayout layout;
    private RelativeLayout layoutSearch;
    private PagerSlidingTabStrip tabs;
    private SmartTabViewPager pager;
    private SmartPagerSlidingAdapter adapter;


    private String[] titles;
    private List<Fragment> fragmentList = new ArrayList<Fragment>(8);

    private boolean isTopHide = false;
    //动画是否结束
    private boolean isAnimationFinish = true;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //super.init("主页面","file:///android_asset/app/web/codehelp/home/home.html", true);

        setContentView(R.layout.smart_pager_slider_activity);

        titles= new String[]{"百度","必应","科技","NBA","爱奇艺","腾讯视频","优酷","Coding"};

        fragmentList.add(SmartWebViewFragment.newInstance("http://www.baidu.com"));
        fragmentList.add(SmartWebViewFragment.newInstance("http://cn.bing.com/"));
        fragmentList.add(SmartWebViewFragment.newInstance("http://tech.baidu.com"));
        fragmentList.add(SmartWebViewFragment.newInstance("http://sports.sohu.com"));
        fragmentList.add(SmartWebViewFragment.newInstance("http://www.iqiyi.com/"));
        fragmentList.add(SmartWebViewFragment.newInstance("http://v.qq.com/"));
        fragmentList.add(SmartWebViewFragment.newInstance("http://www.youku.com/"));
        fragmentList.add(SmartWebViewFragment.newInstance("file:///android_asset/app/web/codehelp/home/home.html"));


        //scroller = (CustomScrollView)findViewById(R.id.scroller);
        layout= (LinearLayout)findViewById(R.id.layout);
        layoutSearch = (RelativeLayout)findViewById(R.id.layoutSearch);

        tabs = (PagerSlidingTabStrip) findViewById(R.id.pagerTabs);

        tabs.setTabsStyle(getResources().getDisplayMetrics(),"#E2E2E2","#ffffff");
        //tabs.setTextColor(Color.rgb(206,206,206));
        pager = (SmartTabViewPager) findViewById(R.id.viewPager);
        adapter = new SmartPagerSlidingAdapter(getSupportFragmentManager(), titles, fragmentList);
        pager.setAdapter(adapter);
        tabs.setViewPager(pager);

//        scroller.setOnTouchListener(new View.OnTouchListener() {
//
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//
//                switch (event.getAction()) {
//
//                    case MotionEvent.ACTION_DOWN:
//                        Log.d("TAG", "mScroller-----ACTION_DOWN------------");
//                        //lastY = event.getY();
//                        break;
//                    case MotionEvent.ACTION_MOVE:
//                }
//                return false;
//            }
//        });
    }


    @Override
    public void onScrollChanged(int l, int t, int oldl, int oldt) {
        Log.d(">>>onScrollChanged:", " l:" + l + " t:" + t + " oldl:" +oldl + " oldt:" + oldt + " searchY:" + layoutSearch.getHeight() +" pageY:"+ pager.getY());
        if(t>5 && !isTopHide){
            hideTop();
        }else if (t<5 && isTopHide){
            showTop();
        }
    }

    @Override
    public void onReceiveMessage(final MessageAction messageAction, final JSONObject json) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                switch (messageAction) {
                    default:
                        Log.e(">>>MessageAction#", "default:switch action:" + messageAction.toString() + " not implementation!");
                        break;
                }
            }
        });
    }

    /**
     * 显示上部的布局
     */
    private void showTop() {
        isTopHide = false;
        ObjectAnimator anim = ObjectAnimator.ofFloat(layout, "y", -177, 0);
        anim.setDuration(TIME_ANIMATION);
        anim.start();
    }


    /**
     * 隐藏上部的布局
     */
    private void hideTop() {
        isTopHide = true;
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(layout, "y", 0, -177);
        anim2.setDuration(TIME_ANIMATION);
        anim2.start();
    }

}