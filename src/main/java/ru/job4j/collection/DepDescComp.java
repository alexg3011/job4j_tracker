package ru.job4j.collection;

import java.util.Comparator;

public class DepDescComp implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {

        String value1 = o1.split("/")[0];
        String value2 = o2.split("/")[0];
        int res = value2.compareTo(value1);
        return res == 0 ? o1.compareTo(o2) : res;
    }
}