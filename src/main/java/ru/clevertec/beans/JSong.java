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

    public JSong setProcessedObject(Object processedObject) {
        this.processedObject = processedObject;
        return this;
    }

    public String serialize() throws IllegalAccessException {
        return serializeDifferentObject(this.processedObject).toString();
    }

    private StringBuilder serializeCollection(Object currentObject) throws IllegalAccessException {

        CustomStringJoiner jsonBuilder = CustomStringJoinerFactory.createInstance(
                StringJoinerSet.BRACKET, prettyString);

        Collection<?> collection = (Collection<?>) currentObject;

        for (Object collectionElement : collection) {
            StringBuilder fieldValue = serializeDifferentObject(collectionElement);
            jsonBuilder.append(fieldValue);
        }

        return jsonBuilder.getResultingString();
    }

    private StringBuilder serializeArray(Object currentObject) throws IllegalAccessException {

        CustomStringJoiner jsonBuilder = CustomStringJoinerFactory.createInstance(
                StringJoinerSet.BRACKET, prettyString);

        int arrayLength = Array.getLength(currentObject);

        for (int i = 0; i < arrayLength; i++) {
            Object arrayElement = Array.get(currentObject, i);
            StringBuilder fieldValue = serializeDifferentObject(arrayElement);
            jsonBuilder.append(fieldValue);
        }
        return jsonBuilder.getResultingString();
    }

    private StringBuilder serializeMap(Object currentObject) throws IllegalAccessException {

        CustomStringJoiner jsonBuilder = CustomStringJoinerFactory.createInstance(
                StringJoinerSet.BRACE, prettyString);

        Map<?, ?> map = (Map<?, ?>) currentObject;

        for (Map.Entry<?, ?> entry : map.entrySet()) {

            String fieldName = String.format(Format.DOUBLE_QUOTE_FIELD, entry.getKey());
            StringBuilder fieldValue = serializeDifferentObject(entry.getValue());

            jsonBuilder.append(fieldName)
                    .append(getColon())
                    .append(fieldValue);
        }

        return jsonBuilder.getResultingString();
    }


    private StringBuilder serializeObject(Object currentObject) throws IllegalAccessException {
        Field[] fields = currentObject.getClass().getDeclaredFields();

        CustomStringJoiner jsonBuilder = CustomStringJoinerFactory.createInstance(
                StringJoinerSet.BRACE, prettyString);

        for (Field field : fields) {
            field.setAccessible(true);

            String fieldName = String.format(Format.DOUBLE_QUOTE_FIELD, field.getName());

            Object fieldElement = field.get(currentObject);

            StringBuilder fieldValue = serializeDifferentObject(fieldElement);

            jsonBuilder.append(fieldName)
                    .append(getColon())
                    .append(fieldValue);

            field.setAccessible(false);
        }
        return jsonBuilder.getResultingString();
    }

    private String getColon() {
        return prettyString
                ? Constant.PRETTY_COLON
                : Constant.COLON;
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
                && fieldType != Boolean.class
                && fieldType != Character.class;
    }

    private StringBuilder serializeDifferentObject(Object object) throws IllegalAccessException {
        StringBuilder value = new StringBuilder();

        if (object == null) {

            value.append(Constant.NULL_STR);

        } else if (object.getClass().isArray()) {

            value = serializeArray(object);

        } else if (Collection.class.isAssignableFrom(object.getClass())) {

            value = serializeCollection(object);

        } else if (Map.class.isAssignableFrom(object.getClass())) {

            value = serializeMap(object);

        } else if (isCustomObject(object.getClass())) {

            value = serializeObject(object);

        } else {

            String strValue = object.toString();

            if (isValueNeedDoubleQuote(object.getClass())) {
                value.append(String.format(Format.DOUBLE_QUOTE_VALUE, strValue));
            } else {
                value.append(strValue);
            }
        }

        return value;
    }

}
