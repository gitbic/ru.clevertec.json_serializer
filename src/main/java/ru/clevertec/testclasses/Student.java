package ru.clevertec.testclasses;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Student {
    private String name;
    private double stipend;
    private List<String> specs;
    LanguageKnowledge[] languageKnowledges;
    private Map<String, Boolean> passedTests;
    private PhysData physiologicalData;

    public Student(String name, double stipend, List<String> specs, LanguageKnowledge[] languageKnowledges,
                   Map<String, Boolean> passedTests, PhysData physiologicalData) {
        this.name = name;
        this.stipend = stipend;
        this.specs = specs;
        this.languageKnowledges = languageKnowledges;
        this.passedTests = passedTests;
        this.physiologicalData = physiologicalData;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", stipend=" + stipend +
                ", specs=" + specs +
                ", languageKnowledges=" + Arrays.toString(languageKnowledges) +
                ", passedTests=" + passedTests +
                ", physiologicalData=" + physiologicalData +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Double.compare(student.stipend, stipend) == 0 &&
                Objects.equals(name, student.name) &&
                Objects.equals(specs, student.specs) &&
                Arrays.equals(languageKnowledges, student.languageKnowledges) &&
                Objects.equals(passedTests, student.passedTests) &&
                Objects.equals(physiologicalData, student.physiologicalData);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name, stipend, specs, passedTests, physiologicalData);
        result = 31 * result + Arrays.hashCode(languageKnowledges);
        return result;
    }
}
