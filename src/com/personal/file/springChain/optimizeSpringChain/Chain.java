package com.personal.file.springChain.optimizeSpringChain;

import java.util.List;

/**
 * Created by mj on 2017/10/30.
 */
public class Chain {

    private List<ChainHandler> handlers;

    private int index = 0;

    public Chain(List<ChainHandler> handlers) {
        this.handlers = handlers;
    }

    public void process(){
        if(index >= handlers.size()){
            return;
        }
        handlers.get(index++).execute(this);
    }
}
