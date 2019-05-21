package demo.minifly.com.fuction_demo.canvas_test.canvas_new;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.List;

import demo.minifly.com.fuction_demo.utils.DensityUtils;

/**
 * author ：minifly
 * date: 2017/7/3
 * time: 18:53
 * desc:
 */
public class CanvasPieView extends View {

    private Context mContext;
    private Paint piePaint;
    private int[] colors;
    private List<PipPoint> pipPointList;
    private int mWidth;
    private int mHeight;


    public CanvasPieView(Context context) {
        super(context);
        mContext =  context;
        init();
    }

    /**
     * 关于构造函数的问题， 一般只用继承一个参数的和两个参数的构造函数即可，三个参数的构造函数用到的比较少，
     * 一个参数是在代码中进行new view操作的时候调用，两个参数是在代码中进行inflate xml的时候调用。
     * @param context
     * @param attrs
     */
    public CanvasPieView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext =  context;
        init();
    }

    public void init(){
        piePaint = new Paint();
        piePaint.setColor(Color.BLACK);
        piePaint.setStrokeWidth(DensityUtils.dip2px(mContext,4));
        piePaint.setStyle(Paint.Style.FILL);
        piePaint.setAntiAlias(true);//消除锯齿效果
        colors = new int[]{0xFFb2ebf2,0xFF80deea,0xFF4dd0e1,0xFF26c6da,0xFF00bcd4,0xFF00acc1,0xFF0097a7,0xFF00838f,0xFF006064,0xFF84ffff,0xFF18ffff,0xFF0035ff};

    }

    /**
     * view的大小不仅仅是获取一次就可以的，控件的大小还受到父控件的控制，当父控件改动的时候，应该顺应改变。
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //获取到当前的view的宽高。
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int r = (int) (Math.min(mWidth*0.8,mHeight*0.8)/2);
        float startAngle = 0f;
        canvas.translate(mWidth/2,mHeight/2);

        RectF rect = new RectF(-r,-r,r,r);

        for(int i = 0 ; i<pipPointList.size() ; i++){
            piePaint.setColor(pipPointList.get(i).getColor());
            canvas.drawArc(rect,startAngle,pipPointList.get(i).getAngle(),true,piePaint);
            startAngle += pipPointList.get(i).getAngle();
        }
    }

    public void setData(List<PipPoint> pipPoints){
        /** 处理数据  */
        int sumCount = 0;
        for(PipPoint pipPoint : pipPoints){
            sumCount += pipPoint.getValue();//获取总的数值
        }
        for(int i = 0 ; i<pipPoints.size() ; i++){
            pipPoints.get(i).setPercent(pipPoints.get(i).getValue()/sumCount);
            pipPoints.get(i).setAngle(360*pipPoints.get(i).getValue()/sumCount);

            if(i<=colors.length){
                pipPoints.get(i).setColor(colors[i]);
            }else{
                pipPoints.get(i).setColor(colors[i/colors.length]);
            }
        }
        /** 处理数据 ending  */
        pipPointList = pipPoints;
        invalidate();//重绘
    }

    static class PipPoint{
        private float value; //数值
        private float percent; //百分比

        private String name; //名称
        private int color = 0; //颜色值

        private float angle; //角度值

        public PipPoint(float value, String name) {
            this.value = value;
            this.name = name;
        }

        public float getValue() {
            return value;
        }

        public void setValue(float value) {
            this.value = value;
        }

        public float getPercent() {
            return percent;
        }

        public void setPercent(float percent) {
            this.percent = percent;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getColor() {
            return color;
        }

        public void setColor(int color) {
            this.color = color;
        }

        public float getAngle() {
            return angle;
        }

        public void setAngle(float angle) {
            this.angle = angle;
        }
    }


}
