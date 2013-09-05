package com.kurzandroidu.zakladyandroidu;

import android.content.Context;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.google.android.gms.maps.SupportMapFragment;

public class ScrollAwareViewPager extends ViewPager {

    private static final int NON_DRAGABLE_FRACTION = 8;

    public ScrollAwareViewPager(Context context) {
        super(context);
    }

    public ScrollAwareViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {

        if (((FragmentPagerAdapter) getAdapter()).getItem(getCurrentItem()) instanceof SupportMapFragment) {
            if (event.getAction() == MotionEvent.ACTION_DOWN
                    && event.getX() < (getWidth() - (getWidth() / NON_DRAGABLE_FRACTION))
                    && event.getX() > (getWidth() / NON_DRAGABLE_FRACTION)) {

                return false;
            }
        }
        return super.onInterceptTouchEvent(event);
    }
}
