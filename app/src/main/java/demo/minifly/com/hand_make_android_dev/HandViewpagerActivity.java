package demo.minifly.com.hand_make_android_dev;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.common.business.utils.ContextUtils;

import demo.minifly.com.R;
import q.rorbin.badgeview.DisplayUtil;

public class HandViewpagerActivity extends AppCompatActivity {

    private ViewPager mHandViewpagerVp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hand_viewpager);
        initView();
    }

    private void initView() {
        mHandViewpagerVp = (ViewPager) findViewById(R.id.hand_viewpager_vp);


        //xml中设置 clipchild false ， 设置左右边距60dp留出位置
        mHandViewpagerVp.setOffscreenPageLimit(5); //设置显示3个缓存
        mHandViewpagerVp.setPageMargin(DisplayUtil.dp2px(this,30));

        int [] imgArray = new int[6];
        imgArray[0] = R.drawable.test_cup;
        imgArray[1] = R.drawable.test_cup;
        imgArray[2] = R.drawable.test_cup;
        imgArray[3] = R.drawable.test_cup;
        imgArray[4] = R.drawable.test_cup;
        imgArray[5] = R.drawable.test_cup;


        mHandViewpagerVp.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return imgArray.length;
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return view == object;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                container.removeView((View) object);
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {

                //显示构建
                ImageView imageView = new ImageView(HandViewpagerActivity.this);
                imageView.setImageResource(imgArray[position]);
//                imageView.setBackgroundColor(Color.GRAY);
//                imageView.setBackground(HandViewpagerActivity.this.getResources().getDrawable(R.drawable.view_pager_bg));
                container.addView(imageView);
                return imageView;
            }
        });

        mHandViewpagerVp.setPageTransformer(false,new ViewpagerMixTransform());


    }
}
