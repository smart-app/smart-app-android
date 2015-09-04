package com.smart.app.framework.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by sky on 15/8/22.
 */
public class SmartPagerSlidingAdapter extends PagerAdapter {

    private String[] titles;
    private List<Fragment> fragmentList;
    private FragmentManager fragmentManager;

    public SmartPagerSlidingAdapter(FragmentManager fm, String[]titles, List<Fragment> fragmentList) {
        this.fragmentManager = fm;
        this.titles = titles;
        this.fragmentList = fragmentList;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public int getCount() {
        return titles.length;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(fragmentList.get(position).getView()); // 移出viewpager两边之外的page布局
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == o;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = fragmentList.get(position);
        if (!fragment.isAdded()) {
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.add(fragment, fragment.getClass().getSimpleName());
            ft.commit();
            fragmentManager.executePendingTransactions();
        }

        if (fragment.getView().getParent() == null) {
            container.addView(fragment.getView());
        }

        return fragment.getView();
    }

}