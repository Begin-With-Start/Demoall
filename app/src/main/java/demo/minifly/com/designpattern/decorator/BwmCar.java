package demo.minifly.com.designpattern.decorator;

import demo.minifly.com.fuction_demo.utils.LogUtils;

public class BwmCar implements Car {

    @Override
    public void drive(String destStr) {
        LogUtils.showErrLog("BwmCar 车 开出来了！"+ destStr);
    }
}