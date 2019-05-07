package com.fitness.network.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
    }

    @NetworkListener(type = NetTypes.WIFI)
    private void netChange(String type){
        Toast.makeText(this,"net change  " + type,Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        NetworkManager.getDefault().unRegisterObserver(this);
    }
}
