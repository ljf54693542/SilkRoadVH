package com.rongshoo.silkroadvh.base;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;

import com.rongshoo.silkroadvh.R;

/**
 * Created by liaoj on 2017/6/27.
 */

public class ToolbarAlphaBehavior extends CoordinatorLayout.Behavior<Toolbar> {

    private Context mContext;
    private int offset=0;
    private int startOffset=0;
    private int endOffset=0;

    public ToolbarAlphaBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext=context;
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, Toolbar child, View directTargetChild, View target, int nestedScrollAxes) {
        return true;
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, Toolbar child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        startOffset=0;
        endOffset=mContext.getResources().getDimensionPixelOffset(R.dimen.banner_height)-child.getHeight();
        offset+=dyConsumed;
        if (offset<=startOffset){//alpha=0
            child.getBackground().setAlpha(0);
        }else if(offset>startOffset&&offset<endOffset){//alpha=0~255
            float percent=(offset-startOffset)/endOffset;
            int alpha=Math.round(percent*255);
            child.getBackground().setAlpha(alpha);
        }else if (offset>endOffset){//alpha=255
            child.getBackground().setAlpha(255);
        }
    }
}
