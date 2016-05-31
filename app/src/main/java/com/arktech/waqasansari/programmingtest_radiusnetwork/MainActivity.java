package com.arktech.waqasansari.programmingtest_radiusnetwork;

import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    int[] imageList = {R.drawable.image_1, R.drawable.image_2, R.drawable.image_3,
            R.drawable.image_4, R.drawable.image_5, R.drawable.image_6,
            R.drawable.image_7, R.drawable.image_8, R.drawable.image_9,
            R.drawable.image_10, R.drawable.image_11, R.drawable.image_12};

    LinearLayout indicatorLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        indicatorLayout = (LinearLayout) findViewById(R.id.indicatorLayout);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        CustomPagerAdapter pagerAdapter = new CustomPagerAdapter(MainActivity.this, imageList);
        viewPager.setAdapter(pagerAdapter);

        final int noOfIndicators = (int) Math.ceil(imageList.length/4);

        for(int i = 0; i < noOfIndicators; i++)
            addIndicatorButton(i);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            private int currentPosition;
            private int scrollState;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentPosition = position;
                for (int i = 0; i < noOfIndicators; i++) {
                    if (i == position)
                        ((ImageButton) findViewById(i)).setImageResource(R.drawable.btn_on);
                    else
                        ((ImageButton) findViewById(i)).setImageResource(R.drawable.btn_off);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                handleScrollState(state);
                scrollState = state;
            }

            //*************************************************************************************
            private void handleScrollState(final int state) {
                if (state == ViewPager.SCROLL_STATE_IDLE) {
                    setNextItemIfNeeded();
                }
            }

            private void setNextItemIfNeeded() {
                if (!isScrollStateSettling()) {
                    handleSetNextItem();
                }
            }

            private boolean isScrollStateSettling() {
                return scrollState == ViewPager.SCROLL_STATE_SETTLING;
            }

            private void handleSetNextItem() {
                final int lastPosition = viewPager.getAdapter().getCount() - 1;
                if (currentPosition == 0) {
                    viewPager.setCurrentItem(lastPosition, false);
                } else if (currentPosition == lastPosition) {
                    viewPager.setCurrentItem(0, false);
                }
            }
            //*************************************************************************************

        });

    }

    private void addIndicatorButton(final int id){
        ImageButton btnIndicator = new ImageButton(getApplicationContext());
        btnIndicator.setId(id);
        btnIndicator.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        btnIndicator.setBackgroundColor(Color.TRANSPARENT);
        btnIndicator.setPadding(20, 20, 20, 20);
        if(id == 0)
            btnIndicator.setImageResource(R.drawable.btn_on);
        else btnIndicator.setImageResource(R.drawable.btn_off);
        btnIndicator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(id);
            }
        });
        indicatorLayout.addView(btnIndicator);
    }

}
