package demo.minifly.com.fuction_demo.object_animator_test;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

/**
 * author ：minifly
 * date: 2016/12/19
 * time: 11:24
 * desc: 自定义的属性值
 */
public class ColorBean {
    private View targetView ;

    public int getColor() {
        Drawable background = targetView.getBackground();
        int color = 0;

        if (background instanceof ColorDrawable) {//drawble 与 color 分开取值
            ColorDrawable colordDrawable = (ColorDrawable) background;
            color = colordDrawable.getColor();
        }
        return color;
    }

    public void setColor(int color) {
        this.targetView.setBackgroundColor(color);
    }

    public ColorBean(View targetView){
        this.targetView = targetView;
    }

}
