package ru.clevertec.testclasses;

import java.util.*;

public class SomeTypes {
//    public byte byteValue = 1;
//    public int intValue = 147;
//    public Integer wrapperIntValue = 2642;
//    public double doubleValue = 750193.28104;
//    public Double wrapperDoubleValue = 823.10284;
//    public Integer wrapperIntNull = null;
//    public Sex oEnum = Sex.MALE;
//    public boolean primitiveBool = true;
//    public Boolean wrapperBool = false;
//    public String string = "Hello";
//    public char primitiveChar = 'c';
//    public Character wrapperChar = 'v';

//    public Integer[] integerArray = {1, null, 3};
//    public int[] intArray = {1, 2, 3};
//    public String[] strArray = {null, "two", "three"};
//    public List<Integer> list = new ArrayList<>(Arrays.asList(null, 2, 3));
//    public List<String> list1 = new ArrayList<>(Arrays.asList("one", null, "three"));
//    public List<Boolean> list3 = new ArrayList<>(Arrays.asList(true, false, null));
//public List<int[]> list = new ArrayList<>(Arrays.asList(intArray, intArray, intArray));

        public Map<Integer, String> map = Map.of(1, "value1", 2, "value2");
    public Map<String, Integer> map1 = Map.of("key1", 11, "key2", 22);
    public Map<Integer, Integer> map2 = Map.of(1, 10, 2, 20);

    PhysiologicalData physiologicalData =
            new PhysiologicalData(18, 70.4, 175.2, Sex.MALE);


}
