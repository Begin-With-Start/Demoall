package demo.minifly.com.fuction_demo.canvas_test;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.view.Window;

import java.util.Timer;
import java.util.TimerTask;

import demo.minifly.com.R;
import demo.minifly.com.fuction_demo.utils.ScreenUtils;

/**
 * author ：minifly
 * date: 2016/12/22
 * time: 09:19
 * desc:
 */
public class MarqueeImageView extends View {
    // 背景图片
    Bitmap back;
    Context context;
    int nowX = 0;
    int backWidth;
    int vw;
    int vh;
    int speed;

    public MarqueeImageView(Context context) {
        super(context);
        this.context = context;
    }

    public MarqueeImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public MarqueeImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
    }

    // 启动
    public void Start(Window windows) {

        Bitmap backMap = ((BitmapDrawable)getResources().getDrawable(R.drawable.icon_bg)).getBitmap();

        int h = backMap.getHeight();
        int w = backMap.getWidth();

        // 获取设备高度和宽度
//        Rect frame = new Rect();
//        windows.getDecorView().getWindowVisibleDisplayFrame(frame);
        vh =  ScreenUtils.getScreenHeight(context);
        vw = ScreenUtils.getScreenWidth(context);
        // 设置滚动速度
        speed = 1;
        //裁剪一下
        back = Bitmap.createBitmap(backMap, 0, 0, backMap.getWidth(), vh);
        backWidth = back.getWidth();

        final Handler handler = new Handler() {

            public void handleMessage(Message msg) {
                if (msg.what == 0x123) {
                    // 该函数的作用是使整个窗口客户区无效。窗口的客户区无效意味着需要重绘
                    invalidate();
                }
            }
        };
        new Timer().schedule(new TimerTask() {

            @Override
            public void run() {
                handler.sendEmptyMessage(0x123);
            }
        }, 0, 80);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int w = backWidth - nowX;

        if (vw <= w) {
            // 图片剩余宽度大于屏幕宽度，从原图上截取屏幕窗口大小的一块区域
            Bitmap bitmap = Bitmap.createBitmap(back, nowX, 0, vw, vh);
            canvas.drawBitmap(bitmap, 0, 0, null);
        } else {
            Bitmap bitmap = Bitmap.createBitmap(back, nowX, 0, w, vh);
            canvas.drawBitmap(bitmap, 0, 0, null);
            Bitmap bitmap2 = Bitmap.createBitmap(back, 0, 0, vw - w, vh);
            canvas.drawBitmap(bitmap2, w, 0, null);
        }

        if (nowX + speed >= backWidth) {
            nowX = 0;
        } else {
            nowX += speed;
        }
    }

}
