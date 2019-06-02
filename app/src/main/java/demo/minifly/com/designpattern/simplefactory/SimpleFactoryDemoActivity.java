package demo.minifly.com.designpattern.simplefactory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import demo.minifly.com.R;

public class SimpleFactoryDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_factory_demo);

        new CarFactory().product(CarType.AudiType).drive();
        new CarFactory().product(CarType.AwmType).drive();
        new CarFactory().product(CarType.JiliType).drive();
    }
}
