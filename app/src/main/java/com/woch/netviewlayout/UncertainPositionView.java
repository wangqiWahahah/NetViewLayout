package com.woch.netviewlayout;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
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

    private int MaxWidth = 0;

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

        if (MaxWidth == 0){
            addView(new View(getContext()));
        }else {
            removeAllViews();

            for (final ViewLoctionEntity vLE : viewLoctionEntityList) {

                ImageView iv = new ImageView(getContext());
                iv.setBackgroundColor(Color.parseColor("#b36d61"));
                LayoutParams lp = new LayoutParams((int) ScreenUtils.dp2px(Float.parseFloat(vLE.getW()), getContext()), (int) ScreenUtils.dp2px(Float.parseFloat(vLE.getH()), getContext()));
                iv.setLayoutParams(lp);
                iv.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //if (vLE.getIntentName() != null && !"".equals(vLE.getIntentName())){

                            Class send = null;
                            try {
                                //send = Class.forName(vLE.getIntentName());
                                send = Class.forName("com.woch.netviewlayout.ShowActivity");
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            }
                            Intent intent = new Intent();
                            intent.setClass(getContext(), send);
                            getContext().startActivity(intent);

                        //}

                    }
                });
                //iv.setId();
                addView(iv);

            }

            width = MaxWidth;
            height = MaxWidth/3*2;

        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        if (MaxWidth == 0){

            MaxWidth = getMaxWidth(widthMeasureSpec);
            setList(viewLoctionEntityList);

        }

        setMeasuredDimension(MaxWidth, MaxWidth/3*2);

    }

    private int getMaxWidth(int widthMeasureSpec) {

        int result = 0;
        int specMode = MeasureSpec.getMode(widthMeasureSpec);
        int specSize = MeasureSpec.getSize(widthMeasureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            // We were told how big to be
            result = specSize;
        } else {
            // Measure the text
            // result = (int) mTextPaint.measureText(mText) + getPaddingLeft()
            // + getPaddingRight();
            if (specMode == MeasureSpec.AT_MOST) {
                // Respect AT_MOST value if that was what is called for by
                // measureSpec
                result = Math.min(result, specSize);
            }
        }
        return result;

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
