package demo.minifly.com.designpattern.abstractfactory;

import demo.minifly.com.fuction_demo.utils.LogUtils;

public class IntelCpu implements Cpu {

    private int pins = 0 ;
    public IntelCpu( int pins) {
        this.pins = pins;
    }

    @Override
    public void calculate() {
        LogUtils.showErrLog("intel cup 针脚数：  " + pins);
    }
}
