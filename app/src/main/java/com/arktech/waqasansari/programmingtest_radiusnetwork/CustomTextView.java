package com.arktech.waqasansari.programmingtest_radiusnetwork;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by WaqasAhmed on 5/29/2016.
 */
public class CustomTextView extends TextView {

    public CustomTextView(Context context) {
        super(context);
        init(null);
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for(int i=0; i<10; i++)
            super.onDraw(canvas);
    }

    private void init (AttributeSet attrs){
        if (attrs != null){
            Typeface typeface;
            if(this.getId() == R.id.txtGalleryHeading)
                typeface = Typeface.createFromAsset(getContext().getAssets(), "custom_font_title_text.ttf");
            else typeface = Typeface.createFromAsset(getContext().getAssets(), "custom_font_image_number.ttf");
            setTypeface(typeface);
        }
    }
}
