package demo.minifly.com.designpattern.decorator;

public abstract class InsuranceIDecorator implements Car{
    protected Car car;


    public InsuranceIDecorator(Car car) {
        this.car = car;
    }

    @Override
    public void drive(String destStr) {
        this.car.drive("");
    }
}

