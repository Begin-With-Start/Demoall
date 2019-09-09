package demo.minifly.com.designpattern.abstractfactory;

import demo.minifly.com.fuction_demo.utils.LogUtils;

public class AMDCpu implements Cpu {

    private int pins = 0 ;
    public AMDCpu(int pins) {
        this.pins = pins;
    }

    @Override
    public void calculate() {
        LogUtils.showErrLog("amd cup 针脚数：  " + pins);
    }
}
