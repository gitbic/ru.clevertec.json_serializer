package ru.clevertec.enums;

import ru.clevertec.constants.Constant;

public enum StringJoinerSet {
    BRACE(Constant.COMMA, Constant.BRACE_LEFT, Constant.BRACE_RIGHT),
    BRACKET(Constant.COMMA, Constant.BRACKET_LEFT, Constant.BRACKET_RIGHT);

    String comma;
    String leftBrace;
    String rightBrace;

    StringJoinerSet(String comma, String leftBrace, String rightBrace) {
        this.comma = comma;
        this.leftBrace = leftBrace;
        this.rightBrace = rightBrace;
    }

    public String getComma() {
        return comma;
    }

    public String getLeftBrace() {
        return leftBrace;
    }

    public String getRightBrace() {
        return rightBrace;
    }
}
