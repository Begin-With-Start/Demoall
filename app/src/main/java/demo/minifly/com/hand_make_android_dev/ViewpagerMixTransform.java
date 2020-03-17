package demo.minifly.com.hand_make_android_dev;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * create by minifly on 2019-11-07 19:10
 * description: viewpager scoll anmator ...  depends on view animator
 */
public class ViewpagerMixTransform implements ViewPager.PageTransformer {

    private static final float DEFAULT_CENTER = 0.5f;
    private static float MIN_ALPH = 0.5f;
    private static float NORMAL_SCALE = 2f / 3;

    /**
     * set alpha and scalesize for viewpager
     *
     * @param minAlph     min alph for viewpager next child and previous child
     * @param normalScale min scal for viewpager next child and previous child
     */
    public ViewpagerMixTransform(float minAlph, float normalScale) {
        MIN_ALPH = minAlph;  //透明度设置
        NORMAL_SCALE = normalScale; //大小变换变化设置
    }

    public ViewpagerMixTransform() {
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void transformPage(@NonNull View page, float position) {
        int pageWidth = page.getWidth();
        int pageHeight = page.getHeight();
        page.setPivotY(pageHeight / 2);
        page.setPivotX(pageWidth);

        page.setScaleX(NORMAL_SCALE);
        page.setScaleY(NORMAL_SCALE);

        if (position < -1) {
            page.setAlpha(MIN_ALPH);
            page.setScaleX(NORMAL_SCALE);
            page.setScaleY(NORMAL_SCALE);
            page.setPivotX(pageWidth);
        } else if (position <= 1) { // [-1,1]

            if (position < 0) { //[0，-1]
                float factor = MIN_ALPH + (1 - MIN_ALPH) * (1 + position);
                float scaleTmp = NORMAL_SCALE + (1 - NORMAL_SCALE) * (1 + position);
                page.setAlpha(factor);
                page.setScaleY(scaleTmp);
                page.setScaleX(scaleTmp);
                page.setPivotX(pageWidth * (DEFAULT_CENTER + (DEFAULT_CENTER * -position)));
            } else {//[1，0]
                float factor = MIN_ALPH + (1 - MIN_ALPH) * (1 - position);
                float scaleTmp = NORMAL_SCALE + (1 - NORMAL_SCALE) * (1 - position);
                page.setAlpha(factor);
                page.setScaleY(scaleTmp);
                page.setScaleX(scaleTmp);
                page.setPivotX(pageWidth * ((1 - position) * DEFAULT_CENTER));
            }

        } else { // (1,+Infinity]
            page.setAlpha(MIN_ALPH);
            page.setScaleX(NORMAL_SCALE);
            page.setScaleY(NORMAL_SCALE);
            page.setPivotX(0);
//            page.setPivotX(NORMAL_SCALE);
        }
    }
}
