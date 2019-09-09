package demo.minifly.com.fuction_demo.project_arrow_test;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import demo.minifly.com.R;
import demo.minifly.com.fuction_demo.utils.DensityUtils;

/**
 * author ：minifly
 * date: 2017/8/9
 * time: 14:47
 * desc:
 */
public class ArrowLinearlayout extends LinearLayout {

    public int [] point1,point2;
    public Paint linePaint;
    public Context mContext;
    private Paint arrowPaint;
    private Canvas myCanvas;

    public ArrowLinearlayout(Context context) {
        super(context);
        this.mContext = context;
        init();
    }

    public ArrowLinearlayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init();
    }

    public ArrowLinearlayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        init();
    }

    public void init(){

        linePaint = new Paint();
        linePaint.setColor(getResources().getColor(R.color.common_color_upload_border_f2000000));
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setAntiAlias(true);//去锯齿
        linePaint.setStrokeCap(Paint.Cap.BUTT);//圆形的笔触
        linePaint.setStrokeWidth(DensityUtils.dip2px(mContext,2));

        arrowPaint = new Paint();
        arrowPaint.setColor(getResources().getColor(R.color.common_color_upload_border_f2000000));
        arrowPaint.setStyle(Paint.Style.STROKE);
        arrowPaint.setAntiAlias(true);//去锯齿
        arrowPaint.setStrokeCap(Paint.Cap.BUTT);//圆形的笔触
        arrowPaint.setStrokeWidth(DensityUtils.dip2px(mContext,1));
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        this.myCanvas = canvas;

//        if(tempPoint3!=null&& point4 !=null  && tempPoint3.getX() == point4.getX()){//把第四个点画上
//            canvas.drawCircle(centerX4,centerY4,dotSize/2, dotPaint);
//            canvas.drawCircle(centerX3,centerY3,dotSize/2, dotPaint);
//            canvas.drawLine(centerX3,centerY3,tempPoint3.getX(),tempPoint3.getY(),linePaint);
//        }

//        if(point1 !=null && point2!=null ){
////            canvas.drawLine(point1[0],point1[1],point2[0],point2[1],linePaint);
//            drawAL(point1[0],point1[1],point2[0],point2[1]);
//        }


        for(Map<String , Integer> map : pointS){
            drawAL(map.get("startx"),map.get("starty"),map.get("endx"),map.get("endy"));
        }


    }

    public void setData(int [] point1,int [] point2){

        this.point1 = point1;
        this.point2 = point2;

        invalidate();//重绘

    }

    public void setData(View startView , View endView){
        judgePoint(startView,endView);
        invalidate();//重绘
    }

    List<Map<String , Integer>> pointS = new LinkedList<>();

    /**
     * 从view中拿到划线的点逻辑
     * @param startView
     * @param endView
     */
    public void judgePoint(View startView ,View endView){
        Map<String , Integer> map = new HashMap<>();

        int [] startLoc = new int[]{0,0};
        startView.getLocationOnScreen(startLoc);//获取到view在screen的位置
        int [] endLoc = new int[]{0,0};
        endView.getLocationOnScreen(endLoc); //获取view在screen的位置


        if(startLoc[1] == endLoc[1]){//平级的view
            if(startLoc[0] < endLoc[0]){//左边往右边的连线

                map.put("startx",startLoc[0] + startView.getWidth());
                map.put("starty",startLoc[1]);
                map.put("endx",endLoc[0]);
                map.put("endy",endLoc[1]);
                pointS.add(map);

            }else{ //右边向左边的连线

                map.put("startx",startLoc[0] );
                map.put("starty",startLoc[1]);
                map.put("endx",endLoc[0] + endView.getWidth());
                map.put("endy",endLoc[1] );
                pointS.add(map);

            }
        }else{//不是平级的view

            if(startLoc[0] < endLoc[0]){//上和右下的操作


                if(startLoc[1] < endLoc[1]){///从上到下连线
                    map.put("startx",startLoc[0] + startView.getWidth()/2);
                    map.put("starty",startLoc[1] + startView.getHeight()/2);
                    map.put("endx",endLoc[0] + endView.getWidth()/2);
                    map.put("endy",endLoc[1] - endView.getHeight()/2);
                    pointS.add(map);
                }else{//从下到上连线
                    map.put("startx",startLoc[0] + startView.getWidth()/2);
                    map.put("starty",startLoc[1] - startView.getHeight()/2);
                    map.put("endx",endLoc[0] + endView.getWidth()/2);
                    map.put("endy",endLoc[1] + endView.getHeight()/2);
                    pointS.add(map);
                }



            }else{//上和左下的操作

                if(startLoc[1] < endLoc[1]){///从上到下连线
                    map.put("startx",startLoc[0] + startView.getWidth()/2);
                    map.put("starty",startLoc[1] + startView.getHeight()/2);
                    map.put("endx",endLoc[0] + endView.getWidth()/2);
                    map.put("endy",endLoc[1] - endView.getHeight()/2);
                    pointS.add(map);
                }else{//从下到上连线
                    map.put("startx",startLoc[0] + startView.getWidth()/2);
                    map.put("starty",startLoc[1] - startView.getHeight()/2);
                    map.put("endx",endLoc[0] + endView.getWidth()/2);
                    map.put("endy",endLoc[1] + endView.getHeight()/2);
                    pointS.add(map);
                }

            }

        }
    }

    /**
     * 画箭头
     * @param sx
     * @param sy
     * @param ex
     * @param ey
     */
    public void drawAL(int sx, int sy, int ex, int ey)
    {
        double H = 8; // 箭头高度
        double L = 3.5; // 底边的一半
        int x3 = 0;
        int y3 = 0;
        int x4 = 0;
        int y4 = 0;
        double awrad = Math.atan(L / H); // 箭头角度
        double arraow_len = Math.sqrt(L * L + H * H); // 箭头的长度
        double[] arrXY_1 = rotateVec(ex - sx, ey - sy, awrad, true, arraow_len);
        double[] arrXY_2 = rotateVec(ex - sx, ey - sy, -awrad, true, arraow_len);
        double x_3 = ex - arrXY_1[0]; // (x3,y3)是第一端点
        double y_3 = ey - arrXY_1[1];
        double x_4 = ex - arrXY_2[0]; // (x4,y4)是第二端点
        double y_4 = ey - arrXY_2[1];
        Double X3 = new Double(x_3);
        x3 = X3.intValue();
        Double Y3 = new Double(y_3);
        y3 = Y3.intValue();
        Double X4 = new Double(x_4);
        x4 = X4.intValue();
        Double Y4 = new Double(y_4);
        y4 = Y4.intValue();
        // 画线
        myCanvas.drawLine(sx, sy, ex, ey,arrowPaint);
        Path triangle = new Path();
        triangle.moveTo(ex, ey);
        triangle.lineTo(x3, y3);
        triangle.lineTo(x4, y4);
        triangle.close();
        myCanvas.drawPath(triangle,arrowPaint);

    }

    // 计算
    public double[] rotateVec(int px, int py, double ang, boolean isChLen, double newLen)
    {
        double mathstr[] = new double[2];
        // 矢量旋转函数，参数含义分别是x分量、y分量、旋转角、是否改变长度、新长度
        double vx = px * Math.cos(ang) - py * Math.sin(ang);
        double vy = px * Math.sin(ang) + py * Math.cos(ang);
        if (isChLen) {
            double d = Math.sqrt(vx * vx + vy * vy);
            vx = vx / d * newLen;
            vy = vy / d * newLen;
            mathstr[0] = vx;
            mathstr[1] = vy;
        }
        return mathstr;
    }

}
