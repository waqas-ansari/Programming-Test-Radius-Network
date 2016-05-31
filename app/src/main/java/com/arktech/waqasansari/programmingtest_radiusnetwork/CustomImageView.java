package com.arktech.waqasansari.programmingtest_radiusnetwork;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.MaskFilter;
import android.graphics.Paint;
import android.graphics.PathEffect;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.support.v4.media.MediaBrowserCompat;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by WaqasAhmed on 5/30/2016.
 */
public class CustomImageView extends ImageView {

    public CustomImageView(Context context) {
        super(context);
    }

    public CustomImageView(Context context, AttributeSet attrs){
        super(context, attrs);
    }

    public CustomImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CustomImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);
    }

    private Bitmap addWhiteBorder(Bitmap bmp, int borderSize) {
        Bitmap bmpWithBorder = Bitmap.createBitmap(bmp.getWidth() + borderSize * 2, bmp.getHeight() + borderSize * 2, bmp.getConfig());
        Canvas canvas = new Canvas(bmpWithBorder);
        canvas.drawColor(Color.WHITE);
        canvas.drawBitmap(bmp, borderSize, borderSize, null);
        return bmpWithBorder;
    }

    @Override
    public void setImageResource(int resId) {
        Bitmap rawBmp = BitmapFactory.decodeResource(getContext().getResources(), resId);
        Bitmap rawMask = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.image_frame_arc_filled);
        Bitmap rawBorderMask = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.image_frame_arc);

        Bitmap bmp = Bitmap.createScaledBitmap(rawBmp, rawBmp.getWidth(), rawBmp.getHeight(), true);
        Bitmap mask = Bitmap.createScaledBitmap(rawMask, bmp.getWidth(), bmp.getHeight(), true);
        Bitmap borderMask = Bitmap.createScaledBitmap(rawBorderMask, mask.getWidth(), mask.getHeight(), true);

        Bitmap bitmap = Bitmap.createBitmap(mask.getWidth(), mask.getHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmap);
        canvas.drawBitmap(bmp, 0, 0, null);


        Paint maskPaint = new Paint();
        maskPaint.setXfermode(
                new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawBitmap(mask, 0, 0, maskPaint);

        maskPaint = new Paint();
        maskPaint.setXfermode(
                new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
        canvas.drawBitmap(borderMask, 0, 0, maskPaint);

        this.setImageBitmap(bitmap);
    }
}
