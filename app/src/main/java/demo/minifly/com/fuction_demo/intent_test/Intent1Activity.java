package demo.minifly.com.fuction_demo.intent_test;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import demo.minifly.com.R;
import demo.minifly.com.fuction_demo.bean.BeanIn;

/**
 * 通过一个例子来看看，在两个activity里面进行传递intent的时候会不会重新新建一个对象
 * 看看对象的地址有没有发生变化
 * 对象地址发生了改变。
 *
 */
public class Intent1Activity extends AppCompatActivity {

    private TextView intentActivityTxt;
    private Button btn;
    private Context mContext;
    private BeanIn bean;

    /**
     * 事实证明，每次如果在oncreate里面进行的操作，会在第一次将activity放入栈中的时候，进行一次初始化操作
     * 而，在onresume里面的操作，在每次从入栈和到栈顶的操作，都会把操作进行一次
     * 那么如果是非常耗时，并且只需要一次的操作应该在oncreate中进行。
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent1);
        initView();
    }

    private void initView() {
        mContext = this;
        btn = (Button) findViewById(R.id.intent_activity_btn);
        Service service ;
        intentActivityTxt = (TextView) findViewById(R.id.intent_activity_txt);
        bean = new BeanIn();

        intentActivityTxt.setText(bean.toString());


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(mContext,Intent2Activity.class);
                Bundle bundle = new Bundle();

                bundle.putParcelable("bean", bean);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}
