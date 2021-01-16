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
    public StringBuilder append(StringBuilder string) {
        addDelimiter();
        stringBuilder.append(string);
        return stringBuilder;
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
    public void createResultingString() {
        stringBuilder.insert(Constant.STRING_BUILDER_ZERO_POSITION, Constant.INDENT.repeat(objectLevel))
                .insert(Constant.STRING_BUILDER_ZERO_POSITION, Constant.LINE_SEPARATOR)
                .insert(Constant.STRING_BUILDER_ZERO_POSITION, prefix)
                .append(Constant.LINE_SEPARATOR)
                .append(Constant.INDENT.repeat(objectLevel - Constant.ONE_OBJECT_LEVEL))
                .append(suffix);
    }

    @Override
    public StringBuilder getResultingString() {
        createResultingString();
        objectLevel--;
        return stringBuilder;
    }
}
