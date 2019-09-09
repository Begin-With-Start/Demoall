package demo.minifly.com.designpattern.abstractfactory;

import demo.minifly.com.fuction_demo.utils.LogUtils;

public class IntelMainBoard implements MainBoard {

    private int cpuHoles; //cpu的插槽

    public IntelMainBoard(int cpuHoles) {
        this.cpuHoles = cpuHoles;
    }

    @Override
    public void installCPU() {
        LogUtils.showErrLog("intel 主板 cpu卡插槽数： " + cpuHoles);
    }
}
