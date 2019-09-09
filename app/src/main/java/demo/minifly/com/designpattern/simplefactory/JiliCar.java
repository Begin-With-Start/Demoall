package demo.minifly.com.designpattern.simplefactory;

import demo.minifly.com.fuction_demo.utils.LogUtils;

public class JiliCar implements Car {
    @Override
    public void drive() {
        LogUtils.showErrLog("吉利 车 ");
    }
}
