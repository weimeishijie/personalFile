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
     * isPresent : 与get()方法合用
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
     * ifPresent :
     * 如果Optional实例有值则为其调用 consumer，否则不做处理
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

        // Optional对象的正确书写方式;  存在对它做点什么
        //user.ifPresent(System.out::println);
        //而不是
        //if(user.isPresent()){
        //  System.out.println(suer.get());
        // }

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
        // Optional对象的正确书写方式;  存在即返回，无则提供默认值
        // return user.orElse(null);而不是 return user.isPresent()? user.get() : null;
        // return user.orElse(UNKNOWN_USER)
    }

//    public static void main(String[] args) {
//        orElseDemo();
//    }

    /**
     * orElseGet :
     * orElseGet与orElse方法类似，区别在于得到的默认值。orElse方法将传入的字符串作为默认值，
     * orElseGet方法可以接受Supplier接口的实现用来生成默认值。示例如下：
     */
    private static void orElseGetDemo(){
        Optional empty = Optional.ofNullable(null);
        Optional name = Optional.ofNullable("Sanaulla");
        //orElseGet与orElse方法类似，区别在于orElse传入的是默认值，
        // orElseGet可以接受一个lambda表达式生成默认值
        //输出：Default Value
        System.out.println(empty.orElseGet(() -> "Default Value"));
        //输出：Sanaulla
        System.out.println(name.orElseGet(() -> "Default Value"));

        //Optional对象的正确书写方式； 存在即返回，无则由函数来产生
        //return user.orElseGet(() -> fetchAuserFromDatabase())
        //而不是
        //return user.isPresent()? user : fetchAuserFromDatabase();

    }

//    public static void main(String[] args) {
//        orElseGetDemo();
//    }

    /**
     * orElseThrow :
     * 如果有值则将其返回，否则抛出supplier接口创建的异常。
     *
     * 在orElseGet方法中，我们传入一个 Supplier 接口。然而，在orElseThrow中我们可以传入一个
     * lambda 表达式或方法，如果值不存在来抛出异常。示例如下：
     */
    private void orElseThrowDemo(){
        try{
            Optional empty = Optional.ofNullable(null);
            //orElseThrow与orElse方法类似。与返回值不同，
            // orElseThrow会抛出lambda表达式或方法生成的异常
            empty.orElseThrow(ValueAbsentException::new);
        } catch(Throwable ex){
            ex.printStackTrace(System.out);
        }

    }
    class ValueAbsentException extends Throwable{
        public ValueAbsentException(){
            super();
        }

        public ValueAbsentException(String msg){
            super(msg);
        }

        @Override
        public String getMessage(){
            return "No value present in the Optional instance";
        }
    }

//    public static void main(String[] args) {
//        new OptionalDemo().orElseThrowDemo();
//    }

    /**
     * map :
     * 如果有值，则对其执行调用mapping函数得到返回值。如果返回值不为null，则创见包含mapping
     * 返回值的Optional作为map方法返回值，否则返回空Optional
     *
     * map方法用来对Optional实例的值执行一系列操作。通过一组实现了Function接口的lambda表达式
     * 传入操作，map方法的实例如下：
     */
    private static void mapDemo(){
        Optional name = Optional.ofNullable("Sanaulla");
        // map方法执行传入的lambda表达式参数对Optional实例的值进行修改。
        //为lambda表达式的返回值创建新的Optional实例作为map方法的返回值
        Optional<String> upperName = name.map((value) -> value.toString());
        System.out.println(upperName.orElse("No value found"));

        //map 函数隆重登场
        //当 user.isPresent()为真，获得它关联的orders，为假则返回一个空集合时，我们用上面的
        //orElse，orElseGet方法都乏力时，那原本就是map函数的责任，我们可以这样一行
        //return user.map(u -> u.getOrders()).orElse(Collections.emptyList())
        //而不是
        //if(user.isPresent()){
        //  return user.get().getOrders();
        // } else {
        //  return Collections.emptyList();
        // }

        // map 是可能无限级联的，比如在深一层，获得用户名的大写形式
        // return user.map(u -> u.getUsername()).map(name -> name.toUppercase()).orElse(null);
        //而不是
        //User user = ......
        //if(user != null){
        //  String name = user.getUsername()
        //  if(name != null){
        //    return name.toUpperCase();
        //  } else {
        //    return null;
        //  }
        // } else {
        //   return null;
        // }


    }

//    public static void main(String[] args) {
//        mapDemo();
//    }

    /**
     * flatMap :
     * 如果有值，为其执行mapping函数返回Optional类型返回值，否则返回Optional。flatMap
     * 与map(Function)方法类似，区别在于flatMap中的mapper返回值必须是Optional。调用结束时
     * flatMap不会对结果用Optional封装。
     *
     * flatMap方法与map方法类似，区别在于mapping函数的返回值不同。map方法的mapping函数返回值
     * 可以是任何类型T，而flatMap方法的mapping函数必须是Optional。
     */
    private static void flatMapDemo(){
        Optional<String> name = Optional.ofNullable("Sanaulla");
        // flatMap与map(Function)非常类似，区别在于传入方法的lambda表达式的返回类型。
        //map方法中的lambda表达式返回值可以是任意类型，在map函数返回之前会包装为Optional
        //但flatMap方法中的lambda表达式返回值必须是Optional实例。
        Optional<String> upperName = name.flatMap((value) -> Optional.of(value.toUpperCase()));
        System.out.println(upperName.orElse("No value found"));//输出SANAULLA
    }

//    public static void main(String[] args) {
//        flatMapDemo();
//    }

    /**
     * filter :
     * filter这个方法通过传入限定条件对Optional实例的值进行过滤
     *
     * 如果有值并且满足断言条件返回包含该值的Optional，否则返回空Optional
     *
     * filter方法可以传入一个lambda表达式。对于filter函数我们应该传入实现了Predicate接口的lambda表达式。
     */
    private static void filterDemo(){
        Optional<String> name = Optional.ofNullable("Sanaulla");
        //filter方法检查给定的Option值是否满足某些条件。
        //如果满足则返回同一个Option实例，否则返回空Optional
        Optional<String> longName = name.filter((value) -> value.length() > 6);
        System.out.println(longName.orElse("The name is less than 6 characters"));

        //另一个例子是Optional值不满足filter指定的条件
        Optional<String> anotherName = Optional.of("Sana");
        Optional<String> shortName = anotherName.filter((value) -> value.length() > 6);
        //输出：name长度不足6字符
        System.out.println(shortName.orElse("The name is less then 6 characters"));
    }

//    public static void main(String[] args) {
//        filterDemo();
//    }



}












