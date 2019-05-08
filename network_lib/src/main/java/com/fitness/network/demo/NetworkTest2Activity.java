package com.fitness.network.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.fitness.network.NetTypes;
import com.fitness.network.NetworkManager;
import com.fitness.network.R;
import com.fitness.network.annotation.NetworkListener;

public class NetworkTest2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_test2);
        NetworkManager.getDefault().registerObserver(this);
    }

    @NetworkListener()
    public void netChange(@NetTypes String type){
        Toast.makeText(this,"NetworkTest2Activity 网络变化" + type , Toast.LENGTH_LONG).show();
        Log.e("mainactivity" ,"NetworkTest2Activity 网络变化  auto: " + type );
    }
}
