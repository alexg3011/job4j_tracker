package ru.job4j.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamUsage {

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, -1, 2, -3, 0));
        List<Integer> rsl = list.stream().filter(
                num -> num > 0
        ).collect(Collectors.toList());
        rsl.forEach(System.out::println);
    }
}
