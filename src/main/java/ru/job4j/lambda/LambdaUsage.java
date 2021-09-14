package ru.job4j.lambda;

import java.util.Arrays;
import java.util.Comparator;

public class LambdaUsage {
    public static void main(String[] args) {
        Attachment[] atts = {
                new Attachment("image 1", 20),
                new Attachment("image 3", 120),
                new Attachment("image 2", 23)
        };
        Comparator<Attachment> comparator = (left, right) -> {
            System.out.println("compare - " + right.getSize() + " : " + left.getSize());
            return right.getSize() - left.getSize();
        };
        Arrays.sort(atts, comparator);
        for (Attachment a : atts) {
            System.out.println(a);
        }
    }

}
