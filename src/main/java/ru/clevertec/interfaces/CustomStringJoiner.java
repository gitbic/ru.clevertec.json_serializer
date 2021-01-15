package ru.clevertec.interfaces;

public interface CustomStringJoiner {
    StringBuilder append(String string);

    void addDelimiter();

    String getResultingString();
}
