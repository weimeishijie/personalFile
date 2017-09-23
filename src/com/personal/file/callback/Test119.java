package com.personal.file.callback;

/**
 * Created by mj on 2017/9/10.
 */
public class Test119 {

    public static void main(String[] args) {
        Caller caller = new Caller();
        //设置实现类给回调，设置的是实现了接口的类，内部包含了向上造型，
        caller.setCallListener(new Call119Listener());
        //内部调用了回调函数（接口的）方法
        caller.callPolice(119);
    }
}
