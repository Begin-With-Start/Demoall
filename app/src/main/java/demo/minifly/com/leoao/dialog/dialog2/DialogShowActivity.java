package demo.minifly.com.leoao.dialog.dialog2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import demo.minifly.com.R;
import demo.minifly.com.leoao.dialog.dialog2.dialog.CustomeBaseDialogUpdateAll;

public class DialogShowActivity extends AppCompatActivity implements View.OnClickListener {

    private Button dialog_button_show_update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_show);
        initView();
    }

    private void initView() {
        dialog_button_show_update = (Button) findViewById(R.id.dialog_button_show_update);

        dialog_button_show_update.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dialog_button_show_update:
                CustomeBaseDialogUpdateAll updateAll = new CustomeBaseDialogUpdateAll(this);
                updateAll.showProgressview();
                updateAll.showSuspend();
                break;
        }
    }
}
