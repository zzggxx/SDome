package com.example.lenovo.osdemo;

/**
 * Created by Lenovo on 2016/8/30.
 */
public class Course {
    private String name;
    private int id;

    public Course(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
