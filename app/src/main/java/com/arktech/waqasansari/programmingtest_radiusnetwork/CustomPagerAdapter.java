package com.arktech.waqasansari.programmingtest_radiusnetwork;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CustomPagerAdapter extends PagerAdapter{
    Context context;
    int[] imageListsIds;

    int[] customImgIds = {R.id.img1, R.id.img2, R.id.img3, R.id.img4};
    int[] customTextViewIds = {R.id.num1, R.id.num2, R.id.num3, R.id.num4};

    public CustomPagerAdapter(Context context, int[] imageListsIds){
        this.imageListsIds = imageListsIds;
        this.context = context;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();

        View viewItem = inflater.inflate(R.layout.view_pager_item, container, false);


        CustomImageView customImageView[] = new CustomImageView[customImgIds.length];
        CustomTextView customTextView[] = new CustomTextView[customTextViewIds.length];

        for(int i=0; i<customImgIds.length; i++){
            customImageView[i] = (CustomImageView) viewItem.findViewById(customImgIds[i]);
            customTextView[i] = (CustomTextView) viewItem.findViewById(customTextViewIds[i]);
        }

        int checker = position + 3*position;

        int count=0;
        for(int i=checker; i<checker+4; i++){
            customImageView[count].setImageResource(imageListsIds[i]);
            customTextView[count].setText(String.valueOf(i+1));
            count++;
        }

        container.addView(viewItem);

        return viewItem;
    }

    @Override
    public int getCount() {
        return (int) Math.ceil(imageListsIds.length/4);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}