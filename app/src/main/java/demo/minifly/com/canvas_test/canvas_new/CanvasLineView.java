package demo.minifly.com.canvas_test.canvas_new;

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
 * date: 2017/7/3
 * time: 14:43
 * desc:
 */
public class CanvasLineView extends View {
    private Context mContext;
    private Paint linePaint;

    public CanvasLineView(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public CanvasLineView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    public void init(){
        linePaint = new Paint();
        linePaint.setColor(Color.BLACK);
        linePaint.setStyle(Paint.Style.FILL);
        linePaint.setStrokeWidth(DensityUtils.dip2px(mContext,1));

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(getResources().getColor(R.color.canvas_own_line_color));
        canvas.drawLine(0,0,100,400,linePaint);

        canvas.drawLines(new float[]{50,50,200,200,200,200,400,400},linePaint);
    }
}










