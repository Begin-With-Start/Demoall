package demo.minifly.com.transition_element;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import demo.minifly.com.R;

public class FrameActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mTransitionFrameBtn;
    private Button mTransitionFrameFragmentBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame);
        initView();
    }

    private void initView() {
        mTransitionFrameBtn = (Button) findViewById(R.id.transition_frame_btn);
        mTransitionFrameFragmentBtn = (Button) findViewById(R.id.transition_frame_fragment_btn);

        mTransitionFrameBtn.setOnClickListener(this);
        mTransitionFrameFragmentBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.transition_frame_btn:
                Intent intent = new Intent();
                intent.setClass(this,TransitionElementActivity.class);
                startActivity(intent);
                break;
            case R.id.transition_frame_fragment_btn:
                Intent intent1 = new Intent();
                intent1.setClass(this,FrameFragmentAcitity.class);
                startActivity(intent1);
                break;
        }
    }
}
