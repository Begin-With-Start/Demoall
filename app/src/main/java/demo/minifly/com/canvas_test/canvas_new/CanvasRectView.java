package demo.minifly.com.canvas_test.canvas_new;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
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
 * desc: 画矩形
 */
public class CanvasRectView extends View {
    private Context mContext;
    private Paint rectPaint;

    public CanvasRectView(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public CanvasRectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    public CanvasRectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    public CanvasRectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        mContext = context;
        init();
    }

    public void init(){
        rectPaint = new Paint();
        rectPaint.setColor(Color.BLACK);
        rectPaint.setStyle(Paint.Style.FILL);
        rectPaint.setStrokeWidth(DensityUtils.dip2px(mContext,1));

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(getResources().getColor(R.color.canvas_own_rect_color));


        canvas.drawRect(0,0,100,100,rectPaint);

        Rect rect = new Rect(110,110,210,210);
        canvas.drawRect(rect,rectPaint);

        RectF rectF = new RectF(220,220,330,330);
        canvas.drawRect(rectF,rectPaint);

        // 第一种
        RectF rectC = new RectF(350,0,440,400);
        canvas.drawRoundRect(rectC,30,30,rectPaint);


    }
}










