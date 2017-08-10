package demo.minifly.com.project_arrow_test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import java.util.LinkedList;
import java.util.List;

import demo.minifly.com.R;

public class ProjectArrowTestActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mArrowImg1;
    private ImageView mArrowImg2;
    private ImageView mArrowImg3;
    private ImageView mArrowImg4;
    private ArrowLinearlayout mArrowArrowlinearlayout;

    List<View> tempList = new LinkedList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_arrow_test);
        initView();
    }

    private void initView() {
        mArrowImg1 = (ImageView) findViewById(R.id.arrow_img_1);
        mArrowImg2 = (ImageView) findViewById(R.id.arrow_img_2);
        mArrowImg3 = (ImageView) findViewById(R.id.arrow_img_3);
        mArrowImg4 = (ImageView) findViewById(R.id.arrow_img_4);
        mArrowArrowlinearlayout = (ArrowLinearlayout) findViewById(R.id.arrow_arrowlinearlayout);

        mArrowImg1.setOnClickListener(this);
        mArrowImg2.setOnClickListener(this);
        mArrowImg3.setOnClickListener(this);
        mArrowImg4.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.arrow_img_1:
                tempList.add(mArrowImg1);
                judgeViewLine();
                break;
            case R.id.arrow_img_2:

                break;
            case R.id.arrow_img_3:
                tempList.add(mArrowImg3);
                judgeViewLine();
                break;
            case R.id.arrow_img_4:

                break;
        }
    }

    public void judgeViewLine(){
        if(tempList==null){
            //null返回
            return ;
        }
        if(tempList.size()==1){ //才第一个view

        }else if(tempList.size()==2){ //第二个view了
            View view1 = tempList.get(0);
            View view2 = tempList.get(1);

            int [] loc1 = new int[]{0,0};
            view1.getLocationOnScreen(loc1);//获取到view在screen的位置
            int [] loc2 = new int[]{0,0};
            view2.getLocationOnScreen(loc2); //获取view在screen的位置

            //算出一下view两个的中间点的位置
            loc1[0] = loc1[0] + view1.getWidth();

            mArrowArrowlinearlayout.setData(loc1,loc2);

        }
    }

}
