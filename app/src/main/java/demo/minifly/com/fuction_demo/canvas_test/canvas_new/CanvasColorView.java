package demo.minifly.com.fuction_demo.canvas_test.canvas_new;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import demo.minifly.com.R;
import demo.minifly.com.fuction_demo.utils.DensityUtils;

/**
 * author ：minifly
 * date: 2017/7/3
 * time: 14:07
 * desc:
 */
public class CanvasColorView extends View {

    private Paint colorPaint;
    private Context mContext;

    public CanvasColorView(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public CanvasColorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }


    public void init(){
        colorPaint = new Paint();
        colorPaint.setStyle(Paint.Style.FILL);//填充的模式 Style.FILL：实心。
//        colorPaint.setStyle(Paint.Style.STROKE);//填充的模式  Style.STROKE：空心。
        /**
         * STROKE                //描边
         FILL                  //填充
         FILL_AND_STROKE       //描边加填充
         */
        colorPaint.setColor(getResources().getColor(R.color.canvas_own_color));
        colorPaint.setStrokeWidth(DensityUtils.dip2px(mContext,1));

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(getResources().getColor(R.color.canvas_own_color));


    }
}
