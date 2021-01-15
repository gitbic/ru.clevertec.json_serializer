package ru.clevertec.factories;

import ru.clevertec.beans.PrettyStringJoiner;
import ru.clevertec.beans.SimpleStringJoiner;
import ru.clevertec.interfaces.CustomStringJoiner;

public class CustomStringJoinerFactory {
    public static CustomStringJoiner createInstance(String delimiter,
                                             String prefix,
                                             String suffix,
                                             boolean prettyString) {

        if (prettyString) {
            return new PrettyStringJoiner(delimiter, prefix, suffix);
        } else {
            return new SimpleStringJoiner(delimiter, prefix, suffix);
        }
    }
}
