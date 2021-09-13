package ru.job4j.collection;

import java.util.Comparator;

public class DepDescComp implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {

        String value1 = o1.split("/")[0];
        String value2 = o2.split("/")[0];
        if (value1.equals(value2)) {
            return o1.compareTo(o2);
        } else {
            return value2.compareTo(value1);
        }
    }
}