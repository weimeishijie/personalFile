package com.personal.file.callback1;

/**
 * 创建回调对象，就是老板本人，因为员工干完活后要给他打电话，
 * 因此老板必须实现回调接口，不然员工去哪里找老板？
 *
 * 老板是作为上层应用身份出现的，下层应用（员工）是不知道
 * 有哪些方法，因此他想被下层应用（员工）调用必须实现此接口
 */
public class Boss implements CallBackInterface{
    @Override
    public void execute() {
        System.out.println("收到了！！"+System.currentTimeMillis());
    }
}
