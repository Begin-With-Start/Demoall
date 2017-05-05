package demo.minifly.com.project_testhouse;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import demo.minifly.com.R;


public class HorizenProgressActivity extends AppCompatActivity {

    private ProgressBar taskBacklogProgressProgressBar;
    private TextView taskBacklogProgressTxt;
    private LinearLayout taskBacklogProgressLin;
    private TextView task_backlog_progress_lin_below_txt;
    private int width;
    private int heigth;
    private int height;
    private int width1;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizen_progress);
        initView();
    }

    boolean hasMeasured = false;
    StringBuffer sb = new StringBuffer("");

    private void initView() {
        context = this;

        taskBacklogProgressProgressBar = (ProgressBar) findViewById(R.id.task_backlog_progress_progress_bar);
        taskBacklogProgressTxt = (TextView) findViewById(R.id.task_backlog_progress_txt);
        taskBacklogProgressLin = (LinearLayout) findViewById(R.id.task_backlog_progress_lin);
        task_backlog_progress_lin_below_txt = (TextView)findViewById(R.id.task_backlog_progress_lin_below_txt);


        ViewTreeObserver vto = taskBacklogProgressLin.getViewTreeObserver();
        vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener(){
            public boolean onPreDraw(){
                if (hasMeasured == false) {
                    height = taskBacklogProgressLin.getMeasuredHeight();
                    width = taskBacklogProgressLin.getMeasuredWidth();
                    //获取到宽度和高度后，可用于计算

                    hasMeasured = true;

                    sb.append("taskBacklogProgressLin.getMeasuredWidth() "+ width + "\n" );
                    sb.append("taskBacklogProgressLin.getMeasuredHeight() "+ heigth + "\n" );

                    task_backlog_progress_lin_below_txt.setText(sb.toString());

                    ViewGroup.LayoutParams params = taskBacklogProgressProgressBar.getLayoutParams();
                    params.width = measureWidth(100,100,width - dip2px(context,50));
                    params.height = dip2px(context, 6);
                    // params.setMargins(dip2px(MainActivity.this, 1), 0, 0, 0); // 可以实现设置位置信息，如居左距离，其它类推
                    // params.leftMargin = dip2px(MainActivity.this, 1);

                    taskBacklogProgressProgressBar.setLayoutParams(params);
                }
                return true;
            }
        });


    }

    /**
     * 计算进度的宽度
     */
    public int measureWidth(int total ,int current , int allWidth){
        float unitLength = (float) allWidth/total;
        int nowWidth = (int)(current*unitLength);

        return nowWidth;
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
