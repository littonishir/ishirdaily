package com.litton.ishirdaily.bean;

/**
 * Created by littonishir on 2018/8/3.
 */
public class Games {
    public String name;
    private String type;

    public Games() {}

    public Games(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public String toString() {
        return "Gmae:" + name + "  Type:" + type;
    }

}
