package com.library.animationlib;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class MyContainRelativelayout extends RelativeLayout {

    public MyContainRelativelayout(Context context) {
        super(context);
    }

    public MyContainRelativelayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyContainRelativelayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        //从childview中的自定义属性改到myframelayout容器；

        return new MyLayoutParams(getContext(), attrs);
    }

    @Override
    public void addView(View child, ViewGroup.LayoutParams params) {
        //在布局中 inflateview add到layout中之前进行重写，把流程从系统拿到自定义的布局容器中；

        MyLayoutParams myLayoutParams = (MyLayoutParams) params;
        if (myLayoutParams.isSelfDealAnimation()) {
            MyContainerFramelayout framelayout = new MyContainerFramelayout(getContext(), null);
            framelayout.setFadein(myLayoutParams.fadein);
            framelayout.setFadeout(myLayoutParams.fadeout);
            framelayout.setScaleX(myLayoutParams.scaleX);
            framelayout.addView(child);
            super.addView(framelayout, params);
        } else {
            super.addView(child, params);
        }

    }

    private class MyLayoutParams extends RelativeLayout.LayoutParams {

        private boolean fadein;
        private boolean fadeout;
        private boolean scaleX;
//        private final boolean scaleY;

        public MyLayoutParams(Context context, AttributeSet attrs) {
            super(context, attrs);
            TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.MyContainRelativelayout);
            fadein = array.getBoolean(R.styleable.MyContainRelativelayout_fadein, false);
            fadeout = array.getBoolean(R.styleable.MyContainRelativelayout_fadeout, false);
            scaleX = array.getBoolean(R.styleable.MyContainRelativelayout_scalex, false);
//            scaleY = array.getBoolean(R.styleable.MyContainRelativelayout_scaley , false);
        }

        public boolean isSelfDealAnimation() {
            return fadein || fadeout || scaleX;
        }
    }


}
