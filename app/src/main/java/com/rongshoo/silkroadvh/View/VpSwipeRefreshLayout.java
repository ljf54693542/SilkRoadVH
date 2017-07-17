package com.rongshoo.silkroadvh.View;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 *  SwipeRefreshLayout 广告滑动手势冲突
 *  http://blog.csdn.net/u010064206/article/details/50583991
 */
public class VpSwipeRefreshLayout extends SwipeRefreshLayout {


    private float startY;
    private float startX;    // 记录viewPager是否拖拽的标记
    private boolean mIsVpDragger;


    public VpSwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    float lastx = 0;
    float lasty = 0;
    boolean ismovepic = false;

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {


        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            lastx = ev.getX();
            lasty = ev.getY();
            ismovepic = false;
            return super.onInterceptTouchEvent(ev);
        }

        final int action = MotionEventCompat.getActionMasked(ev);
        // VLog.v(ev.getX() + "---" + ev.getY());

        int x2 = (int) Math.abs(ev.getX() - lastx);
        int y2 = (int) Math.abs(ev.getY() - lasty);

        //滑动图片最小距离检查
        // VLog.v("滑动差距 - >" + x2 + "--" + y2);
        if (x2 > y2) {
            if (x2 >= 100) ismovepic = true;
            return false;
        }

        //是否移动图片(下拉刷新不处理)
        if (ismovepic) {
            //     VLog.v("滑动差距 - >" + x2 + "--" + y2);
            return false;
        }

        return super.onInterceptTouchEvent(ev);
    }

}