package demo.minifly.com.canvas_test.canvas_new;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import demo.minifly.com.R;
import demo.minifly.com.utils.DensityUtils;

/**
 * author ：minifly
 * date: 2017/7/3
 * time: 14:07
 * desc: 划线的canvas
 */
public class CanvasPointView extends View {

    private Paint pointPaint;
    private Context mContext;

    public CanvasPointView(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public CanvasPointView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }


    public void init(){
        pointPaint = new Paint();
        pointPaint.setStyle(Paint.Style.FILL);//填充的模式 Style.FILL：实心。
//        pointPaint.setStyle(Paint.Style.STROKE);//填充的模式  Style.STROKE：空心。
        pointPaint.setColor(Color.BLACK);
        pointPaint.setStrokeWidth(DensityUtils.dip2px(mContext,3));

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(getResources().getColor(R.color.canvas_own_point_color));//先画背景，再画点，这样才不会被冲掉
        //先画点的话，会被冲掉的。

        canvas.drawPoint(0,0, pointPaint);

        canvas.drawPoints(new float[]{100,100,200,200,300,300,400,400}, pointPaint);
    }
}
