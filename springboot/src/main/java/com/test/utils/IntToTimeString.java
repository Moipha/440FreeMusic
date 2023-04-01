package com.test.utils;

public class IntToTimeString {
    public static String Method(int time) {
        int minutes = time / 60;
        int seconds = time % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

}
