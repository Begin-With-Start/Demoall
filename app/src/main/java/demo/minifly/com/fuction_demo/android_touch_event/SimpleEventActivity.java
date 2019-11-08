package demo.minifly.com.fuction_demo.android_touch_event;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import demo.minifly.com.R;
import demo.minifly.com.fuction_demo.utils.LogUtils;

public class SimpleEventActivity extends AppCompatActivity {
    private final String TAG = "SimpleEventActivity";
    public MineView mSimpleLayout1View;
    public MineRelativeLayout mSimpleLayout1;
    public MineView mSimpleLayout2View;
    public MineRelativeLayout mSimpleLayout2;
    public ConstraintLayout mSimpleRootviewLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_event);

        this.mSimpleLayout1View = (MineView) findViewById(R.id.simple_layout1_view);
        this.mSimpleLayout1 = (MineRelativeLayout) findViewById(R.id.simple_layout1);
        this.mSimpleLayout2View = (MineView) findViewById(R.id.simple_layout2_view);
        this.mSimpleLayout2 = (MineRelativeLayout) findViewById(R.id.simple_layout2);
        this.mSimpleRootviewLayout = (ConstraintLayout) findViewById(R.id.simple_rootview_layout);

        mSimpleLayout1View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.showErrLog(TAG + "   view mSimpleLayout1View ---- setOnClickListener ");
            }
        });


        mSimpleLayout2.setIntercept(true);
        mSimpleLayout2View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //被viewgroup拦截之后的事件，无法传递到onclick事件来；
                LogUtils.showErrLog(TAG + "   view  mSimpleLayout2View---- setOnClickListener ");
            }
        });
        mSimpleLayout1View.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        LogUtils.showErrLog(TAG + "   view  mSimpleLayout1View---- MotionEvent.ACTION_DOWN ");
                        break;
                    case MotionEvent.ACTION_UP:
                        LogUtils.showErrLog(TAG + "   view  mSimpleLayout1View---- MotionEvent.ACTION_UP ");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        LogUtils.showErrLog(TAG + "   view  mSimpleLayout1View---- MotionEvent.ACTION_MOVE ");
                        break;
                }

                return false;
            }

        });
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogUtils.showErrLog(TAG + "   activity ---- ontouchevent ");
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                LogUtils.showErrLog(TAG + "   activity ---- ontouchevent---- MotionEvent.ACTION_DOWN ");
                break;
            case MotionEvent.ACTION_UP:
                LogUtils.showErrLog(TAG + "   activity ---- ontouchevent---- MotionEvent.ACTION_UP ");
                break;
            case MotionEvent.ACTION_MOVE:
                LogUtils.showErrLog(TAG + "   activity ---- ontouchevent---- MotionEvent.ACTION_MOVE ");
                break;
        }

        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        LogUtils.showErrLog(TAG + "   activity ---- dispatchTouchEvent ");
        return super.dispatchTouchEvent(ev);
    }

}
