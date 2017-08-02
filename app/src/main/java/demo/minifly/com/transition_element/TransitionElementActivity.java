package demo.minifly.com.transition_element;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import demo.minifly.com.R;

public class TransitionElementActivity extends AppCompatActivity implements View.OnClickListener {

    static String VIEW_NAME_HEADER_IMAGE = "image";
    static String VIEW_NAME_HEADER_TITLE = "txt";
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_transition_element);

        context = this;
        findViewById(R.id.transition_1_img_id).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.transition_1_img_id:
                // Now we can start the Activity, providing the activity options as a bundle
                ActivityOptionsCompat activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(
                        this,
                        new Pair<View, String>(findViewById(R.id.transition_1_img_id),
                                VIEW_NAME_HEADER_IMAGE),
                        new Pair<View, String>(findViewById(R.id.transition_1_txt_id),
                                VIEW_NAME_HEADER_TITLE)
                );
                Intent intent = new Intent();
                intent.setClass(context,TransitionElement2Activity.class);
                ActivityCompat.startActivity(context, intent, activityOptions.toBundle());
                break;
        }
    }
}
