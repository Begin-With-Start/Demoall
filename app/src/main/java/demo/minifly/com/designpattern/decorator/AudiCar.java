package demo.minifly.com.designpattern.decorator;

import demo.minifly.com.fuction_demo.utils.LogUtils;

public class AudiCar implements Car {

    @Override
    public void drive(String destStr) {
        LogUtils.showErrLog("AudiCar 车 开出来了！"+ destStr);
    }
}
