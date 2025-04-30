package com.desabisc.guide.webflux.model;

public class Person {
    private Integer id;
    private String name;
    private int age;
    private double weight;
    private char gender;
    private boolean married;

    public Person() {}

    public Person(Integer id, String name, int age, double weight, char gender, boolean married) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.gender = gender;
        this.married = married;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public boolean isMarried() {
        return married;
    }

    public void setMarried(boolean married) {
        this.married = married;
    }

    @Override
    public String toString() {
        return "Person{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", age=" + age +
            ", weight=" + weight +
            ", gender=" + gender +
            ", married=" + married +
            '}';
    }
}
