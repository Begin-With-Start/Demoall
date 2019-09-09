package demo.minifly.com.fuction_demo.ActivityAnimation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import demo.minifly.com.R;


public class PhotoFragmentActivity extends AppCompatActivity {

    private Bundle savedInstancestate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_fragment);
        savedInstancestate = savedInstanceState;
        initView();
    }

    private void initView() {

        // 替换Fragment
        if (savedInstancestate == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_id_container, new PhotoListFragment())
                    .commit();
        }

    }
}
