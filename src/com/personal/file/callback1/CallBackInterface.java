package com.personal.file.callback1;

/**
 * 首先创建一个回调接口，让老板得告知干完活如何找到他的方式：留下老板办公室地址：
 */
public interface CallBackInterface {

    /**
     * 此接口为联系的方式，不论是电话号码还是联系地址，作为
     * 老板都必须要实现此接口
     */
    void execute();
}
