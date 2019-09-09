package demo.minifly.com.designpattern.abstractfactory;

import java.text.DateFormat;

public class CustomerClient {

    /**
     * 攒一个intel的电脑
     */
    public void needInterComputer(){
        ComputerEngineer engineer = new ComputerEngineer();
        engineer.makeComputer(new IntelFactory());
    }

    /**
     * 攒一个amd的电脑
     */
    public void needAmdComputer(){
        ComputerEngineer engineer = new ComputerEngineer();
        engineer.makeComputer(new AmdFactory());
    }
}
