package demo.minifly.com.designpattern.abstractfactory;

/**
 * 抽象工程类
 */
public interface AbstractFactory {
    Cpu createCup();
    MainBoard createMainBoard();
}
