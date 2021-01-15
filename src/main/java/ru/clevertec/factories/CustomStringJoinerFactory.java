package ru.clevertec.factories;

import ru.clevertec.beans.PrettyStringJoiner;
import ru.clevertec.beans.SimpleStringJoiner;
import ru.clevertec.enums.StringJoinerSet;
import ru.clevertec.interfaces.CustomStringJoiner;

public class CustomStringJoinerFactory {
    public static CustomStringJoiner createInstance(StringJoinerSet set, boolean prettyString) {

        if (prettyString) {
            return new PrettyStringJoiner(set.getComma(), set.getLeftBrace(), set.getRightBrace());
        } else {
            return new SimpleStringJoiner(set.getComma(), set.getLeftBrace(), set.getRightBrace());
        }
    }
}
