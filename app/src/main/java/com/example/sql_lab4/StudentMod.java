package com.example.sql_lab4;

public class StudentMod {
    private int id;
    private String name;
    private int age;


    public StudentMod(int id, String name, int age) {
        this.id = id;
        this.age = age;
        this.name = name;

    }



    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "StudentMod{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
