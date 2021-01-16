package ru.clevertec.beans;

import ru.clevertec.constants.Constant;
import ru.clevertec.interfaces.CustomStringJoiner;

public class PrettyStringJoiner implements CustomStringJoiner {
    public static int objectLevel = Constant.ZERO_OBJECT_LEVEL;
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
                + Constant.LINE_SEPARATOR
                + Constant.INDENT.repeat(objectLevel)
                + stringBuilder.toString()
                + Constant.LINE_SEPARATOR
                + Constant.INDENT.repeat(objectLevel - Constant.ONE_OBJECT_LEVEL)
                + suffix;
    }

    @Override
    public void addDelimiter() {
        if (stringBuilder.length() > Constant.STRING_BUILDER_ZERO_LENGTH) {
            stringBuilder.append(delimiter)
                    .append(Constant.LINE_SEPARATOR)
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
