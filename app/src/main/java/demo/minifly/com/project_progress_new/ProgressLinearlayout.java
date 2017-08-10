package demo.minifly.com.project_progress_new;

import android.animation.AnimatorSet;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;

import java.lang.reflect.Field;

import demo.minifly.com.R;
import demo.minifly.com.entry.Point;
import demo.minifly.com.utils.DensityUtils;
import demo.minifly.com.utils.LogUtils;

/**
 * author ：minifly
 * date: 2017/5/3
 * time: 15:14
 * desc:
 */
public class ProgressLinearlayout extends LinearLayout {
    private Context mContext;
    private int progress1,progress2,progress3,progress4;
    private ProgressView progressView1;
    private ProgressView progressView2;
    private ProgressView progressView3;
    private ProgressView progressView4;

    private LinearLayout progressLin;
    private int[] position1 = new int[2];
    private int[] position2 = new int[2];
    private int[] position3 = new int[2];
    private int[] position4 = new int[2];

    //圆形点的大小
    int dotSize;

    //中间点的坐标
    float centerX1 , centerY1,centerX2,centerY2,centerX3,centerY3,centerX4,centerY4;
    private Paint dotPaint;
    private Paint linePaint;
    private Point point1,tempPoint1;
    private Point point2,tempPoint2;
    private Point point3,tempPoint3;
    private Point point4;

    public ProgressLinearlayout(Context context) {
        super(context);
        this.mContext = context;
        initView();
    }

    public ProgressLinearlayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView();
    }

    public ProgressLinearlayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initView();
    }

    public ProgressLinearlayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes, Context mContext) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.mContext = mContext;
        initView();
    }

    public void initView(){
        setWillNotDraw(false);
        dotSize = DensityUtils.dip2px(mContext,5);

        this.setOrientation(HORIZONTAL);

        LayoutInflater mInflater = LayoutInflater.from(mContext);
        View myView = mInflater.inflate(R.layout.mine_linearlayout, null);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        addView(myView,layoutParams);

        progressView1 = (ProgressView) findViewById(R.id.progress_id_1);
        progressView2 = (ProgressView) findViewById(R.id.progress_id_2);
        progressView3 = (ProgressView) findViewById(R.id.progress_id_3);
        progressView4 = (ProgressView) findViewById(R.id.progress_id_4);
        progressLin = (LinearLayout) findViewById(R.id.progress_lin_id);



        //在之前进行绘制操作。
        dotPaint = new Paint();
        dotPaint.setColor(getResources().getColor(R.color.white));
        dotPaint.setStyle(Paint.Style.STROKE);
        dotPaint.setAntiAlias(true);//去锯齿
        dotPaint.setStrokeCap(Paint.Cap.BUTT);//圆形的笔触
        dotPaint.setStrokeWidth(dotSize);
        linePaint = new Paint();
        linePaint.setColor(getResources().getColor(R.color.white));
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setAntiAlias(true);//去锯齿
        linePaint.setStrokeCap(Paint.Cap.BUTT);//圆形的笔触
        linePaint.setStrokeWidth(DensityUtils.dip2px(mContext,2));

    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if(tempPoint1!=null){
            canvas.drawCircle(centerX1,centerY1,dotSize/2, dotPaint);
        }

        if(tempPoint1!=null && point2 !=null && tempPoint1.getX()<=point2.getX()){//还在途中
            canvas.drawLine(centerX1,centerY1,tempPoint1.getX(),tempPoint1.getY(),linePaint);
        }
        if(tempPoint2!=null&& point3 !=null  && tempPoint2.getX()<=point3.getX()){
            canvas.drawCircle(centerX2,centerY2,dotSize/2, dotPaint);
            canvas.drawLine(centerX2,centerY2,tempPoint2.getX(),tempPoint2.getY(),linePaint);
        }
        if(tempPoint3!=null&& point4 !=null  && tempPoint3.getX()<point4.getX()){
            canvas.drawCircle(centerX3,centerY3,dotSize/2, dotPaint);
            canvas.drawLine(centerX3,centerY3,tempPoint3.getX(),tempPoint3.getY(),linePaint);
        }else if(tempPoint3!=null&& point4 !=null  && tempPoint3.getX() == point4.getX()){//把第四个点画上
            canvas.drawCircle(centerX4,centerY4,dotSize/2, dotPaint);
            canvas.drawCircle(centerX3,centerY3,dotSize/2, dotPaint);
            canvas.drawLine(centerX3,centerY3,tempPoint3.getX(),tempPoint3.getY(),linePaint);
        }
    }


    //设置值
    public void setData(int progress1,int progress2 , int progress3 , int progress4){
        this.progress1 = progress1;
        this.progress2 = progress2;
        this.progress3 = progress3;
        this.progress4 = progress4;

        progressView1.setProgressint(progress1);
        progressView2.setProgressint(progress2);
        progressView3.setProgressint(progress3);
        progressView4.setProgressint(progress4);

        calcuLoc();

    }

    //计算所有的点的坐标
    public void calcuLoc(){
        ViewTreeObserver vto = progressView4.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener(){
            @Override
            public void onGlobalLayout() {
                progressView1.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                progressView2.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                progressView3.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                progressView4.getViewTreeObserver().removeGlobalOnLayoutListener(this);

                progressView1.getLocationOnScreen(position1);
                progressView2.getLocationOnScreen(position2);
                progressView3.getLocationOnScreen(position3);
                progressView4.getLocationOnScreen(position4);


                int titleBarHeight = getStatusBarHeight();
                int height = progressView1.getHeight();
                int width = progressView1.getWidth();

                //算所有的划线的点的坐标
                float unit= height/(float)100;
                LogUtils.showErrLog("unit" + unit);

                float progressTop1 = (unit*(100-progress1));
                LogUtils.showErrLog("position1[0]  " + position1[0]+ "  progressTop1  " + unit + "unit*(100-progress1)  " + unit*(100-progress1) + "  width   " + width + "  titleBarHeight  " + titleBarHeight);
                centerX1 = position1[0] + width/2;
                centerY1 = position1[1]+ progressTop1 - titleBarHeight;

                LogUtils.showErrLog("centerX1  " +  centerX1 + "centerY1  " + centerY1 + "  titleBarHeight  " +titleBarHeight);


                float progressTop2 = (unit*(100-progress2));
                centerX2 = position2[0] + width/2;
                centerY2 = position2[1]+ progressTop2 -titleBarHeight ;


                float progressTop3 = (unit*(100-progress3));
                centerX3 = position3[0] + width/2;
                centerY3 = position3[1]+ progressTop3 - titleBarHeight ;


                float progressTop4 = (unit*(100-progress4));
                centerX4 = position4[0] + width/2;
                centerY4 = position4[1]+ progressTop4 - titleBarHeight; // - titleBarHeight

                //画点画线
                LogUtils.showErrLog("测量完成.");

                point1 = new Point((int)centerX1,(int)centerY1);
                point2 = new Point((int)centerX2,(int)centerY2);
                point3 = new Point((int)centerX3,(int)centerY3);
                point4 = new Point((int)centerX4,(int)centerY4);

                myHandler.sendEmptyMessageDelayed(1000,900);
            }
          });

    }
    Handler myHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1000:
                    ValueAnimator valueAnimator1 = ValueAnimator.ofObject(new PointEvaluator(), point1, point2);
                    valueAnimator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            tempPoint1 = (Point) animation.getAnimatedValue();
                            invalidate();
                        }

                    });
                    valueAnimator1.setDuration(300);


                    ValueAnimator valueAnimator2 = ValueAnimator.ofObject(new PointEvaluator(), point2, point3);
                    valueAnimator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            tempPoint2 = (Point) animation.getAnimatedValue();
                            invalidate();
                        }

                    });
                    valueAnimator2.setDuration(300);


                    ValueAnimator valueAnimator3 = ValueAnimator.ofObject(new PointEvaluator(), point3, point4);
                    valueAnimator3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            tempPoint3 = (Point) animation.getAnimatedValue();
                            invalidate();
                        }

                    });
                    valueAnimator3.setDuration(300);


                    AnimatorSet seter = new AnimatorSet();
                    seter.play(valueAnimator1).before(valueAnimator2);
                    seter.play(valueAnimator2).before(valueAnimator3);
                    seter.setDuration(1000);
                    seter.start();
                    break;
            }
        }
    };

    // 获取状态栏高度  任何时候都能获取到，其他两种方式有局限性，同时有的时候获取不到。
    private int getStatusBarHeight() {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, statusBarHeight = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return statusBarHeight;
    }

    public class PointEvaluator implements TypeEvaluator {
        @Override
        public Object evaluate(float fraction, Object startValue, Object endValue) {
            Point startPoint = (Point) startValue;
            Point endPoint = (Point) endValue;
            float x = startPoint.getX() + fraction * (endPoint.getX() - startPoint.getX());
            float y = startPoint.getY() + fraction * (endPoint.getY() - startPoint.getY());
            Point point = new Point(x, y);
            return point;
        }

    }
}
