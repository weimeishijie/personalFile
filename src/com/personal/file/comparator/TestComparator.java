package com.personal.file.comparator;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by mj on 2017/10/28.
 */
public class TestComparator {

    public static void main(String[] args) {
        IdComparator idComparator = new IdComparator();
        SortedSet<User> userSortedSet = new TreeSet<>(idComparator);
        // 假设这里有个集合（从数据中取出的数据）
        List<User> users = new ArrayList<>();
        users.add(new User(1,"admin","admin"));
        users.add(new User(2,"user","admin"));
        users.add(new User(3,"name","admin"));
        users.add(new User(4,"password","admin"));
        users.add(new User(5,"ok","admin"));
        users.add(new User(6,"ok","admin"));

        if(!users.isEmpty()){
            users.stream().forEach(user -> {
                userSortedSet.add(user);
            });
        }

        System.out.println(userSortedSet.toString());

        // 用流对集合排序
        List<User> personList = users.stream()
                .sorted((p1,p2) -> p2.getId().compareTo(p1.getId())).collect(Collectors.toList());
        System.out.println(personList);

    }
}
