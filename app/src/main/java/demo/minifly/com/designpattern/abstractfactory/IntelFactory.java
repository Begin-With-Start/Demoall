package demo.minifly.com.designpattern.abstractfactory;

public class IntelFactory implements AbstractFactory {
    @Override
    public Cpu createCup() {
        return new IntelCpu(358);
    }

    @Override
    public MainBoard createMainBoard() {
        return new IntelMainBoard(2);
    }
}
