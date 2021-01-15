package ru.clevertec.beans;

import ru.clevertec.constants.Constant;
import ru.clevertec.constants.Format;
import ru.clevertec.factories.CustomStringJoinerFactory;
import ru.clevertec.interfaces.CustomStringJoiner;
import ru.clevertec.testclasses.PhysiologicalData;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;
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

    private String serializeCollection(Field field) throws IllegalAccessException {

        CustomStringJoiner mainJsonBuilder = CustomStringJoinerFactory.createInstance(
                Constant.COMMA,
                Constant.BRACKET_LEFT,
                Constant.BRACKET_RIGHT,
                prettyString);

        boolean valueNeedDoubleQuote = isValueNeedDoubleQuote(
                (Class<?>) ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0]);

        Collection<?> collection = (Collection<?>) field.get(processedObject);

        for (Object oFieldValue : collection) {

            String fieldValue;

            if (oFieldValue == null) {
                fieldValue = Constant.NULL_STR;
            } else {
                fieldValue = oFieldValue.toString();

                if (valueNeedDoubleQuote) {
                    fieldValue = String.format(Format.DOUBLE_QUOTE_VALUE, fieldValue);
                }
            }
            mainJsonBuilder.append(fieldValue);
        }
        return mainJsonBuilder.getResultingString();
    }

    private String serializeArray(Field field) throws IllegalAccessException {

        CustomStringJoiner mainJsonBuilder = CustomStringJoinerFactory.createInstance(
                Constant.COMMA,
                Constant.BRACKET_LEFT,
                Constant.BRACKET_RIGHT,
                prettyString);

        Object objectArray = field.get(processedObject);

        boolean valueNeedDoubleQuote = isValueNeedDoubleQuote(field.getType().getComponentType());
        int arrayLength = Array.getLength(objectArray);

        for (int i = 0; i < arrayLength; i++) {

            Object oFieldValue = Array.get(objectArray, i);
            String fieldValue;

            if (oFieldValue == null) {
                fieldValue = Constant.NULL_STR;
            } else {
                fieldValue = oFieldValue.toString();

                if (valueNeedDoubleQuote) {
                    fieldValue = String.format(Format.DOUBLE_QUOTE_VALUE, fieldValue);
                }
            }
            mainJsonBuilder.append(fieldValue);
        }
        return mainJsonBuilder.getResultingString();
    }

    public String serialize() throws IllegalAccessException {
        return serializeObject(this.processedObject);
    }


    public String serializeMap(Field field) throws IllegalAccessException {
        CustomStringJoiner mainJsonBuilder = CustomStringJoinerFactory.createInstance(
                Constant.COMMA,
                Constant.BRACE_LEFT,
                Constant.BRACE_RIGHT,
                prettyString);

        boolean valueNeedDoubleQuote = isValueNeedDoubleQuote(
                (Class<?>) ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[1]);

//        Collection<?> collection = (Collection<?>) field.get(processedObject);
        Map<?, ?> map = (Map<?,?>) field.get(processedObject);

        for (Map.Entry<?, ?> entry : map.entrySet()) {
            String fieldName = String.format(Format.DOUBLE_QUOTE_FIELD, entry.getKey());


            String fieldValue = entry.getValue().toString();

            if (valueNeedDoubleQuote) {
                fieldValue = String.format(Format.DOUBLE_QUOTE_VALUE, fieldValue);
            }

            mainJsonBuilder.append(fieldName)
                    .append(Constant.COLON)
                    .append(fieldValue);
        }

        return mainJsonBuilder.getResultingString();

    }


    public String serializeObject(Object Obj) throws IllegalAccessException {
        Field[] fields = Obj.getClass().getDeclaredFields();

        CustomStringJoiner mainJsonBuilder = CustomStringJoinerFactory.createInstance(
                Constant.COMMA,
                Constant.BRACE_LEFT,
                Constant.BRACE_RIGHT,
                prettyString);

        for (Field field : fields) {
            field.setAccessible(true);

            String fieldName = String.format(Format.DOUBLE_QUOTE_FIELD, field.getName());

            Object oFieldValue = field.get(Obj);
            Class<?> fieldType = field.getType();
            String fieldValue;

            if (oFieldValue == null) {
                fieldValue = Constant.NULL_STR;

            } else if (fieldType.isArray()) {

                fieldValue = serializeArray(field);

            } else if (Collection.class.isAssignableFrom(fieldType)) {

                fieldValue = serializeCollection(field);

            } else if (Map.class.isAssignableFrom(fieldType)) {

//                ObjectMapper mapper = new ObjectMapper();

                fieldValue = serializeMap(field);

            }else if (isObjectCustom(fieldType)){

                fieldValue = serializeObject(oFieldValue);

            } else {

                fieldValue = oFieldValue.toString();

                if (isValueNeedDoubleQuote(fieldType)) {
                    fieldValue = String.format(Format.DOUBLE_QUOTE_VALUE, fieldValue);
                }
            }

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

//    private static boolean isFieldIsCollectionOrArray(Field field) {
//        return (field.getType().isArray()
//                || Collection.class.isAssignableFrom(field.getType()));
//    }

    private static boolean isObjectCustom(Class<?> fieldType) {

        return !fieldType.isPrimitive()
                && !Number.class.isAssignableFrom(fieldType)
                && !Collection.class.isAssignableFrom(fieldType)
                && !Enum.class.isAssignableFrom(fieldType)
                && fieldType != Array.class
                && fieldType != String.class
                && fieldType != StringBuffer.class;
    }
}
