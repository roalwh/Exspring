package com.tj.edu.practice3.objectmapper.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class User {
    private String name;

    @JsonProperty("age")
    private int age;

    private List<Car> userCars;

    public List<Car> getUserCars() {
        return userCars;
    }

    public void setUserCars(List<Car> userCars) {
        this.userCars = userCars;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", userCars=" + userCars +
                '}';
    }
}
