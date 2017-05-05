package demo.minifly.com.canvas_test;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

import demo.minifly.com.utils.DensityUtils;


/**
 * author ：minifly
 * date: 2017/2/28
 * time: 16:11
 * desc:斜线进度条
 */
public class ObliqueProgressbar  extends View {

    private Paint mPaint;
//    private float mProgress;
    private Context mContext;

    public ObliqueProgressbar(Context context) {
        this(context, null);
        mContext = context;
    }

    public ObliqueProgressbar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        mContext = context;
    }

    public ObliqueProgressbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mContext = context;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (current == 0) return;

        //碎片雨
        mPaint.setColor(Color.parseColor("#a96ecb"));
        mPaint.setStrokeWidth(3);
        Random random = new Random();
        int sx, sy;
        for (int i = 0; i < 80; i++) {
            sx = random.nextInt(getWidth() + 10);
            sy = random.nextInt(getHeight() + 10);
//            canvas.drawLine(sx, sy, sx+random.nextInt(5), sy+random.nextInt(5), mPaint);
            canvas.drawCircle(sx, sy, random.nextInt(5) + 1, mPaint);
        }


        //进度
        mPaint.setColor(Color.parseColor("#6Aa96ecb"));
        mPaint.setStrokeWidth(DensityUtils.dip2px(mContext,3));
        float x = current * (getWidth()/100f);
        for (int i = 0; i < x; i += 30) {
            canvas.drawLine(i - 30, -10, i + 30, getHeight() + 10, mPaint);
        }
    }
    private int temp = 0,current;
    private void initAnimator() {
        ValueAnimator animator = ValueAnimator.ofInt(0, temp);
        animator.setDuration(4000);
        animator.start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int count = (int)animation.getAnimatedValue();
                current = count;
                invalidate();
            }
        });
    }

    public void setProgress(float progress) {
        this.temp = (int)progress;
        initAnimator();
    }
}