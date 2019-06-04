package demo.minifly.com.asm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.IOException;

import demo.minifly.com.R;

public class AopDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aop_demo);

        GeneratorAop generatorAop = new GeneratorAop();
        generatorAop.generator();

        MyAop myAop = new MyAop();
        myAop.aopMethod();
    }
}
