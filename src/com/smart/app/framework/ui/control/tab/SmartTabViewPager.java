package com.smart.app.framework.ui.control.tab;

import android.content.Context;
import android.os.SystemClock;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class SmartTabViewPager extends ViewPager {
    private boolean mEnabledTouchScroll = true;
    private boolean mForceDisabledTouchScroll;

    private float mX;
    private float mY;
    private float mXmove;
    private float mYmove;
    private boolean needObtain = true;

    private boolean innerListenerSet;
    private OnPageChangeListener publicListener;

    private boolean canSlidingMen = false;
    private boolean isSlidingMenuEnable = true;

    public static final int INTERCEPT_DO_NOTHING = 0X000;
    public static final int INTERCEPT_YES = 0X001;
    public static final int INTERCEPT_NO = 0X010;
    private int mIntercept = INTERCEPT_DO_NOTHING;

    public void setIntercept(int action) {
        this.mIntercept = action;
    }

    public SmartTabViewPager(Context context) {
        super(context);
        setOnPageChangeListener(new ViewPagerChild());
    }

    public SmartTabViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnPageChangeListener(new ViewPagerChild());
    }


    @Override
    public void setOnPageChangeListener(OnPageChangeListener listener) {
        if (!innerListenerSet) {
            super.setOnPageChangeListener(listener);
            innerListenerSet = true;

        } else {
            publicListener = listener;
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        try {
            int action = ev.getAction();

            if (action == MotionEvent.ACTION_DOWN) {
                // This article is excellent! http://blog.csdn.net/seker_xinjian/article/details/6253617
                mEnabledTouchScroll = true;
                mX = ev.getX();
                mY = ev.getY();
            } else if (action == MotionEvent.ACTION_MOVE) {
                mXmove = ev.getX();
                mYmove = ev.getY();

                if (Math.abs(mX - mXmove) > Math.abs(mY - mYmove)) {
                    if (!mForceDisabledTouchScroll && needObtain) {
                        this.dispatchTouchEvent(MotionEvent.obtain(SystemClock.uptimeMillis(), SystemClock.uptimeMillis(), MotionEvent.ACTION_DOWN, mX, mY, 0));
                        needObtain = false;

                    } else if (mForceDisabledTouchScroll && needObtain) {
                    }
                }
            }
            switch (mIntercept) {
            case INTERCEPT_YES:
                super.onInterceptTouchEvent(ev);
                if (mForceDisabledTouchScroll) {
                    return false;
                }
                return true;
            case INTERCEPT_NO:
                return false;
            case INTERCEPT_DO_NOTHING:
            default:
                return mEnabledTouchScroll && !mForceDisabledTouchScroll && super.onInterceptTouchEvent(ev);
            }

        } catch (Exception e) {

        }

        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        try {
            return super.onTouchEvent(ev);

        } catch (Exception e) {

        }

        return false;
    }

    public void setEnableTouchScrollForViewPager(boolean enableTouchScroll) {
        this.mEnabledTouchScroll = enableTouchScroll;
    }

    public void setForceDisableTouchScroll(boolean forceDisableTouchScroll) {
        mForceDisabledTouchScroll = forceDisableTouchScroll;

        if (mForceDisabledTouchScroll) {
            needObtain = true;
        }
    }
    
    public boolean getForceDisableTouchScroll(){
        return mForceDisabledTouchScroll;
    }
    
    public void setNeedObtain(boolean bt) {
        needObtain = bt;
    }

    public void setCanSlidingMen(boolean bt) {
        canSlidingMen = bt;
    }

    public boolean getIsSlidingMenuEnable() {
        return isSlidingMenuEnable;
    }

    class ViewPagerChild implements OnPageChangeListener {

        @Override
        public void onPageScrolled(int i, float v, int i2) {
            if (publicListener != null) {
                publicListener.onPageScrolled(i, v, i2);
            }
        }

        @Override
        public void onPageSelected(int i) {

            if (canSlidingMen) {
                if (i == 0) {
                    isSlidingMenuEnable = true;

                } else {
                    isSlidingMenuEnable = false;
                }
            }

            if (publicListener != null) {
                publicListener.onPageSelected(i);
            }
        }

        @Override
        public void onPageScrollStateChanged(int i) {
            if (publicListener != null) {
                publicListener.onPageScrollStateChanged(i);
            }
        }
    }
//
//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//
//        int height = 0;
//        for (int i = 0; i < getChildCount(); i++) {
//            View child = getChildAt(i);
//            child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
//            int h = child.getMeasuredHeight();
//            if (h > height)
//                height = h;
//        }
//
//        heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
//
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//    }
}
