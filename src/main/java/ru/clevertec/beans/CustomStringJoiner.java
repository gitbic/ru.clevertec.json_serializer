package ru.clevertec.beans;

public class CustomStringJoiner {
    private final StringBuilder stringBuilder;
    private final String delimiter;
    private final String prefix;
    private final String suffix;

    public CustomStringJoiner(String delimiter, String prefix, String suffix) {
        stringBuilder = new StringBuilder();
        this.delimiter = delimiter;
        this.prefix = prefix;
        this.suffix = suffix;
    }

    public StringBuilder append(String string) {
        addDelimiter();
        stringBuilder.append(string);
        return stringBuilder;
    }

    public StringBuilder append(StringBuilder sb) {
        addDelimiter();
        stringBuilder.append(sb);
        return stringBuilder;
    }

    @Override
    public String toString() {
        return prefix + stringBuilder.toString() + suffix;
    }

    private void addDelimiter() {
        if (stringBuilder.length() > 0) {
            stringBuilder.append(delimiter);
        }
    }
}
