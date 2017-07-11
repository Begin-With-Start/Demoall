package demo.minifly.com.canvas_test;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import demo.minifly.com.R;
import demo.minifly.com.utils.ScreenUtils;

/**
 * 作者：minifly on 2016/12/4 16:10
 * 在刮刮乐的开发中，建议关闭掉硬件加速的设置，因为很多的模式不支持硬件加速
 */
public class CanvasGuaguale  extends View{

    private Paint mPaint;
    private Path mPath;
    private Bitmap mBgBitmap;
    private Bitmap mFgBitmap;
    private Canvas mCanvas;
    private Context mContext;

    public CanvasGuaguale(Context context) {
        super(context);
        this.mContext = context;
        init();
    }

    public CanvasGuaguale(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init();
    }

    public void init(){
        mPaint = new Paint();
        mPaint.setAlpha(0); //透明度设置为0
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN)); //两个图像的混合模式
        mPaint.setStyle(Paint.Style.STROKE); //实线的画笔笔触
        mPaint.setStrokeCap(Paint.Cap.ROUND);  //让画笔的笔触更加的圆滑
        mPaint.setStrokeJoin(Paint.Join.ROUND);  //让画笔的笔触更加的圆滑
        mPaint.setStrokeWidth(50);

        mPath = new Path();
        mBgBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.guaguale);
        mFgBitmap = Bitmap.createBitmap(ScreenUtils.getScreenWidth(mContext),ScreenUtils.getScreenHeight(mContext), Bitmap.Config.ARGB_8888); //刮刮乐的蒙版
        mCanvas = new Canvas(mFgBitmap);

        mCanvas.drawColor(Color.GRAY);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mPath.reset();
                mPath.moveTo(event.getX(),event.getY());
                break;

            case MotionEvent.ACTION_MOVE:
                mPath.lineTo(event.getX(),event.getY());
                break;

        }
        mCanvas.drawPath(mPath,mPaint);   //画轨迹
        invalidate();  //重绘
        return true;//不给父级做处理的事件类型
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(mBgBitmap,0,0,null); //画背景
        canvas.drawBitmap(mFgBitmap,0,0,null); //画前景
    }
}
