package demo.minifly.com.fuction_demo.canvas_test;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * 作者：minifly on 2016/12/5 09:25
 * 绘图板，每次的绘制应该要延迟100ms，来减小系统 的消耗
 * 模板surfaceview
 */
public class CanvasSurfaceViewDrawBoard extends SurfaceView implements SurfaceHolder.Callback,Runnable{

    private SurfaceHolder surfaceHolder;
    private boolean mIsDrawing = false;  //绘图是否结束
    private Canvas mCanvas;

    private Paint mPaint;
    private Path mPath;

    public CanvasSurfaceViewDrawBoard(Context context) {
        super(context);
        initView();
    }

    public CanvasSurfaceViewDrawBoard(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public CanvasSurfaceViewDrawBoard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }


    public void initView(){
        surfaceHolder = getHolder();
        mPaint = new Paint();
        mPath = new Path();

        surfaceHolder.addCallback(this);
        setFocusable(true);
        setFocusableInTouchMode(true);

        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLACK);
        //设置画笔宽度
        mPaint.setStrokeWidth(3);
        //消除锯齿
        mPaint.setAntiAlias(true);

        this.getKeepScreenOn();
    }
    private float lastX = 0,lasty = 0;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x =  event.getX();
        float y =  event.getY();
        if(lastX==0){
            lastX = x;
        }
        if(lasty==0){
            lasty = y;
        }
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mPath.moveTo(x,y);
//                Log.e("msg","哈按下 x " + x + " y " + y );
                break;
            case MotionEvent.ACTION_MOVE:
                mPath.quadTo(lastX,lasty,x,y);//贝塞尔曲线优化的划线 cubicTo 三阶贝塞尔曲线
//                Log.e("msg","移动 x " + x + " y " + y );
//                mPath.lineTo(x,y);
                break;
            case MotionEvent.ACTION_UP:

                break;
        }
        lastX = x;
        lasty = y;
        return true;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mIsDrawing = true;
        new Thread(this).start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mIsDrawing = false;
    }

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        while (mIsDrawing){
            draw();
        }
        long endTime = System.currentTimeMillis();
        if(endTime-startTime<100){
            try{
                Thread.sleep(100-(endTime-startTime));
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private void draw(){
        try{
            mCanvas = surfaceHolder.lockCanvas(); //获取当前的绘图对象
            mCanvas.drawColor(Color.WHITE);
            mCanvas.drawPath(mPath,mPaint);
        }catch (Exception  e){

        }finally {
            if(mCanvas!=null)
                surfaceHolder.unlockCanvasAndPost(mCanvas);//异常之后也应该每次都将绘图的内容进行提交
        }
    }
}
