package demo.minifly.com.designpattern.abstractfactory;

public class ComputerEngineer {

    private AbstractFactory abstractFactory;
    private Cpu cpu;
    private MainBoard mainBoard;
    /**
     * 攒一个电脑；
     */
    public void makeComputer(AbstractFactory abstractFactory){
        this.abstractFactory = abstractFactory;
        prepareCompter();
    }

    private void prepareCompter(){
        cpu = this.abstractFactory.createCup();
        mainBoard = this.abstractFactory.createMainBoard();

        cpu.calculate();
        mainBoard.installCPU();
    }

}
