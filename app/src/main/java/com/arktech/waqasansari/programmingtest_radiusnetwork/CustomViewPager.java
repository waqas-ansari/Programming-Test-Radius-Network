package com.arktech.waqasansari.programmingtest_radiusnetwork;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by WaqasAhmed on 5/28/2016.
 */
public class CustomViewPager extends ViewPager {


    public CustomViewPager(Context context) {
        super(context);
    }

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        boolean wrapHeight = MeasureSpec.getMode(heightMeasureSpec)
                == MeasureSpec.AT_MOST;

        if (wrapHeight) {
            int width = getMeasuredWidth(), height = getMeasuredHeight();
            widthMeasureSpec = MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY);

            if (getChildCount() > 0) {
                View firstChild = getChildAt(0);
                firstChild.measure(widthMeasureSpec,
                        MeasureSpec.makeMeasureSpec(height, MeasureSpec.AT_MOST));

                height = firstChild.getMeasuredHeight();
            }

            heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);

            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }
}
