package com.smart.app.framework.ui.control.tab;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public class SmartTabViewPagerAdapter extends PagerAdapter {
    private TabPageListener mListener;
    private int mPageCount;

    public SmartTabViewPagerAdapter(int pageCount, TabPageListener listener) {
        mPageCount = pageCount;
        mListener = listener;
    }

    public void setPageCount(int pageCount) {
        mPageCount = pageCount;
    }

    @Override
    public int getCount() {
        return mPageCount;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View v = mListener.getPage(position);
        container.addView(v);
        return v;
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == o;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        try {
            container.removeView((View) object);
        } catch (Exception e) {
        }

    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    public interface TabPageListener {
        View getPage(int position);
    }
}
