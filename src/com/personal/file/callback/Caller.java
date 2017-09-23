package com.personal.file.callback;

/**
 * 执行者必须持有回调接口
 */
public class Caller {

    //回调实现类，相当于手机卡或者电话线（赋值给他的是上层服务的实现类）
    private I119_CallBack callBack;

    //座机
    public Caller(){}

    //座机还需要接上电话线
    public void setCallListener(I119_CallBack callBack){
        System.out.println("--->座机");
        this.callBack = callBack;
    }
    //手机
    public Caller(I119_CallBack callBack){
        System.out.println("--->手机");
        this.callBack = callBack;
    }
    //拨号报警(调用回调函数)
    public void callPolice(int num){
        System.out.println("--->拨号："+num);
        callBack.callBack_119(num);
    }
}
