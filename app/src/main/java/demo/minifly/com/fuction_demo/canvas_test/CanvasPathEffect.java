package demo.minifly.com.fuction_demo.canvas_test;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.util.AttributeSet;
import android.view.View;

/**
 * 作者：minifly on 2016/12/4 19:55
 * 绘图的paint 笔触的设定。
 */
public class CanvasPathEffect  extends View{
    public CanvasPathEffect(Context context) {
        super(context);
    }

    public CanvasPathEffect(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CanvasPathEffect(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CanvasPathEffect(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        PathEffect [] pathEffects = new PathEffect[4];
        pathEffects[0] = null;
        pathEffects[1]  = new CornerPathEffect(30);


        Path mPath = new Path();
        mPath.moveTo(0,0);
        for(int i=0;i<=30 ; i++){
            mPath.lineTo(i*35,(float)(Math.random()*100));
        }

        Paint mpaint = new Paint();
        mpaint.setStyle(Paint.Style.STROKE);
        mpaint.setPathEffect(new CornerPathEffect(30));
        canvas.drawPath(mPath,mpaint);

    }
}
