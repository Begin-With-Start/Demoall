package demo.minifly.com.canvas_test.canvas_painting_new;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import demo.minifly.com.R;
import demo.minifly.com.utils.DensityUtils;

/**
 * author ：minifly
 * date: 2017/7/4
 * time: 16:30
 * desc: 每次移动的都是上次的画布，而不是相对于0，0的移动
 * 而且每次的画布操作只会影响下次的绘制，而不会影响上次的绘制。
 * canvas 画布的一个scale操作，放大缩小的操作
 * 做一个让人发晕的东西出来。
 */
public class CanvasScaleNewView extends View {
    private Context mContext;
    private Paint rectPaint;
    private int viewHeight;
    private int viewWidth;

    public CanvasScaleNewView(Context context) {
        super(context);
        this.mContext = context;
        init();
    }

    public CanvasScaleNewView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init();
    }

    public void init(){
        rectPaint = new Paint();
        rectPaint.setStyle(Paint.Style.STROKE);
        rectPaint.setStrokeWidth(DensityUtils.dip2px(mContext,1));
        rectPaint.setAntiAlias(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        viewWidth = w;
        viewHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //画坐标系 starting
        canvas.drawColor(getResources().getColor(R.color.canvas_own_point_color));
        rectPaint.setColor(getResources().getColor(R.color.canvas_own_red_4_color));
        rectPaint.setStrokeWidth(DensityUtils.dip2px(mContext,1));
        canvas.drawLines(new float[]{0, viewHeight /2, viewWidth, viewHeight /2, viewWidth /2,0, viewWidth /2, viewHeight}, rectPaint);
        canvas.drawLines(new float[]{ viewWidth -40, viewHeight /2 -40, viewWidth, viewHeight /2,  viewWidth -40 , viewHeight /2 + 40, viewWidth, viewHeight /2}, rectPaint);
        canvas.drawLines(new float[]{viewWidth /2-40, viewHeight -40, viewWidth /2, viewHeight, viewWidth /2+40, viewHeight -40, viewWidth /2, viewHeight}, rectPaint);
        /**
         * 1.先设置字体的大小
         * 2.绘制字体的x，y轴的起始位置
         */

        rectPaint.setStrokeWidth(DensityUtils.dip2px(mContext,4));
        canvas.translate(viewWidth/2,viewHeight/2);

        rectPaint.setColor(Color.BLACK);
        rectPaint.setTextSize(DensityUtils.dip2px(mContext,17));
        canvas.drawText("x", viewWidth /2 + DensityUtils.dip2px(mContext,15), viewHeight, rectPaint);
        canvas.drawText("y", viewWidth - DensityUtils.dip2px(mContext,20), viewHeight /2 + 50, rectPaint);
        //画坐标系 ending

        Rect rect = new Rect(-viewWidth/2,-viewHeight/2,viewWidth/2,viewHeight/2);

        for(int i=0 ; i < 30 ;i++){
            canvas.scale(0.9f,0.9f);
            canvas.drawRect(rect,rectPaint);
        }

    }
}















