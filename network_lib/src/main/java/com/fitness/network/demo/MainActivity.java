package com.fitness.network.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.fitness.network.NetTypes;
import com.fitness.network.NetworkManager;
import com.fitness.network.R;
import com.fitness.network.annotation.NetworkListener;

public class MainActivity extends AppCompatActivity {
    private final static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_network);
        NetworkManager.getDefault().registerObserver(this);

        findViewById(R.id.network_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, NetworkTest2Activity.class);
                startActivity(intent);
            }
        });
    }

    @NetworkListener(type = NetTypes.WIFI)
    private void netChange(String type){
        Toast.makeText(this,"MainActivity net change  wifi: " + type,Toast.LENGTH_LONG).show();
        Log.e("mainactivity" ,"MainActivity net change  wifi: " + type );
    }

    @NetworkListener()
    private void netChange2(String type){
        Toast.makeText(this,"MainActivity netChange2 auto:" + type,Toast.LENGTH_LONG).show();
        Log.e("mainactivity" ,"MainActivity net netChange2  auto: " + type );

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("mainactivity" ,"MainActivity onstop 状态");

    }
}
