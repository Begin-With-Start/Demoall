package demo.minifly.com.project_all_demo.bubble;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import demo.minifly.com.R;


/**
 * 气泡PopupWindow
 * Created by ShineF on 2017/6/22 0022.
 */

public class BubblePopActivity extends AppCompatActivity implements View.OnClickListener {

    Button mBtnTop, mBtnRight, mBtnLeft, mBtnBottom, mBtnCenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bubble_pop);
        init();
    }

    public void init() {
        mBtnTop = (Button) findViewById(R.id.btn_top);
        mBtnRight = (Button) findViewById(R.id.btn_right);
        mBtnLeft = (Button) findViewById(R.id.btn_left);
        mBtnBottom = (Button) findViewById(R.id.btn_bottom);
        mBtnCenter = (Button) findViewById(R.id.btn_center);
        initData();
    }

    public void initData() {
        mBtnTop.setOnClickListener(this);
        mBtnRight.setOnClickListener(this);
        mBtnLeft.setOnClickListener(this);
        mBtnBottom.setOnClickListener(this);
        mBtnCenter.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btn_top) {
            BubblePopUtils.showBubbleUp(mBtnTop, this, "顶部的提示");
        } else if (i == R.id.btn_right) {
            BubblePopUtils.showBubbleRight(mBtnRight, this, "右边的提示");
        } else if (i == R.id.btn_left) {
            BubblePopUtils.showBubbleLeft(mBtnLeft, this, "左边的提示");
        } else if (i == R.id.btn_bottom) {
            BubblePopUtils.showBubbleBottom(mBtnBottom, this, "底部的提示");
        } else if (i == R.id.btn_center) {
            CustomToast.makeText(this, "这是一条会自动消失的提示", Toast.LENGTH_SHORT).setGravity(Gravity.TOP, 0, 220).show();
        }
    }
}
