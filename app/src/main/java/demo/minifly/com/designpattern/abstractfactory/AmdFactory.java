package demo.minifly.com.designpattern.abstractfactory;

public class AmdFactory implements AbstractFactory {
    @Override
    public Cpu createCup() {
        return new AMDCpu(935);
    }

    @Override
    public MainBoard createMainBoard() {
        return new AMDMainBoard(2);
    }
}
