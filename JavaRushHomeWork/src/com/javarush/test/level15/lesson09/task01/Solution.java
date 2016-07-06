package com.javarush.test.level15.lesson09.task01;

import java.util.HashMap;
import java.util.Map;

/* Статики 1
В статическом блоке инициализировать labels 5 различными парами.
*/

public class Solution {
    public static Map<Double, String> labels = new HashMap<Double, String>();

    static {
        labels.put(12d, "1111");
        labels.put(2d, "11w11");
        labels.put(1d, "11www11");
        labels.put(112d, "11fff11");
        labels.put(133d, "fdsf11");
    }

    public static void main(String[] args) {
        System.out.println(labels);
    }
}
