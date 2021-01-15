package ru.clevertec.beans;

import ru.clevertec.constants.Constant;
import ru.clevertec.constants.Format;
import ru.clevertec.enums.StringJoinerSet;
import ru.clevertec.factories.CustomStringJoinerFactory;
import ru.clevertec.interfaces.CustomStringJoiner;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;

public class JSong {
    private Object processedObject;
    private boolean prettyString;


    public JSong() {
    }

    public JSong(Object processedObject) {
        this.processedObject = processedObject;
    }

    public JSong(Object processedObject, boolean prettyString) {
        this.processedObject = processedObject;
        this.prettyString = prettyString;
    }

    public boolean isPrettyString() {
        return prettyString;
    }

    public void setPrettyString(boolean prettyString) {
        this.prettyString = prettyString;
    }

    public Object getProcessedObject() {
        return processedObject;
    }

    public void setProcessedObject(Object processedObject) {
        this.processedObject = processedObject;
    }

    public String serialize() throws IllegalAccessException {
        return serializeObject(this.processedObject);
    }

    private String serializeCollection(Object currentObject) throws IllegalAccessException {

        CustomStringJoiner mainJsonBuilder = CustomStringJoinerFactory.createInstance(
                StringJoinerSet.BRACKET, prettyString);

        Collection<?> collection = (Collection<?>) currentObject;

        for (Object collectionElement : collection) {
            String fieldValue = serializeDifferentObject(collectionElement);
            mainJsonBuilder.append(fieldValue);
        }
        return mainJsonBuilder.getResultingString();
    }

    private String serializeArray(Object currentObject) throws IllegalAccessException {

        CustomStringJoiner mainJsonBuilder = CustomStringJoinerFactory.createInstance(
                StringJoinerSet.BRACKET, prettyString);

        int arrayLength = Array.getLength(currentObject);

        for (int i = 0; i < arrayLength; i++) {
            Object arrayElement = Array.get(currentObject, i);
            String fieldValue = serializeDifferentObject(arrayElement);
            mainJsonBuilder.append(fieldValue);
        }
        return mainJsonBuilder.getResultingString();
    }

    private String serializeMap(Object currentObject) throws IllegalAccessException {

        CustomStringJoiner mainJsonBuilder = CustomStringJoinerFactory.createInstance(
                StringJoinerSet.BRACE, prettyString);

        Map<?, ?> map = (Map<?, ?>) currentObject;

        for (Map.Entry<?, ?> entry : map.entrySet()) {

            String fieldName = String.format(Format.DOUBLE_QUOTE_FIELD, entry.getKey());
            String fieldValue = serializeDifferentObject(entry.getValue());

            mainJsonBuilder.append(fieldName)
                    .append(Constant.COLON)
                    .append(fieldValue);
        }
        return mainJsonBuilder.getResultingString();
    }


    private String serializeObject(Object currentObject) throws IllegalAccessException {
        Field[] fields = currentObject.getClass().getDeclaredFields();

        CustomStringJoiner mainJsonBuilder = CustomStringJoinerFactory.createInstance(
                StringJoinerSet.BRACE, prettyString);

        for (Field field : fields) {
            field.setAccessible(true);

            String fieldName = String.format(Format.DOUBLE_QUOTE_FIELD, field.getName());

            Object fieldElement = field.get(currentObject);

            String fieldValue = serializeDifferentObject(fieldElement);

            mainJsonBuilder.append(fieldName)
                    .append(Constant.COLON)
                    .append(fieldValue);

            field.setAccessible(false);
        }
        return mainJsonBuilder.getResultingString();
    }

    private static boolean isValueNeedDoubleQuote(Class<?> fieldType) {
        return (!fieldType.isPrimitive() || fieldType == char.class)
                && !Number.class.isAssignableFrom(fieldType)
                && fieldType != Boolean.class;
    }

    private static boolean isCustomObject(Class<?> fieldType) {
        return !fieldType.isPrimitive()
                && !Number.class.isAssignableFrom(fieldType)
                && !Collection.class.isAssignableFrom(fieldType)
                && !Enum.class.isAssignableFrom(fieldType)
                && fieldType != Array.class
                && fieldType != String.class
                && fieldType != StringBuffer.class
                && fieldType != Boolean.class;
    }

    private String serializeDifferentObject(Object object) throws IllegalAccessException {
        String value = null;

        if (object == null) {

            value = Constant.NULL_STR;

        } else if (object.getClass().isArray()) {

            value = serializeArray(object);

        } else if (Collection.class.isAssignableFrom(object.getClass())) {

            value = serializeCollection(object);

        } else if (Map.class.isAssignableFrom(object.getClass())) {

            value = serializeMap(object);

        } else if (isCustomObject(object.getClass())) {

            value = serializeObject(object);

        } else {

            value = object.toString();

            if (isValueNeedDoubleQuote(object.getClass())) {
                value = String.format(Format.DOUBLE_QUOTE_VALUE, value);
            }
        }

        return value;
    }

}
