package demo.minifly.com.canvas_test.canvas_new;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import demo.minifly.com.R;
import demo.minifly.com.utils.DensityUtils;

/**
 * author ：minifly
 * date: 2017/7/3
 * time: 14:43
 * desc: 画椭圆
 */
public class CanvasOvalView extends View {
    private Context mContext;
    private Paint ovalPaint;

    public CanvasOvalView(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public CanvasOvalView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    public void init(){
        ovalPaint = new Paint();
        ovalPaint.setColor(Color.BLACK);
        ovalPaint.setStyle(Paint.Style.FILL);
        ovalPaint.setStrokeWidth(DensityUtils.dip2px(mContext,1));

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(getResources().getColor(R.color.canvas_own_1_color));


        RectF rectC = new RectF(100,100,DensityUtils.dip2px(mContext,80),DensityUtils.dip2px(mContext,100));
        canvas.drawOval(rectC, ovalPaint);
        //画一个正方形就是一个圆了

        RectF rectF = new RectF(DensityUtils.dip2px(mContext,100),DensityUtils.dip2px(mContext,10),DensityUtils.dip2px(mContext,180),DensityUtils.dip2px(mContext,90));
        canvas.drawOval(rectF, ovalPaint);

        //直接画一个圆
        canvas.drawCircle((DensityUtils.dip2px(mContext,250)),DensityUtils.dip2px(mContext,50),DensityUtils.dip2px(mContext,50),ovalPaint);

    }
}










