
package demo.minifly.com.desk;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.LinkedList;

import demo.minifly.com.ActivityAnimation.ActivityAnimation;
import demo.minifly.com.ActivityAnimation.PhotoFragmentActivity;
import demo.minifly.com.R;
import demo.minifly.com.RippleShow.MyRippleShowActivity;
import demo.minifly.com.android_hot_fix.AndroidHotfixActivity;
import demo.minifly.com.androidl_animation.AndroidLCircularRevealActivity;
import demo.minifly.com.androidl_animation.AndroidLTouchFeedbackActivity;
import demo.minifly.com.canvas_test.ZCanvasActivity;
import demo.minifly.com.canvas_test.ZCanvasClockActivity;
import demo.minifly.com.canvas_test.ZCanvasLinearGraddientActivity;
import demo.minifly.com.canvas_test.ZCanvasMyCustomizationActivity;
import demo.minifly.com.canvas_test.ZCanvasSurfaceDrawBoardActivity;
import demo.minifly.com.canvas_test.ZCanvasSurfaceSinActivity;
import demo.minifly.com.canvas_test.ZGuagualeActivity;
import demo.minifly.com.canvas_test.ZObliqueProgressActivity;
import demo.minifly.com.constraintlayout_test.ConstraintActivity;
import demo.minifly.com.image_compress_demo.ImageCompressActivity;
import demo.minifly.com.intent_test.Intent1Activity;
import demo.minifly.com.layout_animation.LayoutAnimationActivity;
import demo.minifly.com.listview_test.ListViewActivity;
import demo.minifly.com.object_animator_test.ObjectAnimatorBezierActivity;
import demo.minifly.com.object_animator_test.ObjectAnimatorOwnColorTest;
import demo.minifly.com.object_animator_test.ObjectAnimatorOwnTest;
import demo.minifly.com.object_animator_test.PropertyValuesHolderAnimatorTest;
import demo.minifly.com.object_animator_test.ValueAnimatorTextActivity;
import demo.minifly.com.object_animator_test.ViewWithAnimatorActivity;
import demo.minifly.com.project_progress_new.ProjectProgressActivity;
import demo.minifly.com.project_testhouse.HorizenProgressActivity;
import demo.minifly.com.project_testhouse.YituotuoEdittextActivity;
import demo.minifly.com.pull_moveview_test.PullMoveviewActivity;
import demo.minifly.com.recycleview_refreshtopbootom.RecycleviewActivity;
import demo.minifly.com.recycleview_test.Activity3;
import demo.minifly.com.rxjava_demo.RxjavaDemoActivity;
import demo.minifly.com.scrollview_test.ScrollActivity;
import demo.minifly.com.textView.TextViewActivity;
import demo.minifly.com.url_config.UrlConfigActivity;
import demo.minifly.com.user_defined_animator.OwnAnimatorActivity;
import demo.minifly.com.user_defined_animator.OwnAnimatorActivity2;

public class DeskActivity extends Activity {
    private RecyclerView deskRecycleView;
    private View view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        view = getLayoutInflater().inflate(R.layout.activity_desk, null);
        setContentView(view);
        findview();
        hideNavigationBar();//控制底部的navigationbar的显示和隐藏的效果。
    }


    public void findview(){
        deskRecycleView = (RecyclerView)findViewById(R.id.desk_recycleview_id);

        deskRecycleView.setLayoutManager(new LinearLayoutManager(this));

        LinkedList<MyBean> list = new LinkedList<>();
        MyBean myBean;

        myBean = new MyBean();
        myBean.setTitle("共享元素的界面跳转");
        myBean.setClassName(ActivityAnimation.class);
        list.add(myBean);

        myBean = new MyBean();
        myBean.setTitle("一个刷新的操作试试");
        myBean.setClassName(RecycleviewActivity.class);
        list.add(myBean);

        myBean = new MyBean();
        myBean.setTitle("刮刮乐");
        myBean.setClassName(ZGuagualeActivity.class);
        list.add(myBean);

        myBean = new MyBean();
        myBean.setTitle("LinearGradient颜色渲染");
        myBean.setClassName(ZCanvasLinearGraddientActivity.class);
        list.add(myBean);

        myBean = new MyBean();
        myBean.setTitle("绘图板，每次的绘制应该要延迟100ms，来减小系统 的消耗");
        myBean.setClassName(ZCanvasSurfaceDrawBoardActivity.class);
        list.add(myBean);

        myBean = new MyBean();
        myBean.setTitle("surfaceviewsin曲线");
        myBean.setClassName(ZCanvasSurfaceSinActivity.class);
        list.add(myBean);

        myBean = new MyBean();
        myBean.setTitle("一个canvas的时钟");
        myBean.setClassName(ZCanvasClockActivity.class);
        list.add(myBean);

        myBean = new MyBean();
        myBean.setTitle("圆形进度条(算算角度什么的)");
        myBean.setClassName(ZCanvasActivity.class);
        list.add(myBean);

//        myBean = new MyBean();
//        myBean.setTitle("图片marqueen效果");
//        myBean.setClassName(ZMarqueeImageviewActivity.class);
//        list.add(myBean);

        myBean = new MyBean();
        myBean.setTitle("viewgroup指定进入的动画");
        myBean.setClassName(LayoutAnimationActivity.class);
        list.add(myBean);

        myBean = new MyBean();
        myBean.setTitle("一个定制的listview效果");
        myBean.setClassName(ListViewActivity.class);
        list.add(myBean);

        myBean = new MyBean();
        myBean.setTitle("颜色的一个animator渐变效果");
        myBean.setClassName(ObjectAnimatorOwnColorTest.class);
        list.add(myBean);

        myBean = new MyBean();
        myBean.setTitle("属性动画例子");
        myBean.setClassName(ObjectAnimatorOwnTest.class);
        list.add(myBean);

        myBean = new MyBean();
        myBean.setTitle("PropertyValuesHolder一个对象的多种属性的同时变化");
        myBean.setClassName(PropertyValuesHolderAnimatorTest.class);
        list.add(myBean);

        myBean = new MyBean();
        myBean.setTitle("valueanimator钱袋宝的钱数额的变化动画仿");
        myBean.setClassName(ValueAnimatorTextActivity.class);
        list.add(myBean);

        myBean = new MyBean();
        myBean.setTitle("view 本身自带的一个animator的属性，直接进行了动画");
        myBean.setClassName(ViewWithAnimatorActivity.class);
        list.add(myBean);

        myBean = new MyBean();
        myBean.setTitle("view 的随手指进行滑动的几种方式");
        myBean.setClassName(PullMoveviewActivity.class);
        list.add(myBean);

        myBean = new MyBean();
        myBean.setTitle("recycleview的例子滑动删除呗");
        myBean.setClassName(Activity3.class);
        list.add(myBean);

        myBean = new MyBean();
        myBean.setTitle("波纹效果的有边界与无边界(可以放到任何的view上面)");
        myBean.setClassName(MyRippleShowActivity.class);
        list.add(myBean);

        myBean = new MyBean();
        myBean.setTitle("可以上下进行粘性滑动的scrollview");
        myBean.setClassName(ScrollActivity.class);
        list.add(myBean);

        myBean = new MyBean();
        myBean.setTitle("三维开门一样的效果");
        myBean.setClassName(OwnAnimatorActivity.class);
        list.add(myBean);

        myBean = new MyBean();
        myBean.setTitle("模拟电视的关机的动画");
        myBean.setClassName(OwnAnimatorActivity2.class);
        list.add(myBean);

        myBean = new MyBean();
        myBean.setTitle("工程定制的一个progress效果");
        myBean.setClassName(ZCanvasMyCustomizationActivity.class);
        list.add(myBean);

        myBean = new MyBean();
        myBean.setTitle("斜线进度条");
        myBean.setClassName(ZObliqueProgressActivity.class);
        list.add(myBean);


        myBean = new MyBean();
        myBean.setTitle("工程定制的一个shape的效果，组成progress效果");
        myBean.setClassName(HorizenProgressActivity.class);
        list.add(myBean);

        myBean = new MyBean();
        myBean.setTitle("按钮波纹与波纹色定制");
        myBean.setClassName(AndroidLTouchFeedbackActivity.class);
        list.add(myBean);

        myBean = new MyBean();
        myBean.setTitle("动画-图形揭示-anroidL新特性");
        myBean.setClassName(AndroidLCircularRevealActivity.class);
        list.add(myBean);

        myBean = new MyBean();
        myBean.setTitle("贝塞尔曲线的购物车添加动画");
        myBean.setClassName(ObjectAnimatorBezierActivity.class);
        list.add(myBean);

        myBean = new MyBean();
        myBean.setTitle("文字特效");
        myBean.setClassName(TextViewActivity.class);
        list.add(myBean);

        myBean = new MyBean();
        myBean.setTitle("热修复测试.");
        myBean.setClassName(AndroidHotfixActivity.class);
        list.add(myBean);

        myBean = new MyBean();
        myBean.setTitle("工程定制的标签添加与管理控件.");
        myBean.setClassName(YituotuoEdittextActivity.class);
        list.add(myBean);

        myBean = new MyBean();
        myBean.setTitle("共享元素相关的测试加入到的是fragment");
        myBean.setClassName(PhotoFragmentActivity.class);
        list.add(myBean);

        myBean = new MyBean();
        myBean.setTitle("intent的传递值，测试是否是会产生新的类.");
        myBean.setClassName(Intent1Activity.class);
        list.add(myBean);

        myBean = new MyBean();
        myBean.setTitle("Constraint布局的尝试");
        myBean.setClassName(ConstraintActivity.class);
        list.add(myBean);

        myBean = new MyBean();
        myBean.setTitle("工程要用的一个改动的progressbar的效果");
        myBean.setClassName(ProjectProgressActivity.class);
        list.add(myBean);
        //ImageCompressActivity

        myBean = new MyBean();
        myBean.setTitle("图片压缩相关的操作");
        myBean.setClassName(ImageCompressActivity.class);
        list.add(myBean);


        myBean = new MyBean();
        myBean.setTitle("rxjava基本实验");
        myBean.setClassName(RxjavaDemoActivity.class);
        list.add(myBean);

        myBean = new MyBean();
        myBean.setTitle("配置url");
        myBean.setClassName(UrlConfigActivity.class);
        list.add(myBean);


        MyAdapter myAdapter = new MyAdapter(this,list);
        deskRecycleView.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();

    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        super.onKeyUp(keyCode, event);
        if ((keyCode == KeyEvent.KEYCODE_VOLUME_UP || keyCode == KeyEvent.KEYCODE_VOLUME_DOWN)){
            this.hideNavigationBar();
        }
        return false;
    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if( hasFocus ) {
            hideNavigationBar();
        }
    }

    public void hideNavigationBar() {
        int uiFlags = //View.SYSTEM_UI_FLAG_LAYOUT_STABLE|
                //View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION|
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION; // hide nav bar

        if( android.os.Build.VERSION.SDK_INT >= 19 ){
            uiFlags |= 0x00001000;    //SYSTEM_UI_FLAG_IMMERSIVE_STICKY: hide navigation bars - compatibility: building API level is lower thatn 19, use magic number directly for higher API target level
        } else {
            uiFlags |= View.SYSTEM_UI_FLAG_LOW_PROFILE;
        }

        getWindow().getDecorView().setSystemUiVisibility(uiFlags);
    }

    class MyAdapter extends RecyclerView.Adapter{
        private Context context;
        private View view;
        private LinkedList<MyBean> list;

        public MyAdapter(Context context,LinkedList<MyBean> list){
            this.context = context;
            this.list = list;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            view = LayoutInflater.from(context).inflate(R.layout.desk_adapter_item, parent, false);
            final MyHolder viewHolder = new MyHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            final MyBean myBean = list.get(position);
            ((MyHolder)holder).myTextView.setText(myBean.getTitle());
            ((MyHolder)holder).myRel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.setClass(context,myBean.getClassName());
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            if(list==null){
                return 0 ;
            }else{
                return list.size();
            }
        }

        class MyHolder extends RecyclerView.ViewHolder{
            private TextView myTextView;
            private RelativeLayout myRel;

            public MyHolder(View itemView) {
                super(itemView);
                myRel = (RelativeLayout)view.findViewById(R.id.desk_adapter_rel);
                myTextView = (TextView)view.findViewById(R.id.desk_adapter_txt_id);
            }
        }
    }

    class MyBean<T> {
        private String title;
        private Class<T> className;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Class<T> getClassName() {
            return className;
        }

        public void setClassName(Class<T> className) {
            this.className = className;
        }
    }
}


