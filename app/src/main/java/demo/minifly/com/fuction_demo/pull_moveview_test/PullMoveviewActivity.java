package demo.minifly.com.fuction_demo.pull_moveview_test;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import demo.minifly.com.R;


/**
 * 作者：minifly on 2016/11/24 11:43
 * 几种可以用来移动view的方式。
 */
public class PullMoveviewActivity extends Activity implements View.OnTouchListener{
    private Context context;
    LayoutDragview1 myView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main_pullviewmove);
        findView();

    }

    public void findView(){
        context = this;
        myView = (LayoutDragview1)findViewById(R.id.myview_id);

        myView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"点击",Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("demoall","事件处理");
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
