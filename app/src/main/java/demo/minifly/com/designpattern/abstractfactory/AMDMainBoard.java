package demo.minifly.com.designpattern.abstractfactory;

import demo.minifly.com.fuction_demo.utils.LogUtils;

public class AMDMainBoard implements MainBoard {

    private int cpuHoles; //cpu 的插槽数量；

    public AMDMainBoard(int cpuHoles) {
        this.cpuHoles = cpuHoles;
    }

    @Override
    public void installCPU() {
        LogUtils.showErrLog("AMD cpu的卡插槽数： " + cpuHoles);
    }
}
