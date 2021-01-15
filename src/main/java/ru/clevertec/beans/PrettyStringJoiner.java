package ru.clevertec.beans;

import ru.clevertec.constants.Constant;
import ru.clevertec.interfaces.CustomStringJoiner;

import java.util.Collection;

public class PrettyStringJoiner implements CustomStringJoiner {
    public static int objectLevel = 0;
    private final StringBuilder stringBuilder;
    private final String delimiter;
    private final String prefix;
    private final String suffix;

    public PrettyStringJoiner(String delimiter, String prefix, String suffix) {
        objectLevel++;
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
        return prefix
                + System.lineSeparator()
                + Constant.INDENT.repeat(objectLevel)
                + stringBuilder.toString()
                + System.lineSeparator()
                + Constant.INDENT.repeat(objectLevel - 1)
                + suffix;
    }

    @Override
    public void addDelimiter() {
        if (stringBuilder.length() > 0) {
            stringBuilder.append(delimiter)
                    .append(System.lineSeparator())
                    .append(Constant.INDENT.repeat(objectLevel));
        }
    }

    @Override
    public String getResultingString() {
        String str = toString();
        objectLevel--;
        return str;
    }
}
