package demo.minifly.com.canvas_test.canvas_painting_new;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import demo.minifly.com.R;
import demo.minifly.com.utils.DensityUtils;

/**
 * author ：minifly
 * date: 2017/7/4
 * time: 16:30
 * desc: cavas的translate操作，每次移动的都是上次的画布，而不是相对于0，0的移动
 * 而且每次的画布操作只会影响下次的绘制，而不会影响上次的绘制。
 */
public class CanvasTranslateView extends View {
    private Context mContext;
    private Paint circlePaint;
    private int screenHeight;
    private int screenWidth;

    public CanvasTranslateView(Context context) {
        super(context);
        this.mContext = context;
        init();
    }

    public CanvasTranslateView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init();
    }

    public void init(){
        circlePaint = new Paint();
        circlePaint.setStyle(Paint.Style.FILL);
        circlePaint.setStrokeWidth(DensityUtils.dip2px(mContext,1));
        circlePaint.setAntiAlias(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        screenWidth = w;
        screenHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //画坐标系 starting
        canvas.drawColor(getResources().getColor(R.color.canvas_own_line_color));
        circlePaint.setColor(getResources().getColor(R.color.canvas_own_red_4_color));
        circlePaint.setStrokeWidth(DensityUtils.dip2px(mContext,1));
        canvas.drawLines(new float[]{0, screenHeight /2, screenWidth, screenHeight /2, screenWidth /2,0, screenWidth/2, screenHeight},circlePaint);
        canvas.drawLines(new float[]{ screenWidth -40, screenHeight /2 -40, screenWidth, screenHeight /2,  screenWidth -40 , screenHeight /2 + 40,  screenWidth, screenHeight /2},circlePaint);
        canvas.drawLines(new float[]{screenWidth/2-40, screenHeight-40, screenWidth/2, screenHeight, screenWidth/2+40, screenHeight-40, screenWidth/2, screenHeight},circlePaint);
        /**
         * 1.先设置字体的大小
         * 2.绘制字体的x，y轴的起始位置
         */
        circlePaint.setTextSize(DensityUtils.dip2px(mContext,17));
        canvas.drawText("x",screenWidth/2 + DensityUtils.dip2px(mContext,15),screenHeight,circlePaint);
        canvas.drawText("y",screenWidth - DensityUtils.dip2px(mContext,20),screenHeight/2 + 50,circlePaint);
        //画坐标系 ending


        //原图画一个
        circlePaint.setColor(getResources().getColor(R.color.canvas_own_green_2_color));
        canvas.translate(DensityUtils.dip2px(mContext,25),DensityUtils.dip2px(mContext,25));
        canvas.drawCircle(0,0,DensityUtils.dip2px(mContext,50)/2,circlePaint);

        //斜方向的一个移动
        canvas.translate(DensityUtils.dip2px(mContext,50),DensityUtils.dip2px(mContext,50));
        circlePaint.setColor(getResources().getColor(R.color.canvas_own_green_5_color));
        canvas.drawCircle(0,0,DensityUtils.dip2px(mContext,50)/2,circlePaint);

        //平移的操作
        canvas.translate(DensityUtils.dip2px(mContext,50+10) ,0);
        circlePaint.setColor(getResources().getColor(R.color.canvas_own_green_9_color));
        canvas.drawCircle(0,0,DensityUtils.dip2px(mContext,50)/2,circlePaint);

    }
}















