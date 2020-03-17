package demo.minifly.com.fuction_demo.banner;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.LinkedList;
import java.util.List;

import demo.minifly.com.R;
import demo.minifly.com.fuction_demo.utils.LogUtils;
import demo.minifly.com.hand_make_android_dev.ViewpagerMixTransform;
import q.rorbin.badgeview.DisplayUtil;

public class NewViewpagerActivity extends AppCompatActivity {

    private ViewPager mHandViewpagerVp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hand_viewpager_new);
        initView();
    }

    private void initView() {
        mHandViewpagerVp = (ViewPager) findViewById(R.id.vp_member);
        int [] memberLevelIcons = new int[]{R.drawable.test_cup,R.drawable.test_cup,R.drawable.test_cup};
        float mHeight = DisplayUtil.dp2px(this,106);
        List<View> ivMembers = new LinkedList<>();

        for (int i = 0; i < memberLevelIcons.length; i++) {
            ImageView ivMember = new ImageView(this);
            ViewGroup.LayoutParams lp = ivMember.getLayoutParams();
            if (lp == null) {
                lp = new ViewGroup.LayoutParams( ViewGroup.LayoutParams.MATCH_PARENT, (int) mHeight);
            } else {
                lp.width =  ViewGroup.LayoutParams.MATCH_PARENT;
                lp.height = (int) mHeight;
            }
            ivMember.setBackgroundColor(this.getResources().getColor(R.color.black));
            ivMember.setLayoutParams(lp);
            ivMember.setAdjustViewBounds(true);
            ivMember.setImageResource(memberLevelIcons[i]);
            ivMembers.add(ivMember);
        }

        mHandViewpagerVp.getLayoutParams().height = (int) mHeight;
        LoopPagerAdapter loopPagerAdapter = new LoopPagerAdapter(ivMembers);
        mHandViewpagerVp.setAdapter(loopPagerAdapter);
        mHandViewpagerVp.setPageMargin(20);//设置两边显示的距离

        mHandViewpagerVp.setCurrentItem(0);
        mHandViewpagerVp.setOffscreenPageLimit(2);
        //进行不断循环需要设置的
        //将父类的touch事件分发至viewPager，否则只能滑动中间的一个view对象ll_container.setOnTouchListener(new View.OnTouchListener() {@Overridepublic boolean onTouch(View v, MotionEvent event) {return vpMember.dispatchTouchEvent(event);}});}

    }

    public class LoopPagerAdapter extends PagerAdapter {

        private List<View> showViews;

        public LoopPagerAdapter(List<View> showViews) {
            this.showViews = showViews;
        }

        @Override
        public int getCount() {
            return showViews.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public Object instantiateItem(View container, int position) {
            int pos = position % showViews.size();
            View view = showViews.get(pos);
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeView(view);
            }
            ((ViewPager) container).addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container,position,object);
        }

        @Override
        public float getPageWidth(int position) {
            return (0.77333f*super.getPageWidth(position));
        }
    }
}
