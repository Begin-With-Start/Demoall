package com.library.animationlib;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

public class MyContainerFramelayout extends FrameLayout {
    private boolean scaleX;
    private boolean fadein;
    private boolean fadeout;
    private int height;
    private int width;

    public MyContainerFramelayout(@NonNull Context context) {
        super(context);
    }

    public MyContainerFramelayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyContainerFramelayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        height = getHeight();
        width = getWidth();
        super.onFinishInflate();
    }

    public boolean isScaleX() {
        return scaleX;
    }

    public void setScaleX(boolean scaleX) {
        this.scaleX = scaleX;
    }

    public boolean isFadein() {
        return fadein;
    }

    public void setFadein(boolean fadein) {
        this.fadein = fadein;
    }

    public boolean isFadeout() {
        return fadeout;
    }

    public void setFadeout(boolean fadeout) {
        this.fadeout = fadeout;
    }

    public  void setAnimationRatio(int ratio){
        setAlpha(ratio);
//        setTranslationX();
    }
}
