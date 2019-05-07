package demo.minifly.com.DesignPattern.decorator;

import demo.minifly.com.utils.LogUtils;

public class InsuranceIDecoratorImp extends InsuranceIDecorator{

    public InsuranceIDecoratorImp(Car car) {
        super(car);
    }

    @Override
    public void drive(String destStr) {
        //添加自己的处理了；
        LogUtils.showErrLog(" 先给车做个保险吧，才能开出去的！");
        car.drive("");
    }
}

