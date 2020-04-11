package demo.minifly.com.fuction_demo.banner;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import demo.minifly.com.R;
import demo.minifly.com.fuction_demo.utils.LogUtils;

public class TextSwithMainActivity extends AppCompatActivity {
    private TextSwitcher textSwitcher1;
    private int curStr;
    String[] mRes = {"点击我翻转", "窗前明月光", "疑是地上霜", "举头望明月", "低头思故乡"};
    private Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch_main);

        mContext = this;
        /**
         * 自动更新的TextSwitchView
         */
        TextSwitchView textSwitchView = (TextSwitchView) findViewById(R.id.switcher2);
        String[] autoRes = {"窗前明月光", "疑是地上霜", "举头望明月", "低头思故乡"};
        textSwitchView.setResources(autoRes);
        textSwitchView.setTextStillTime(3000);


        /**
         * 点击更新的TextSwitchView
         */
        textSwitcher1 = (TextSwitcher) findViewById(R.id.switcher1);
        textSwitcher1.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView tv = new TextView(TextSwithMainActivity.this);
                tv.setTextColor(Color.parseColor("#30c2b1"));
                tv.setText("点击我翻转");
                tv.setTextSize(22);
                return tv;
            }
        });
        next(null);

        LinearLayout linearLayout = findViewById(R.id.animator_linearlayout);
        ImageView imageView = findViewById(R.id.animator_img);
        TextView textView = findViewById(R.id.animator_txt);

        LinearLayout linearLayout2 = findViewById(R.id.animator_linearlayout2);
        ImageView imageView2 = findViewById(R.id.animator_img2);
        TextView textView2 = findViewById(R.id.animator_txt2);

        ObjectAnimator inAnimator = (ObjectAnimator) AnimatorInflater.loadAnimator(getApplicationContext(), R.animator.user_in_translationy);
        ObjectAnimator outAnimator = (ObjectAnimator) AnimatorInflater.loadAnimator(getApplicationContext(), R.animator.user_out_translationy);


        inAnimator.setTarget(linearLayout);
        outAnimator.setTarget(linearLayout);

        animatorSet1 = new AnimatorSet();
        animatorSet1.play(inAnimator).before(outAnimator);
//        animatorSet1.setInterpolator(new DecelerateInterpolator());
        animatorSet1.setDuration(1000);

        animatorSet1.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                imageView.setBackground(getImage());
                LogUtils.showErrLog("onAnimationStart ");
            }

            @Override
            public void onAnimationEnd(Animator animation) {

                LogUtils.showErrLog("onAnimationEnd ");
                animatorSet1.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                LogUtils.showErrLog("onAnimationCancel ");

            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                LogUtils.showErrLog("onAnimationRepeat ");
            }
        });
        animatorSet1.start();

        ObjectAnimator inAnimator2 = (ObjectAnimator) AnimatorInflater.loadAnimator(getApplicationContext(), R.animator.user_in_translationy);
        ObjectAnimator outAnimator2 = (ObjectAnimator) AnimatorInflater.loadAnimator(getApplicationContext(), R.animator.user_out_translationy);

        inAnimator2.setTarget(linearLayout2);
        outAnimator2.setTarget(linearLayout2);


        animatorSet2 = new AnimatorSet();
//        animatorSet2.setInterpolator(new DecelerateInterpolator());
        animatorSet2.setDuration(1000);
        animatorSet2.play(inAnimator2).before(outAnimator2);
        animatorSet2.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                imageView2.setBackground(getImage());
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                animatorSet2.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        imageView2.postDelayed(new Runnable() {
            @Override
            public void run() {
                LogUtils.showLog("开始延迟启动 ");
                animatorSet2.start();
            }
        }, 1000);
//        animatorSet2.setStartDelay(1000);
//        animatorSet2.start();

    }

    private AnimatorSet animatorSet2;
    private AnimatorSet animatorSet1;

    int index = 0;
    int[] images = new int[]{R.mipmap.circle_personal_bg_female,
            R.mipmap.circle_icon_addpic_focused,
            R.mipmap.circle_personal_bg_female,
            R.mipmap.electronic_history};

    private Drawable getImage() {
        index++;
        if (index  == images.length) {
            index = 0;
        }
        LogUtils.showErrLog("获取到的图片：   "+ " index  " + index + "   images :  " + images[index]);
        return mContext.getResources().getDrawable(images[index]);
    }

    public void next(View source) {
        textSwitcher1.setText(mRes[curStr++ % mRes.length]);
    }

    public void cancleAnimatoreSet(AnimatorSet animatorSet) {
        if (animatorSet != null) {
            animatorSet.cancel();
            animatorSet.removeAllListeners();
        }
    }

    @Override
    protected void onDestroy() {
        LogUtils.showLog("尝试释放动画内存");
        cancleAnimatoreSet(animatorSet1);
        cancleAnimatoreSet(animatorSet2);
        super.onDestroy();
    }
}
