package com.example.proga_lab8.my_program.obj;

import java.sql.Timestamp;

public class Human {
    private Timestamp birthday;
    private String name;
    private int id;

    public Human(int id, Timestamp date, String name) {
        birthday = date;
        this.name = name;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Его высочество: " +name + " родился: " + birthday;
    }

    public String getName() {
        return name;
    }

    public Timestamp getBirthday() {
        return birthday;
    }

    public int getId() {
        return id;
    }
}
