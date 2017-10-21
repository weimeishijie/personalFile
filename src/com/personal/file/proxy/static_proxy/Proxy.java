package com.personal.file.proxy.static_proxy;

/**
 * 代理对象
 * Created by mj on 2017/10/21.
 */
public class Proxy implements Subject {

    RealObject realObject;

    public Proxy(RealObject realObject) {
        this.realObject = realObject;
    }

    @Override
    public void hello() {
        System.out.println("Before");
        try {
            realObject.hello();
        } catch (Exception e){
            System.out.println(e);
        } finally {
            System.out.println("After");
        }
    }
}
