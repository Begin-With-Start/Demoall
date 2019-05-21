package demo.minifly.com.fuction_demo.map;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import demo.minifly.com.R;

import static demo.minifly.com.R.id.task_addpoint_step5_showall_edittext2;

public class InputTextviewControlActivity extends AppCompatActivity {

    private KeyBoardEditText mTaskAddpointStep5ShowallEdittext;
    private TextInputLayout mTaskAddpointStep5ShowallInputlayout;
    private ClearEditText mDeskMyEmailEdittext;
    private KeyBoardEditText mTaskAddpointStep5ShowallEdittext2;
    private TextInputLayout mTaskAddpointStep5ShowallInputlayout2;
    private Button button,button2,controlBtn;
    private boolean isShowinput = false;
    private KeyboardEditLayout mAllLayout;
    private boolean isShowInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_textview_control);
        initView();
    }

    private void initView() {
        isShowInput = false;
        mTaskAddpointStep5ShowallEdittext = (KeyBoardEditText) findViewById(R.id.task_addpoint_step5_showall_edittext);
        mTaskAddpointStep5ShowallInputlayout = (TextInputLayout) findViewById(R.id.task_addpoint_step5_showall_inputlayout);

        mTaskAddpointStep5ShowallEdittext2 = (KeyBoardEditText) findViewById(task_addpoint_step5_showall_edittext2);
        mTaskAddpointStep5ShowallInputlayout2 = (TextInputLayout) findViewById(R.id.task_addpoint_step5_showall_inputlayout2);
        controlBtn = (Button) findViewById(R.id.control_btn);
        button2 = (Button) findViewById(R.id.button_id2);
        mAllLayout = (KeyboardEditLayout) findViewById(R.id.all_layout);

        button = (Button) findViewById(R.id.button_id);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAllLayout.previous();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAllLayout.next();
            }
        });

        controlBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isShowInput){
                    isShowInput = !isShowInput;
                    mAllLayout.startAllChildSoftinput();
                }else{
                    isShowInput = !isShowInput;
                    mAllLayout.forbidenAllChildSoftinput();
                }
            }
        });
    }
}
