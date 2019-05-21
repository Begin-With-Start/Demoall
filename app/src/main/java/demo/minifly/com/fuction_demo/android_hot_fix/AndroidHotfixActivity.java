package demo.minifly.com.fuction_demo.android_hot_fix;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import demo.minifly.com.R;


public class AndroidHotfixActivity extends Activity {

    private TextView hotfix_txt;
    private int num = -1;//替换了方法却没有替换掉属性的值..有的问题啊，坑坑的啊....卧槽
    //对这就是一个坑哈。。。只有到方法中去改动变量的赋值，不这样的话，是不会变的

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_hotfix);
        initView();
    }

    public void initView() {
//        hotfix_txt_1 = (TextView) findViewById(R.id.hotfix_txt_1);
        hotfix_txt = (TextView) findViewById(R.id.hotfix_txt);
//        StringBuilder sb = new StringBuilder("这个地方打印一下改动的值，应该是20000的，如果是-1也就是旧版的数据的话那么就有点坑." + num + "\n\n");
//
//        num = 30000;//这都是后来给的了......
        StringBuilder sb = new StringBuilder("");
        sb.append("已经修改了哦哈哈哈哈哈. \n值是：" + num);
        hotfix_txt.setText(sb.toString());
    }
}
