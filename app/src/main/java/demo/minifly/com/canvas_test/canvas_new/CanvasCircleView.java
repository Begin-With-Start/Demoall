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
 * desc: 画圆的几种style的方式，一个是填充，一个是空心，一个填充加描边。
 */
public class CanvasCircleView extends View {
    private Context mContext;
    private Paint stylePaint;

    public CanvasCircleView(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public CanvasCircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    public CanvasCircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    public CanvasCircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        mContext = context;
        init();
    }

    public void init(){
        stylePaint = new Paint();
        stylePaint.setColor(Color.BLACK);
        stylePaint.setStrokeWidth(DensityUtils.dip2px(mContext,1));
        stylePaint.setAntiAlias(true);//消除锯齿

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(getResources().getColor(R.color.canvas_own_3_color));

        // 第一种  填充的方式
        stylePaint.setStyle(Paint.Style.FILL);
        RectF rect = new RectF(DensityUtils.dip2px(mContext,20),DensityUtils.dip2px(mContext,20),DensityUtils.dip2px(mContext,100),DensityUtils.dip2px(mContext,100));
        canvas.drawOval(rect,stylePaint);

        //第二种的填充方式， 空心
        RectF rect2 = new RectF(DensityUtils.dip2px(mContext,120),DensityUtils.dip2px(mContext,20),DensityUtils.dip2px(mContext,200),DensityUtils.dip2px(mContext,100));
        stylePaint.setStyle(Paint.Style.STROKE);
        canvas.drawOval(rect2,stylePaint);

        //第三种填充方式， 填充加描边
        RectF rect3 = new RectF(DensityUtils.dip2px(mContext,220),DensityUtils.dip2px(mContext,20),DensityUtils.dip2px(mContext,300),DensityUtils.dip2px(mContext,100));
        stylePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawOval(rect3,stylePaint);




    }
}










