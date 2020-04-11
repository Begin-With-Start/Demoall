package demo.minifly.com.fuction_demo.banner;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.leoao.sdk.common.utils.DisplayUtil;

import java.util.Timer;
import java.util.TimerTask;

import demo.minifly.com.R;

public class TextSwitchView extends TextSwitcher implements ViewSwitcher.ViewFactory {

    private int index = -1;
    private Context context;
    private String[] resources = {"窗前明月光", "疑是地上霜", "举头望明月", "低头思故乡"};
    private Timer timer;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    index = next();
                    updateText();
                    break;
            }
            super.handleMessage(msg);
        }
    };

    //自定义View的构造方法
    public TextSwitchView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public TextSwitchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    private void init() {
        if (timer == null) {
            timer = new Timer();
        }
        //实现ViewSwitcher.ViewFactory接口方法，创建出TextView并启动动画

        setFactory(this);
        setInAnimation(AnimationUtils.loadAnimation(context, R.anim.in_animation));
        setOutAnimation(AnimationUtils.loadAnimation(context, R.anim.out_animation));
    }

    public void setResources(String[] res) {
        resources = res;
    }

    //这个是自定义View的启动点，从外面传进来的间隔时间，并以此来开启这个定时任务器
    public void setTextStillTime(long time) {
        if (timer == null) {
            timer = new Timer();
        } else {
            timer.scheduleAtFixedRate(new MyTask(), 1, time);
        }
    }

    //启动任务，每间隔time时间发送一个消息给handler更新文字
    private class MyTask extends TimerTask {
        @Override
        public void run() {
            mHandler.sendEmptyMessage(1);
        }
    }

    private int next() {
        int flag = index + 1;
        if (flag > resources.length - 1) {
            flag = flag - resources.length;
        }
        return flag;
    }

    private void updateText() {
        setText(resources[index]);
    }

    @Override
    public View makeView() {
        TextView tv = new TextView(context);
        tv.setTextColor(Color.parseColor("#FF0000"));
        tv.setTextSize(22);
//        return  getLayout("");
        return  tv;
    }

    LinearLayout linearLayout;

    public View getLayout(String url) {
        linearLayout = new LinearLayout(context);
        linearLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        linearLayout.setBackground(context.getResources().getDrawable(R.drawable.ysf_progress_bar_white));
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setGravity(Gravity.CENTER_VERTICAL);
//            linearLayout.setVerticalGravity();

        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        linearLayout.addView(imageView);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) imageView.getLayoutParams();
        layoutParams.leftMargin = DisplayUtil.dip2px(3);
        layoutParams.height = DisplayUtil.dip2px(15);
        layoutParams.width = DisplayUtil.dip2px(15);
        imageView.setLayoutParams(layoutParams);
        imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.ali_vsdk_lock_success));


        TextView textView = new TextView(context);
        textView.setText("刚刚购买了该套餐");
        textView.setTextColor(context.getResources().getColor(R.color.common_color_ff4200));
        textView.setTextSize(DisplayUtil.dip2px(10));
        linearLayout.addView(textView);
        LinearLayout.LayoutParams textLayoutParam = (LinearLayout.LayoutParams) imageView.getLayoutParams();
        layoutParams.leftMargin = DisplayUtil.dip2px(8);
        layoutParams.rightMargin = DisplayUtil.dip2px(8);
        layoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT;
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        textView.setLayoutParams(textLayoutParam);

//        ImageLoadFactory.getLoad().loadRoundImage(imageView, url, DisplayUtil.dip2px(8), R.mipmap.default11);
        return linearLayout;
    }
}
