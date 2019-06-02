package demo.minifly.com.designpattern.decorator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import demo.minifly.com.R;

public class DecoratorDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decorator_demo);

        new BenzCar().drive("");
        BeautyCar beautyCar = new BeautyCar(new BenzCar());
        beautyCar.drive("");
        InsuranceIDecorator insuranceIDecorator = new InsuranceIDecoratorImp(beautyCar);
        insuranceIDecorator.drive("");

        new AudiCar().drive("");
        InsuranceIDecorator audiDecorator = new InsuranceIDecoratorImp(new AudiCar());
        audiDecorator.drive("");

        new BwmCar().drive("");
        InsuranceIDecorator bwmDecorator = new InsuranceIDecoratorImp(new BwmCar());
        bwmDecorator.drive("");


    }

}
