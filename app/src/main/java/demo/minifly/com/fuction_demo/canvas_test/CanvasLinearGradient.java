package demo.minifly.com.fuction_demo.canvas_test;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

/**
 * 作者：minifly on 2016/12/4 17:16
 * shader : 渐变 与渲染
 * lineargradient ： 线性
 * bitmapshader : 位图
 * radialgradient : 光束的渲染
 */
public class CanvasLinearGradient extends View {
    public CanvasLinearGradient(Context context) {
        super(context);
    }

    public CanvasLinearGradient(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CanvasLinearGradient(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CanvasLinearGradient(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int width = getWidth(); //以控件的宽高进行设置。
        int height = getHeight(); //控件的宽高进行设置。

        Paint paint = new Paint();
        paint.setShader(new LinearGradient(0,0,0,height, Color.BLUE,Color.WHITE, Shader.TileMode.REPEAT));//从（0,0）到（0,height）的色彩渐变
        canvas.drawRect(0,0,height,height,paint);//(0,0)到(height,height)的一个矩形
    }

}
