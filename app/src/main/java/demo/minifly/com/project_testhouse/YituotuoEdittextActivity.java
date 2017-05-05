package demo.minifly.com.project_testhouse;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import demo.minifly.com.R;


public class YituotuoEdittextActivity extends AppCompatActivity {

    private RelativeLayout yituotuo_rel_id;
    private LinearLayout ll_center_bg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yituotuo_edittext);
        initView();

    }

    private void initView() {
        yituotuo_rel_id = (RelativeLayout) findViewById(R.id.yituotuo_rel_id);
        ll_center_bg = (LinearLayout) findViewById(R.id.ll_center_bg);

        yituotuo_rel_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ll_center_bg.setVisibility(View.GONE);
            }
        });
    }
}
