package com.leo.leomvp.test.dagger;

/**
 * Created by Leo on 2017/7/21.
 */


public class Cloth {

    private String color;

    @Override
    public String toString() {
        return color + "布料";
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
