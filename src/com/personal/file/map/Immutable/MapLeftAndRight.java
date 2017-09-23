package com.personal.file.map.Immutable;


import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by mj on 2017/9/9.
 */
public class MapLeftAndRight {

    public static void main(String[] args) {
        //Pair类用的是commons-lang3-3.4.jar包下的
        Map<String, Pair<String, String>> map = new ConcurrentHashMap<>();
        map.put("li", new ImmutablePair<>("he", "hui"));
        map.put("wen", new ImmutablePair<>("he","hui"));
        map.put("ya", new ImmutablePair<>("he", "hui"));
        System.out.println(map);
        String left = map.get("li").getLeft();
        String right = map.get("li").getRight();
        System.out.println("left: "+left+ " "+"right: "+right);


    }

}
