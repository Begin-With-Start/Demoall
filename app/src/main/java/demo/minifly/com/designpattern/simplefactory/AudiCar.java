package demo.minifly.com.designpattern.simplefactory;

import demo.minifly.com.fuction_demo.utils.LogUtils;

public class AudiCar implements Car {
    @Override
    public void drive() {
        LogUtils.showErrLog("audi 车 ");
    }
}
