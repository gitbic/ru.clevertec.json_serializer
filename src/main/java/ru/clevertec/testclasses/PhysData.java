package ru.clevertec.testclasses;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class PhysData {
    private int age;
    private double weight;
    private double height;
    private Sex sex;
//    Box box = new Box();

    public PhysData(int age, double weight, double height, Sex sex) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhysData physData = (PhysData) o;
        return age == physData.age &&
                Double.compare(physData.weight, weight) == 0 &&
                Double.compare(physData.height, height) == 0 &&
                sex == physData.sex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, weight, height, sex);
    }
}
