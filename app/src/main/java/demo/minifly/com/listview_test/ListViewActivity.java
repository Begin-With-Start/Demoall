package demo.minifly.com.listview_test;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

import java.util.LinkedList;
import java.util.List;

import demo.minifly.com.R;


/**
 * 作者：minifly on 2016/11/24 11:43
 */
public class ListViewActivity extends Activity {

    private MyListView myListView;
    private Toolbar barRel;
    private int mTouchSlop = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main_listview);
        findView();

        mTouchSlop  = ViewConfiguration.get(this).getScaledTouchSlop();//获取系统默认的最小的判定用户滑动的距离
    }

    public void findView(){
        myListView = (MyListView)findViewById(R.id.minifly_listview_id);
        barRel = (Toolbar)findViewById(R.id.list_title_bar_rel);

        myListView.setDividerHeight(0);
//        View headerView  = new View(this);
//        headerView.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT,(int)getResources().getDimension(android.support.v7.appcompat.R.dimen.abc_action_bar_default_height_material)));
//        myListView.addHeaderView(headerView);


        List<ListViewBean> list = new LinkedList<ListViewBean>();
        for(int i = 0 ; i<40;i++){
            ListViewBean listViewBean = new ListViewBean();
            listViewBean.setName("张三");
            listViewBean.setAge("20");
            listViewBean.setContent("content");
            list.add(listViewBean);
        }
        ListViewAdapter adapter = new ListViewAdapter(list,this);
        myListView.setAdapter(adapter);

//        VelocityTracker
        myListView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        mFirstY = event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        mCurrY = event.getY();

                        if((mCurrY-mFirstY)>mTouchSlop){
                            drection = 0;//down
                        }else{
                            drection = 1;//up
                        }

                        if(drection==1){//向下滑动
                            if(mShow){
                                showAni(0);
                                mShow = !mShow;
                            }
                        }else if(drection==0){//向上滑动
                            if(!mShow){
                                showAni(1);
                                mShow = !mShow;
                            }
                        }

                        break;
                    case MotionEvent.ACTION_UP:

                        break;
                }

                return false;
            }
        });
    }
    private float mFirstY,mCurrY ;
    private int drection;
    private boolean mShow = false;
    private ObjectAnimator mAnimator;


    public void showAni(int drection){
        if(mAnimator!=null && mAnimator.isRunning()){
            mAnimator.cancel();
        }
        if(drection==1){
            mAnimator = ObjectAnimator.ofFloat(barRel,"Y",barRel.getTranslationY(),0);
        }else{
            mAnimator = ObjectAnimator.ofFloat(barRel,"Y",barRel.getTranslationY(),-barRel.getHeight());
        }
        mAnimator.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
