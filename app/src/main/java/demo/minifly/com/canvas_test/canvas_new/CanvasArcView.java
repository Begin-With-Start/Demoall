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
 * desc: 画圆弧
 */
public class CanvasArcView extends View {
    private Context mContext;
    private Paint arcPaint;

    public CanvasArcView(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public CanvasArcView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    public CanvasArcView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    public CanvasArcView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        mContext = context;
        init();
    }

    public void init(){
        arcPaint = new Paint();
        arcPaint.setColor(Color.BLACK);
        arcPaint.setStyle(Paint.Style.FILL);
        arcPaint.setStrokeWidth(DensityUtils.dip2px(mContext,1));
        arcPaint.setAntiAlias(true);//消除锯齿

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(getResources().getColor(R.color.canvas_own_2_color));

        // 第一种  有中心点的圆弧
        RectF rectC = new RectF(DensityUtils.dip2px(mContext,20),DensityUtils.dip2px(mContext,20),DensityUtils.dip2px(mContext,100),DensityUtils.dip2px(mContext,100));
        canvas.drawArc(rectC,0,90,true,arcPaint);

        // 第一种  没有中心点的圆弧
        RectF rectC2 = new RectF(DensityUtils.dip2px(mContext,130),DensityUtils.dip2px(mContext,20),DensityUtils.dip2px(mContext,200),DensityUtils.dip2px(mContext,100));
        canvas.drawArc(rectC2,0,90,false,arcPaint);


    }
}










