package demo.minifly.com.designpattern.simplefactory;

public class CarFactory {

    public Car product(@CarType int cartype) {
        switch (cartype) {
            case CarType.AudiType:
                return new AudiCar();
            case CarType.AwmType:
                return new AwmCar();
            case CarType.JiliType:
                return new JiliCar();
            default:
                return null;
        }
    }
}
