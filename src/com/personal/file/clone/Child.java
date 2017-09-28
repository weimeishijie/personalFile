package com.personal.file.clone;

/**
 * Created by mj on 2017/9/26.
 */
public class Child{

    public String name;

    public Child(){}

    public Child(String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return "Child{" +
                "name='" + name + '\'' +
                '}';
    }
}
