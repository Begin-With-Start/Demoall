package demo.minifly.com.designpattern.decorator;

import demo.minifly.com.fuction_demo.utils.LogUtils;

public class BeautyCar implements  Car {

    private  Car car;

    public BeautyCar(Car car) {
        this.car = car;
    }

    @Override
    public void drive(String destStr) {
        LogUtils.showErrLog("我喷漆拉!");
        car.drive(destStr);
    }
}
