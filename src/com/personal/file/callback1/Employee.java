package com.personal.file.callback1;

/**
 * 创建控制类，也就是员工对象，他必须持有老板的地址（回调接口），
 * 即使老板换了一茬又一茬，办公室不变，总能找到对应的老板。
 *
 * 员工类，必须要记住，这是一个底层类，底层是不了解上层服务的
 */
public class Employee {

    //联系方式（持有接口）
    private CallBackInterface callBack = null;

    //告诉老板的联系方式，也就是注册
    public void setCallBack(CallBackInterface callBack){
        this.callBack = callBack;
    }

    //工人干活
    public void doSome(){
        //1.开始干活了
        for(int i=0; i<10; i++){
            System.out.println("第【"+i+"】事情干完了");
        }

        //2.告诉老板干完了
        callBack.execute();
    }

}
