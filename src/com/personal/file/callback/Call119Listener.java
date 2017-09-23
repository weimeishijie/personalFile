package com.personal.file.callback;

/**
 * 回调方法的处理
 */
public class Call119Listener implements I119_CallBack{
    /**
     * 回调处理方法
     * 如果是119，消防员出动
     */
    @Override
    public void callBack_119(int num) {
        if(num == 119){
            callback();
        } else {
            System.out.println("--->不在119职责范围内，请联系其他部门.");
        }
    }

    public void callback(){
        System.out.println("Hello World");
    }
}
