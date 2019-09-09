package demo.minifly.com.fuction_demo.bottomnavgationview_demo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import demo.minifly.com.R;
import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;


public class BottomNavigationDemoActivity extends AppCompatActivity {

    private BottomNavigationViewEx mBottomNavigationId;
    private TextView mBottomNavigationTextId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation_demo);
        initView();
    }

    private void initView() {
        mBottomNavigationId = (BottomNavigationViewEx) findViewById(R.id.bottom_navigation_id);
        mBottomNavigationTextId = (TextView) findViewById(R.id.bottom_navigation_text_id);

        mBottomNavigationId.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mBottomNavigationTextId.setText(item.getTitle().toString());
                return true;
            }
        });


//        mBottomNavigationId.enableAnimation(false);
        mBottomNavigationId.enableShiftingMode(false);
//        mBottomNavigationId.enableItemShiftingMode(false);

        addBadgeAt(2, 1);

        /**
         * 禁止所有动画效果

         bnve.enableAnimation(false);
         bnve.enableShiftingMode(false);
         bnve.enableItemShiftingMode(false);
         自定义图标和文本大小

         bnve.setIconSize(widthDp, heightDp);
         bnve.setTextSize(sp);
         和 ViewPager 绑定####

         // set adapter
         adapter = new VpAdapter(getSupportFragmentManager(), fragments);
         bind.vp.setAdapter(adapter);

         // binding with ViewPager
         bind.bnve.setupWithViewPager(bind.vp);


         添加带数字的小红点

         Gradle 中加入 badge 库的依赖

         compile 'q.rorbin:badgeview:1.1.0'
         和底部控件绑定

         // add badge
         addBadgeAt(2, 1);

         private Badge addBadgeAt(int position, int number) {
         // add badge
         return new QBadgeView(this)
         .setBadgeNumber(number)
         .setGravityOffset(12, 2, true)
         .bindTarget(bind.bnve.getBottomNavigationItemView(position))
         .setOnDragStateChangedListener(new Badge.OnDragStateChangedListener() {
        @Override
        public void onDragStateChanged(int dragState, Badge badge, View targetView) {
        if (Badge.OnDragStateChangedListener.STATE_SUCCEED == dragState)
        Toast.makeText(BadgeViewActivity.this, R.string.tips_badge_removed, Toast.LENGTH_SHORT).show();
        }
        });
         }

         */
    }

    private Badge addBadgeAt(int position, int number) {
        // add badge
        return new QBadgeView(this)
            .setBadgeNumber(number)
            .setGravityOffset(12, 2, true)
            .bindTarget(mBottomNavigationId.getBottomNavigationItemView(position))
            .setOnDragStateChangedListener(new Badge.OnDragStateChangedListener() {
                @Override
                public void onDragStateChanged(int dragState, Badge badge, View targetView) {
                    if (Badge.OnDragStateChangedListener.STATE_SUCCEED == dragState)
                        Toast.makeText(BottomNavigationDemoActivity.this, "小红点", Toast.LENGTH_SHORT).show();
                }
            });
    }
}
