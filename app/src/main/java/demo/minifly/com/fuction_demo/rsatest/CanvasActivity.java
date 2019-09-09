package demo.minifly.com.fuction_demo.rsatest;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import demo.minifly.com.R;


/**
 * 作者：minifly on 2016/11/24 11:43
 */
public class CanvasActivity extends Activity {

    private TextView rsaTxt;
    private String signResult;
    private String reSignResult;
    private String reSignResultOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main_rsa);
        initView();
        findView();
    }

    public void findView() {

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initView() {

        try {
            signResult = RSAUtilTest.sign("123456");
            reSignResult = RSAUtilTest.resign(signResult);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("rsa sign:" + signResult);

        try {
            String sign= "Iljf/y7axMv/3QsvNLNgMi7Cgp1sGdgL22FP5ZNfRDDGxgu9PdprcCOTcUDSFn6luwVa54HKFdPmfW2X45lH2BlZc+8giCwiF7GBzZMTQNoGRxYyW88DSgZNhNLmw2XH2EOwVz8UTil9eII4YQ3pQuxRWvt2IVCjiIN4vFf9Zuk=";
            reSignResultOut = RSAUtilTest.resign(sign);
        } catch (Exception e) {
            e.printStackTrace();

            reSignResultOut = "error";
        }


        rsaTxt = (TextView)findViewById(R.id.rsa_txt);
        rsaTxt.setText("123456\n" + signResult + "\n\n"+ reSignResult+ "\n\n"+ reSignResultOut);
    }
}
