package com.personal.file.proxy.static_proxy;

/**
 * 目标对象
 * Created by mj on 2017/10/21.
 */
public class RealObject implements Subject {
    @Override
    public void hello() {
        System.out.println("hello");
    }
}
