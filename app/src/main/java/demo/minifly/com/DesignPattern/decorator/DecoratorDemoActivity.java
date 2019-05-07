package demo.minifly.com.DesignPattern.decorator;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import demo.minifly.com.R;

public class DecoratorDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decorator_demo);

        new BenzCar().drive("");
        InsuranceIDecorator insuranceIDecorator = new InsuranceIDecoratorImp(new BenzCar());
        insuranceIDecorator.drive("");

        new AudiCar().drive("");
        InsuranceIDecorator audiDecorator = new InsuranceIDecoratorImp(new AudiCar());
        audiDecorator.drive("");

        new BwmCar().drive("");
        InsuranceIDecorator bwmDecorator = new InsuranceIDecoratorImp(new BwmCar());
        bwmDecorator.drive("");


    }

}
