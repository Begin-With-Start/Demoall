package demo.minifly.com.ActivityAnimation;

import android.app.Activity;
import android.os.Bundle;
import android.transition.ChangeImageTransform;
import android.transition.Explode;
import android.view.Window;

import demo.minifly.com.R;


public class ActivityAnimation2 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);


        getWindow().setSharedElementEnterTransition(new ChangeImageTransform(this,null));
        getWindow().setSharedElementExitTransition(new ChangeImageTransform(this,null));

        getWindow().setExitTransition(new Explode());
//        getWindow().setExitTransition(new Slide());
//        getWindow().setExitTransition(new Fade());
        setContentView(R.layout.activity_animation2);
    }
}
