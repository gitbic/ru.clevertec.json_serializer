package ru.clevertec.testclasses;

import java.util.List;
import java.util.Map;

public class Student {
    private String name;
    private double stipend;
    private List<String> specs;
    private String[] languages;
    private Map<String, Boolean> passedTests;
    private PhysiologicalData physiologicalData;

    public Student(String name, double stipend, List<String> specs, String[] languages,
                   Map<String, Boolean> passedTests, PhysiologicalData physiologicalData) {
        this.name = name;
        this.stipend = stipend;
        this.specs = specs;
        this.languages = languages;
        this.passedTests = passedTests;
        this.physiologicalData = physiologicalData;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getStipend() {
        return stipend;
    }

    public void setStipend(double stipend) {
        this.stipend = stipend;
    }

    public List<String> getSpecs() {
        return specs;
    }

    public void setSpecs(List<String> specs) {
        this.specs = specs;
    }

    public String[] getLanguages() {
        return languages;
    }

    public void setLanguages(String[] languages) {
        this.languages = languages;
    }

    public Map<String, Boolean> getPassedTests() {
        return passedTests;
    }

    public void setPassedTests(Map<String, Boolean> passedTests) {
        this.passedTests = passedTests;
    }

    public PhysiologicalData getPhysiologicalData() {
        return physiologicalData;
    }

    public void setPhysiologicalData(PhysiologicalData physiologicalData) {
        this.physiologicalData = physiologicalData;
    }
}
