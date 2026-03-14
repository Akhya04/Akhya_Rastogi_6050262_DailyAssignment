package com.demo.jdbc;

public class Employee {

    int student_id;
    String name;
    int age;
    String course;

    public Employee(int student_id, String name, int age, String course) {
        this.student_id = student_id;
        this.name = name;
        this.age = age;
        this.course = course;
    }

    public Employee(String name, int age, String course) {
        this.name = name;
        this.age = age;
        this.course = course;
    }
}