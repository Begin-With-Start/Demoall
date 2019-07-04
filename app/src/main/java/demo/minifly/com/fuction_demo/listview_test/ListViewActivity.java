package demo.minifly.com.fuction_demo.listview_test;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.leoao.sdk.common.utils.DisplayUtil;
import com.leoao.sdk.common.utils.LogUtils;

import java.util.LinkedList;
import java.util.List;

import demo.minifly.com.R;


/**
 * 作者：minifly on 2016/11/24 11:43
 */
public class ListViewActivity extends Activity {

    private MyListView myListView;
    private int mTouchSlop = 0;
    private LinearLayout topLin;
    private LinearLayout headerView;
    private int count;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main_listview);
        findView();
        mTouchSlop = DisplayUtil.dip2px(45);
//        mTouchSlop  = ViewConfiguration.get(this).getScaledTouchSlop();//获取系统默认的最小的判定用户滑动的距离
    }

    public void findView() {
        myListView = findViewById(R.id.minifly_listview_id);
        topLin = findViewById(R.id.minifly_listview_top_lin);

        myListView.setDividerHeight(0);

        headerView = new HeaderViewLin(this);

        linearLayout = new LinearLayout(getApplicationContext());
        linearLayout.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT,(int)getResources().getDimension(android.support.v7.appcompat.R.dimen.abc_action_bar_default_height_material)));

        linearLayout.addView(headerView);

        myListView.addHeaderView(linearLayout);

        linearLayout.setClickable(true);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.e("" , "");
            }
        });

        textClick();

        List<ListViewBean> list = new LinkedList<>();
        for (int i = 0; i < 40; i++) {
            ListViewBean listViewBean = new ListViewBean();
            listViewBean.setName("张三");
            listViewBean.setAge("20");
            listViewBean.setContent("content");
            list.add(listViewBean);
        }
        ListViewAdapter adapter = new ListViewAdapter(list, this);
        myListView.setAdapter(adapter);


        myListView.setOnTouchListener((v, event) -> {

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    mFirstY = event.getY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    mCurrY = event.getY();

                    if (Math.abs(mCurrY - mFirstY) > mTouchSlop) {
                        drection = 0;//down
                    } else {
                        drection = 1;//up
                    }

                    if (drection == 1) {//向下滑动
                        if (mShow) {
                            show(1);
                            mShow = !mShow;
                        }
                    } else if (drection == 0) {//向上滑动
                        if (!mShow) {
                            show(0);
                            mShow = !mShow;
                        }
                    }


                    break;
                case MotionEvent.ACTION_UP:

                    break;
            }

            return false;
        });
    }

    private void textClick() {
        headerView.setClickable(true);
        topLin.setClickable(true);
        TextView textView = ((HeaderViewLin) headerView).getTextView();
        textView.setText("我是一个header : " + count);
        headerView.setOnClickListener(v -> {
            count++;
            textView.setText("我是一个header : " + count);
        });

        topLin.setOnClickListener(v -> LogUtils.e("ListViewActivity" , "" + count));
    }

    private float mFirstY, mCurrY;
    private int drection;
    private boolean mShow = false;

    public void show(int drection) {
        if (drection == 1) {
            topLin.setVisibility(View.VISIBLE);
            linearLayout.removeAllViews();
            topLin.removeAllViews();
            topLin.addView(headerView);
            topLin.setBackgroundColor(getApplicationContext().getResources().getColor(R.color.white_10));
            textClick();
        } else {
            topLin.setVisibility(View.GONE);
            linearLayout.removeAllViews();
            topLin.removeAllViews();
            linearLayout.addView(headerView);
        }
    }


//    public void showAni(int drection){
//        if(mAnimator!=null && mAnimator.isRunning()){
//            mAnimator.cancel();
//        }
//        if(drection==1){
//            mAnimator = ObjectAnimator.ofFloat(barRel,"Y",barRel.getTranslationY(),0);
//        }else{
//            mAnimator = ObjectAnimator.ofFloat(barRel,"Y",barRel.getTranslationY(),-barRel.getHeight());
//        }
//        mAnimator.start();
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
