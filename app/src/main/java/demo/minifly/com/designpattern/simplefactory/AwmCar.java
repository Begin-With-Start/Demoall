package demo.minifly.com.designpattern.simplefactory;


import demo.minifly.com.fuction_demo.utils.LogUtils;

public class AwmCar implements Car {
    @Override
    public void drive() {
        LogUtils.showErrLog("大众车 ");
    }
}
