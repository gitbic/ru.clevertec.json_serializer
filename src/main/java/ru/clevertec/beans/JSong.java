package ru.clevertec.beans;

import ru.clevertec.constants.Constant;
import ru.clevertec.constants.Format;
import ru.clevertec.constants.RegEx;

import java.lang.reflect.Field;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

public class JSong {
    private Object o;

    public JSong(Object o) {
        this.o = o;
    }

    public Object getO() {
        return o;
    }

    public void setO(Object o) {
        this.o = o;
    }

    public String serialize() throws IllegalAccessException {
        Field[] fields = o.getClass().getDeclaredFields();

        CustomStringJoiner mainJsonBuilder = new CustomStringJoiner(
                Constant.COMMA,
                Constant.BRACE_LEFT,
                Constant.BRACE_RIGHT);

        for (Field field : fields) {
            field.setAccessible(true);

            String fieldName = String.format(Format.DOUBLE_QUOTE_FIELD, field.getName());

            Object oFieldValue = field.get(o);
            String fieldValue;

            if (oFieldValue == null) {
                fieldValue = "null";
            } else {
                fieldValue = oFieldValue.toString();

                Class<?> fieldType = field.getType();

                if (fieldType != int.class
                        && fieldType != double.class
                        && fieldType != short.class
                        && fieldType != long.class
                        && fieldType != boolean.class
                        && fieldType != Boolean.class
                        && !Number.class.isAssignableFrom(fieldType)) {
                    fieldValue = String.format(Format.DOUBLE_QUOTE_VALUE, fieldValue);
                }
            }

            mainJsonBuilder.append(fieldName)
                    .append(Constant.COLON)
                    .append(fieldValue);

            field.setAccessible(false);
        }
    return mainJsonBuilder.toString();
//        System.out.println(mainJsonBuilder.toString());
    }


}
