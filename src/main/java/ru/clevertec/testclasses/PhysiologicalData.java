package ru.clevertec.testclasses;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PhysiologicalData {
    private int age;
    private double weight;
    private double height;
    private Sex sex;
    Box box = new Box();

    public PhysiologicalData(int age, double weight, double height, Sex sex) {
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.sex = sex;
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

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

}
