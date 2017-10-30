package com.personal.file.springChain.simpleSpringChain;

/**
 * spring的链式设计
 * Created by mj on 2017/10/30.
 */
public abstract class Handler {

    private Handler handler;

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public void execute(){
        handlerProcess();
        if(handler == null){
            return;
        }
        handler.execute();
    }

    public abstract void handlerProcess();
}
