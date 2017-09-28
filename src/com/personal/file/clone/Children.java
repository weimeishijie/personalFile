package com.personal.file.clone;

import java.io.Serializable;

/**
 * Created by mj on 2017/9/26.
 */
public class Children implements Cloneable, Serializable{

    private static final long serialVersionUID = 6832122780722711261L;

    public String name;

    public Children(){

    }

    public Children(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return "Children{" +
                "name='" + name + '\'' +
                '}';
    }


    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
