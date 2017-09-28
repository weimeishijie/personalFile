package com.personal.file.clone;

/**
 * 注意代码实现为浅拷贝，如果想实现深拷贝，参考如下代码
 *
 * copy constructor
 * public Car(Car car){
 *     Wheel wheel = new Wheel();
 *     wheel.brand = car.wheel.brand;
 *
 *     this.wheel = wheel;
 *     this.manufacturer = car.manufacturer;
 * }
 *
 * 为了更加便捷，我们还可以为上述类增加一个静态方法
 *
 */
public class Car {

    Wheel wheel;
    String manufacturer;

    public Car(Wheel wheel,String manufacturer){
        this.wheel = wheel;
        this.manufacturer = manufacturer;
    }

    //copy constructor
    public Car(Car car){
        this(car.wheel,car.manufacturer);
    }

    public static class Wheel{
        String brand;
    }

}














