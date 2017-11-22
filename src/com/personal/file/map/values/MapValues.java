package com.personal.file.map.values;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mj on 2017/9/9.
 *
 */
public class MapValues {
    public static void main(String[] args) {
        //HashMap容器是没有顺序的
        Map<String, String> map = new HashMap<>();
        map.put("li", "h");
        map.put("wen", "e");
        map.put("ya", "hui");
        System.out.println(map);
        Collection<String> collection = map.values();
        System.out.println(collection);

    }
}
