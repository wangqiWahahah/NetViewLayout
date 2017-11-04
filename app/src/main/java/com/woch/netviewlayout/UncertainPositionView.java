package com.woch.netviewlayout;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by wangdake on 2017/10/17.
 */

public class UncertainPositionView extends ViewGroup {

    private List<ViewLoctionEntity> viewLoctionEntityList;
    private int width, height;

    public UncertainPositionView(Context context) {
        this(context, null, 0);
    }

    public UncertainPositionView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public UncertainPositionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        width = ScreenUtils.getScreenWidth(getContext());
        height = ScreenUtils.getScreenHeight(getContext());
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public UncertainPositionView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        this(context, attrs, defStyleAttr);
    }

    public void setList(List<ViewLoctionEntity> viewLoctionEntityList){

        this.viewLoctionEntityList = viewLoctionEntityList;

        removeAllViews();

        for (ViewLoctionEntity v : viewLoctionEntityList) {

            ImageView iv = new ImageView(getContext());
            iv.setBackgroundColor(Color.parseColor("#b36d61"));
            LayoutParams lp = new LayoutParams((int) ScreenUtils.dp2px(Float.parseFloat(v.getW()), getContext()), (int) ScreenUtils.dp2px(Float.parseFloat(v.getH()), getContext()));
            iv.setLayoutParams(lp);
            //iv.setId();
            addView(iv);

        }

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {


        for (int i=0; i<viewLoctionEntityList.size(); i++){

            View view = getChildAt(i);
            int l1 = (int) (width* Double.parseDouble(viewLoctionEntityList.get(i).getX()));
            int t1 = (int) (height* Double.parseDouble(viewLoctionEntityList.get(i).getY()));
            int mWidth = (int) ScreenUtils.dp2px(Float.parseFloat(viewLoctionEntityList.get(i).getW()), getContext());
            int mHeight = (int) ScreenUtils.dp2px(Float.parseFloat(viewLoctionEntityList.get(i).getH()), getContext());
            int r1 = l1/100+mWidth;
            int b1 = mHeight+t1/100;
            view.layout(l1/100, t1/100, r1, b1);

        }



    }

    private static final String TAG = "UncertainPositionView";




}
