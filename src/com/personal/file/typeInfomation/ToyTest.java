package com.personal.file.typeInfomation;

/**
 * 对 Class 类的演示 主要演示的是：
 * Class.forName()、加载不存在的 class 对象 （对象进行了初始化）
 * Object.getClass() 获取已经存在的 class 对象
 * 类名.class 返回一个 class 对象 （此对象没有初始化）
 * Class.getName() 获取类的名字（含有包名）
 * Class.isInterface() 判断类是不是接口
 * Class.getInterface() 获取所有的接口
 * Class.getSimpleName() 获取类的名字（不含包名）
 * Class.getCanonicalName() 获取类的名字（含有包名）
 * Class.getSuperclass() 获取父类
 * Class.newInstance() 为类创建一个对象
 */

interface HasBatteries{}
interface Waterproof{}
interface Shoots{}

class Toy{
    // Comment out the following default constructor to see NoSuchMethodError from (*1*)
    Toy(){}
    Toy(int i){}
}

class FancyToy extends Toy implements HasBatteries, Waterproof, Shoots{
    FancyToy(){
        super(1);
    }
}

public class ToyTest {
    static void printInfo(Class cc){
        System.out.println("Class name: "+cc.getName()+ " is interface? [" + cc.isInterface() + "]");
        System.out.println("Simple name: "+cc.getSimpleName());
        System.out.println("Canonical name : "+cc.getCanonicalName());
    }

    public static void main(String[] args) {
        Class c = null;
        try {
            c = Class.forName("com.personal.file.typeInfomation.FancyToy");
        } catch (ClassNotFoundException e) {
            System.out.println("Can't find FancyToy");
            System.exit(0);
        }
        printInfo(c);
        for(Class face : c.getInterfaces()){
            printInfo(face);
        }
        Class up = c.getSuperclass();
        Object obj = null;
        try {
            // Requires default constructor;
            obj = up.newInstance();
        } catch (InstantiationException e) {
            System.out.println("Cannot instantiate");
            System.exit(1);
        } catch (IllegalAccessException e) {
            System.out.println("Cannot access");
            System.exit(1);
        }
        printInfo(obj.getClass());
        System.out.println();
        printInfo(up);
    }
}
