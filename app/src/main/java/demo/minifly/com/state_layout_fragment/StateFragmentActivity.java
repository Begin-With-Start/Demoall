package demo.minifly.com.state_layout_fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import demo.minifly.com.R;

public class StateFragmentActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout mTitleBarId;
    private RelativeLayout mContentRelId;
    private StateLayout mContentStateId;
    private Button mBtnId1;
    private Button mBtnId2;
    private Button mBtnId3;
    private Button mBtnId4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state_fragment);
        initView();
    }

    private void initView() {
        mTitleBarId = (LinearLayout) findViewById(R.id.title_bar_id);
        mContentRelId = (RelativeLayout) findViewById(R.id.content_rel_id);
        mContentStateId = (StateLayout) findViewById(R.id.content_state_id);
        mBtnId1 = (Button) findViewById(R.id.btn_id1);
        mBtnId1.setOnClickListener(this);
        mBtnId2 = (Button) findViewById(R.id.btn_id2);
        mBtnId2.setOnClickListener(this);
        mBtnId3 = (Button) findViewById(R.id.btn_id3);
        mBtnId3.setOnClickListener(this);
        mBtnId4 = (Button) findViewById(R.id.btn_id4);
        mBtnId4.setOnClickListener(this);


        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction trans = fm.beginTransaction();
        ContentFragment cf = new ContentFragment();
        trans.add(R.id.content_rel_id, cf);
        trans.addToBackStack("mybackstack");
        trans.commitAllowingStateLoss();

    }

    //切换内容与状态的view
    public void switchContentView(boolean showContent) {
        if (showContent) {
            mContentRelId.setVisibility(View.VISIBLE);
            mContentStateId.setVisibility(View.GONE);
        } else {
            mContentRelId.setVisibility(View.GONE);
            mContentStateId.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_id1:
                mContentStateId.showErrorView();
                break;
            case R.id.btn_id2:

                mContentStateId.showEmptyView();
                break;
            case R.id.btn_id3:

                mContentStateId.showProgressView();
                break;
            case R.id.btn_id4:

                mContentStateId.showContentView();

                break;
        }
    }


    /**
     *         FragmentManager fm = getSupportFragmentManager();
               FragmentTransaction trans = fm.beginTransaction();
     //        trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
     //            trans.setCustomAnimations(R.anim.comm_slide_in_from_right, R.anim.comm_scale_out);
                trans.add(R.id.container, fg);
     */
}
