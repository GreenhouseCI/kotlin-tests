package com.greenhouseci.kotlin_tests.kotlintests;

/**
 * Created by priit on 5.05.16.
 */

public class Formatter {

    public String stripMiddle(String s) {
        return s.replaceAll("\\s+", " ");
    }

    public String stripLeft(String s) {
        return s.replaceAll("^\\s+", "");
    }

    public String stripRight(String s) {
        return s.replaceAll("\\s+$", "");
    }

    public String strip(String s) {
        return stripLeft(stripRight(stripMiddle(s)));
    }
}
