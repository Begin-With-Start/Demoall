package demo.minifly.com.map;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;

import demo.minifly.com.R;

import static demo.minifly.com.R.id.desk_my_email_edittext;

public class InputTextviewControlActivity extends AppCompatActivity{

    private Button button;
    private boolean isShowInput = true;
    private KeyBoardEditText mTaskAddpointStep5ShowallEdittext;
    private TextInputLayout mTaskAddpointStep5ShowallInputlayout;
    private ClearEditText mDeskMyEmailEdittext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_textview_control);
        initView();
    }

    private void initView() {
        button = (Button) findViewById(R.id.demo_inputtext_button);
        mTaskAddpointStep5ShowallEdittext = (KeyBoardEditText) findViewById(R.id.task_addpoint_step5_showall_edittext);
        mTaskAddpointStep5ShowallInputlayout = (TextInputLayout) findViewById(R.id.task_addpoint_step5_showall_inputlayout);
        mDeskMyEmailEdittext = (ClearEditText) findViewById(desk_my_email_edittext);

        mTaskAddpointStep5ShowallEdittext.setOnKeyboardClick(new KeyBoardEditText.OnKeyboardClick() {
            @Override
            public void onKeyboardClick() {
                if(isShowInput){
                    isShowInput = !isShowInput;
                    mTaskAddpointStep5ShowallEdittext.setInputType(InputType.TYPE_NULL);
                }else{
                    isShowInput = !isShowInput;
                    mTaskAddpointStep5ShowallEdittext.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                }
            }
        });

        mTaskAddpointStep5ShowallEdittext.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {

            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable arg0) {
                mTaskAddpointStep5ShowallEdittext.setSelection(mTaskAddpointStep5ShowallEdittext.length());
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(isShowInput){
//                    isShowInput = !isShowInput;
////                    mTaskAddpointStep5ShowallEdittext.setKeyBoardInputType(InputType.TYPE_NULL);
//                }else{
//                    isShowInput = !isShowInput;
////                    mTaskAddpointStep5ShowallEdittext.setKeyBoardInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
//                }
            }
        });
    }
}
