package com.smart.app.framework.ui.activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.smart.app.framework.R;
import com.smart.app.framework.bridge.SmartWebView;
import com.smart.app.framework.message.IMessageCallback;
import com.smart.app.framework.message.MessageAction;
import com.smart.app.framework.ui.control.tab.SmartTabViewPager;
import com.smart.app.framework.ui.control.tab.SmartTabViewPagerAdapter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class SmartTabActivity extends SmartBaseActivity implements ViewPager.OnPageChangeListener,
        SmartTabViewPagerAdapter.TabPageListener, View.OnClickListener {

    private int tabCount = 0;

    private RadioGroup tabGroup;
    private SmartTabViewPager viewPager;
    private View tabIndicator;
    private SmartWebView[] tabWebViewArray;
    private RadioButton[] tabRadioButtonArray;
    private String[] tabUrlArray;

    private int tabItemWidth;
    private int tabIndicatorMargin;
    private int selectedTabIndex = 0;
    private JSONArray tabArray;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.smart_activity_tab);
        initParams();
        initHeader(findViewById(R.id.header));
        initView();
        initTab();
        initialTabIndicator();
        initTabIndicatorMargin(selectedTabIndex);
        initWebView(selectedTabIndex, true);
    }

    @Override
    protected void initParams() {
        super.initParams();
        if(this.params!=null){
            selectedTabIndex = this.params.optInt("selectedTabIndex", 0);
            try {
                tabArray = new JSONArray(this.params.optString("tabs"));
            } catch (JSONException e) {
                Log.e(">>>tabArray", e.toString());
            }
        }
    }

    private void initView() {
        tabGroup = (RadioGroup) findViewById(R.id.tabGroup);
        tabIndicator = findViewById(R.id.tabIndicator);
        viewPager = (SmartTabViewPager) findViewById(R.id.viewPager);
        viewPager.setOnPageChangeListener(this);
        selectedTabIndex = getIntent().getIntExtra("selectedIndex", 0);
    }

    private void initTab() {
        RadioGroup.LayoutParams params = new RadioGroup.LayoutParams(RadioGroup.LayoutParams.MATCH_PARENT, RadioGroup.LayoutParams.MATCH_PARENT);
        params.weight = 1;
        try {
            tabCount = tabArray.length();
            tabWebViewArray = new SmartWebView[tabCount];
            tabRadioButtonArray = new RadioButton[tabCount];
            tabUrlArray = new String[tabCount];

            for (int i = 0; i < tabCount; i++) {
                JSONObject tab = tabArray.getJSONObject(i);
                String name = tab.optString("name");
                String url = tab.optString("url");
                RadioButton radioButton = createTab(name, i);
                tabRadioButtonArray[i] = radioButton;
                tabUrlArray[i] = url;
                tabGroup.addView(radioButton, i, params);
            }

            viewPager.setAdapter(new SmartTabViewPagerAdapter(tabCount, this));
            viewPager.setCurrentItem(selectedTabIndex, true);
            tabRadioButtonArray[selectedTabIndex].setChecked(true);

        } catch (JSONException e) {
            Log.e(">>>init Tab", e.toString());
        }

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int index = view.getId();
                initWebView(index, viewPager.getCurrentItem() == index);
                viewPager.setCurrentItem(index, false);
            }
        };

        for (RadioButton tab : tabRadioButtonArray) {
            tab.setOnClickListener(onClickListener);
        }
    }

    private RadioButton createTab(String name, int index) {
        RadioButton tab = new RadioButton(this);
        tab.setId(index);
        tab.setBackgroundColor(getResources().getColor(R.color.light_gray));
        tab.setButtonDrawable(new ColorDrawable(Color.TRANSPARENT));
        tab.setText(name);
        tab.setGravity(Gravity.CENTER);
        tab.setTextColor(getResources().getColorStateList(R.color.smart_tab_selector));
        tab.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
        return tab;
    }


    private void initTabIndicatorMargin(int position) {
        ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) tabIndicator.getLayoutParams();
        lp.leftMargin = (tabItemWidth * position) + tabIndicatorMargin;
        tabIndicator.requestLayout();
    }

    private void initialTabIndicator() {
        DisplayMetrics dm = this.getResources().getDisplayMetrics();
        tabItemWidth = dm.widthPixels / tabCount;
        int tabIndicatorWidth = this.getResources().getDimensionPixelSize(R.dimen.smart_indicator_default_width);
        ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) tabIndicator.getLayoutParams();
        lp.width = tabIndicatorWidth;
        tabIndicatorMargin = (tabItemWidth - tabIndicatorWidth) / 2;
        lp.leftMargin = tabIndicatorMargin + tabItemWidth * selectedTabIndex;
        tabIndicator.requestLayout();
    }

    private void initWebView(int index, boolean force) {
        if (force || tabRadioButtonArray[index].getTag() == null) {
            ensureWebViewIsCreated(index);
            tabRadioButtonArray[index].setTag(Boolean.TRUE);
            tabWebViewArray[index].loadUrl(tabUrlArray[index]);
        }
    }

    private void ensureWebViewIsCreated(int index) {
        if (tabWebViewArray[index] == null) {
            tabWebViewArray[index] = createWebView(null);
            if (index == 0) {
                tabRadioButtonArray[index].setTag(Boolean.TRUE);
                tabWebViewArray[index].loadUrl(tabUrlArray[index]);
            }
        }
    }


    @Override
    public View getPage(int index) {
        ensureWebViewIsCreated(index);
        return tabWebViewArray[index];
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onPageScrolled(int position, float offset, int offsetPixels) {
        ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) tabIndicator.getLayoutParams();
        lp.leftMargin = (tabItemWidth * position) + tabIndicatorMargin + (int) (offset * (tabIndicator.getWidth() + tabIndicatorMargin * 2));
        tabIndicator.requestLayout();
    }

    @Override
    public void onPageSelected(int i) {
        viewPager.setForceDisableTouchScroll(false);
        viewPager.setNeedObtain(false);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (state == ViewPager.SCROLL_STATE_IDLE) {
            int index = viewPager.getCurrentItem();

            if (!tabRadioButtonArray[index].isChecked()) {
                tabRadioButtonArray[index].setChecked(true);
                initWebView(index, false);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}