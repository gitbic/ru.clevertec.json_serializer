package ru.clevertec.beans;

import ru.clevertec.constants.Constant;
import ru.clevertec.interfaces.CustomStringJoiner;

public class SimpleStringJoiner implements CustomStringJoiner {
    private final StringBuilder stringBuilder;
    private final String delimiter;
    private final String prefix;
    private final String suffix;

    public SimpleStringJoiner(String delimiter, String prefix, String suffix) {
        stringBuilder = new StringBuilder();
        this.delimiter = delimiter;
        this.prefix = prefix;
        this.suffix = suffix;
    }

    @Override
    public StringBuilder append(String string) {
        addDelimiter();
        stringBuilder.append(string);
        return stringBuilder;
    }

    @Override
    public String toString() {
        return prefix + stringBuilder.toString() + suffix;
    }

    @Override
    public void addDelimiter() {
        if (stringBuilder.length() > Constant.STRING_BUILDER_ZERO_LENGTH) {
            stringBuilder.append(delimiter);
        }
    }

    @Override
    public String getResultingString() {
        return toString();
    }
}
