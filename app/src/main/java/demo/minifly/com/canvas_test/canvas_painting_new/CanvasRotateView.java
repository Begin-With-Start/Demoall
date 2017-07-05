package demo.minifly.com.canvas_test.canvas_painting_new;

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
 * date: 2017/7/4
 * time: 19:19
 * desc: 画个表盘
 */
public class CanvasRotateView extends View {
    private Context mContext;
    private Paint rotatePaint;
    private int viewWidth,viewHeight;

    public CanvasRotateView(Context context) {
        super(context);
        this.mContext = context;
        init();
    }

    public CanvasRotateView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init();
    }

    public void init(){
        rotatePaint = new Paint();
        rotatePaint.setStyle(Paint.Style.STROKE);
        rotatePaint.setStrokeWidth(DensityUtils.dip2px(mContext,1));
        rotatePaint.setAntiAlias(true);
    }

    /**
     * 因为父控件的一些改变也会改变当前的view的大小，所以，在这个地方需要监听onsizechange的回调。
     */
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
        canvas.translate(viewWidth/2,viewHeight/2);

        int r = Math.min(viewWidth/2,viewHeight/2);
        r = r - 40;

        rotatePaint.setColor(Color.BLACK);
        canvas.drawCircle(0,0,r,rotatePaint);

        rotatePaint.setStrokeWidth(DensityUtils.dip2px(mContext,1));
        rotatePaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(0,0,DensityUtils.dip2px(mContext,2),rotatePaint);
        rotatePaint.setStyle(Paint.Style.STROKE);

        for(int i = 0 ; i < 60 ; i++){
            if(i%15 == 0){
                rotatePaint.setStrokeWidth(DensityUtils.dip2px(mContext,2));
                canvas.drawLine(0,r-DensityUtils.dip2px(mContext,18),0,r,rotatePaint);
            }else{
                rotatePaint.setStrokeWidth(DensityUtils.dip2px(mContext,1));
                canvas.drawLine(0,r-DensityUtils.dip2px(mContext,10),0,r,rotatePaint);
            }
            canvas.rotate(6);
        }
    }
}







