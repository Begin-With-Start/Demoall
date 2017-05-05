package demo.minifly.com.object_animator_test;

import android.view.View;

/**
 * author ：minifly
 * date: 2016/12/19
 * time: 11:24
 * desc: 自定义的属性值
 */
public class WrapBean {
    private View targetView ;

    public WrapBean(View targetView){
        this.targetView = targetView;
    }

    public int getWidth(){
        return targetView.getLayoutParams().width;
    }

    public void setWidth(int width){
        targetView.getLayoutParams().width = width;
        targetView.requestLayout();
    }
}
