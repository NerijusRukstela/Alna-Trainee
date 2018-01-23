package com.mkyong.editor.domain;


import java.io.Serializable;

public class Employee implements Serializable {
    private String department;
    private String name;
    private String position;
    private long id;
    private String sort;

    public Employee(String name, String position, String department) {

        this.name = name;
        this.department = department;
        this.position = position;
    }

    public Employee(String name, String position, String department, long id) {

        this.name = name;
        this.department = department;
        this.position = position;
        this.id = id;
    }

    public Employee(String sort) {
        this.sort = sort;
    }

    public Employee() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSort() {
        return sort;
    }
}
