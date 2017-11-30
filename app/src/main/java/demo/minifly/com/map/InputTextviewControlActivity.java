package demo.minifly.com.map;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.lang.reflect.Method;

import demo.minifly.com.R;

import static demo.minifly.com.R.id.task_addpoint_step5_showall_edittext2;

public class InputTextviewControlActivity extends AppCompatActivity {

    private KeyBoardEditText mTaskAddpointStep5ShowallEdittext;
    private TextInputLayout mTaskAddpointStep5ShowallInputlayout;
    private ClearEditText mDeskMyEmailEdittext;
    private KeyBoardEditText mTaskAddpointStep5ShowallEdittext2;
    private TextInputLayout mTaskAddpointStep5ShowallInputlayout2;
    private Button button;
    private boolean isShowinput = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_textview_control);
        initView();
    }

    private void initView() {

        mTaskAddpointStep5ShowallEdittext = (KeyBoardEditText) findViewById(R.id.task_addpoint_step5_showall_edittext);
        mTaskAddpointStep5ShowallInputlayout = (TextInputLayout) findViewById(R.id.task_addpoint_step5_showall_inputlayout);

        mTaskAddpointStep5ShowallEdittext2 = (KeyBoardEditText) findViewById(task_addpoint_step5_showall_edittext2);
        mTaskAddpointStep5ShowallInputlayout2 = (TextInputLayout) findViewById(R.id.task_addpoint_step5_showall_inputlayout2);

        disableShowSoftInput(mTaskAddpointStep5ShowallEdittext,false);
        disableShowSoftInput(mTaskAddpointStep5ShowallEdittext2,false);

        button = (Button) findViewById(R.id.button_id);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isShowinput){
                    isShowinput = !isShowinput;
                    disableShowSoftInput(mTaskAddpointStep5ShowallEdittext,isShowinput);
                    disableShowSoftInput(mTaskAddpointStep5ShowallEdittext2,isShowinput);
                }else{
                    isShowinput = !isShowinput;
                    disableShowSoftInput(mTaskAddpointStep5ShowallEdittext,isShowinput);
                    disableShowSoftInput(mTaskAddpointStep5ShowallEdittext2,isShowinput);
                }

            }
        });
    }

    /**
     * 禁止Edittext弹出软件盘，光标依然正常显示。
     */
    public void disableShowSoftInput(EditText editText ,boolean isShow) {
        if (android.os.Build.VERSION.SDK_INT <= 10) {
            editText.setInputType(InputType.TYPE_NULL);
        } else {
            Class<EditText> cls = EditText.class;
            Method method;
            try {
                method = cls.getMethod("setShowSoftInputOnFocus", boolean.class);
                method.setAccessible(true);
                method.invoke(editText, isShow);
            } catch (Exception e) {
            }

            try {
                method = cls.getMethod("setSoftInputShownOnFocus", boolean.class);
                method.setAccessible(true);
                method.invoke(editText, isShow);
            } catch (Exception e) {
            }
        }
    }
}
