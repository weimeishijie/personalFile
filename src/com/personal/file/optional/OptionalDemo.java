package com.personal.file.optional;

import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * 新版本的Java，比如java 8引入了一个新的Optional类，Optional类的Javadoc描述如下：
 */
public class OptionalDemo {

    /**
     * 这是一个可以为null的容器对象。如果值存在则isPresent()方法会返回true，
     * 调用get()方法会返回该对象。
     *
     * of :
     * 为非null的值创建一个Optional；
     * of方法通过工厂方法创建Optional类。需要注意的是，创建对象时传入的参数不能为null。
     * 如果传入参数为null，则抛出NullPointerException
     */
    private static void ofDemo(){
        //调用工厂方法创建Optional实例
        Optional<String> name = Optional.of("Sanaulla");
        System.out.println(name);
        //传入参数为null，抛出NullPointerException
        Optional<String> someNull = Optional.of(null);
    }

//    public static void main(String[] args) {
//        ofDemo();
//    }

    /**
     * ofNullable :
     * 为指定的值创建一个Optional，如果指定的值为null，则返回一个空的Optional。
     *
     * ofNullable与of方法相似，唯一的区别是可以接受参数为null的情况。实例如下：
     */
    private static void ofNullableDemo(){
        //下面创建了一个不包含任何值得Optional实例，例如：值为null
        Optional empty = Optional.ofNullable(null);
        System.out.println(empty);
    }

//    public static void main(String[] args) {
//        ofNullableDemo();
//    }

    /**
     * isPresent :
     * 如果值存在返回true，否则返回false
     */
    private static void isPresentDemo(){
        Optional<String> name = Optional.of("Sanaulla");
        if(name.isPresent()){
            System.out.println(name.get());
        }
    }

//    public static void main(String[] args) {
//        isPresentDemo();
//    }

    /**
     * get :
     * 如果Optional有值则将其返回，否则抛出NoSuchElementException.
     */
    private static void getDemo(){
        Optional empty = Optional.ofNullable(null);
        //执行下面的代码回输出：No value present
        try {
            //在空的Optional实例上调用get()，抛出NoSuchElementException
            System.out.println(empty.get());
        } catch (NoSuchElementException e){
            e.printStackTrace(System.out);
        }
    }

//    public static void main(String[] args) {
//        getDemo();
//    }

    /**
     * isPresent :
     * 如果Optional实例有值则为其调用consumer，否则不做处理
     *
     * 要理解ifPresent方法，首先需要了解Consumer类。简单地说，Consumer类包含一个抽象方法，
     * 该抽象方法对传入的值进行处理，但没有返回值。Java8支持不用接口直接通过lambda表达式
     * 传入参数
     */
    private static void ifPresentDemo(){
        Optional<String> name = Optional.ofNullable("Sanaulla");
        //ifPresent方法接受 lambda 表达式作为参数，lambda表达式对Optional的值调用consumer进行处理
        name.ifPresent((value) -> {
            System.out.println("The length of the value is: "+value.length());
        });
    }

//    public static void main(String[] args) {
//        ifPresentDemo();
//    }

    /**
     * orElse :
     * 如果有值则将其返回，否则返回指定的其它值。
     *
     * 如果Optional实例有值则将其返回，否则返回orElse方法传入的参数。示例如下：
     */
    private static void orElseDemo(){
        Optional empty = Optional.ofNullable(null);
        Optional<String> name = Optional.ofNullable("Sanaulla");
        //如果值不为null，orElse方法返回Optional实例的值，如果为null，返回传入的消息。
        //输出：There is no value present!
        System.out.println(empty.orElse("There is no value persent!"));
        //输出：Sanaulla
        System.out.println(name.orElse("There is some value!"));
    }

//    public static void main(String[] args) {
//        orElseDemo();
//    }




}












