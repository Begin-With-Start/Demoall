package demo.minifly.com.lottie;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.airbnb.lottie.LottieAnimationView;

import demo.minifly.com.R;

public class LottieShowActivity extends Activity implements View.OnClickListener {

    private LottieAnimationView mLottieMedal;
    private Button mStartPlay;
    private Button mReturnPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lottie_show);
        initView();
    }

    private void initView() {
        mLottieMedal = (LottieAnimationView) findViewById(R.id.lottie_medal);
        mStartPlay = (Button) findViewById(R.id.start_play);
        mStartPlay.setOnClickListener(this);
        mReturnPlay = (Button) findViewById(R.id.return_play);
        mReturnPlay.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_play:
//                ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f);
//                mLottieMedal.setAnimation(animator);
                mLottieMedal.setSpeed(0.7f);
                mLottieMedal.addAnimatorUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        float animatorF = (float) animation.getAnimatedValue();
                        if(animatorF >= 0.75f ){
                            mLottieMedal.cancelAnimation();
                            mLottieMedal.setProgress(0.75f);
                        }
                    }
                });
                mLottieMedal.playAnimation();
                break;
            case R.id.return_play:
//                mLottieMedal.set
                mLottieMedal.setProgress(1f);
                break;
        }
    }
}
