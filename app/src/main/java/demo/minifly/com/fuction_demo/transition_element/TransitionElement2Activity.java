package demo.minifly.com.fuction_demo.transition_element;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.transition.Transition;
import android.widget.ImageView;
import android.widget.TextView;

import demo.minifly.com.R;

public class TransitionElement2Activity extends AppCompatActivity {

    private ImageView mTransition2ImgId;
    private TextView mTransition2TxtId;

    static String VIEW_NAME_HEADER_IMAGE = "image";
    static String VIEW_NAME_HEADER_TITLE = "txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition_element2);
        initView();

        ViewCompat.setTransitionName(mTransition2ImgId, VIEW_NAME_HEADER_IMAGE);
        ViewCompat.setTransitionName(mTransition2TxtId, VIEW_NAME_HEADER_TITLE);

        addTransitionListener();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAfterTransition();
        }
    }

    private void initView() {
        mTransition2ImgId = (ImageView) findViewById(R.id.transition_2_img_id);
        mTransition2TxtId = (TextView) findViewById(R.id.transition_2_txt_id);
    }


    private boolean addTransitionListener() {


            // There is an entering shared element transition so add a listener to it
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ){

                final Transition transition = getWindow().getSharedElementEnterTransition();
                if (transition != null) {
                    transition.addListener(new Transition.TransitionListener() {
                        @Override
                        public void onTransitionEnd(Transition transition) {
                            // As the transition has ended, we can now load the full-size image

                            // Make sure we remove ourselves as a listener
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ){
                                transition.removeListener(this);
                            }
                        }

                        @Override
                        public void onTransitionStart(Transition transition) {
                        }

                        @Override
                        public void onTransitionCancel(Transition transition) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ){
                                transition.removeListener(this);
                            }
                        }

                        @Override
                        public void onTransitionPause(Transition transition) {
                            // No-op
                        }

                        @Override
                        public void onTransitionResume(Transition transition) {
                            // No-op
                        }
                    });
                }

                return true;
            }
        return false;
    }
}
