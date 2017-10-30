package com.personal.file.comparator;

import java.util.Comparator;

/**
 * 自定义比较器
 * Created by mj on 2017/10/28.
 */
public class IdComparator implements Comparator<User> {

    public IdComparator(){

    }

    @Override
    public int compare(User o1, User o2) {
        if(o1 != null && o2 != null && o1.getId() != null && o2.getId() != null){
            return o2.getId().compareTo(o1.getId());
        }
        return 1;
    }
}
