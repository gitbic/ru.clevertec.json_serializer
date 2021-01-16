package ru.clevertec.testclasses;

import java.math.BigDecimal;
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
//    public Boolean[] booleanArray = {true, false, null};


//    public List<String> list1 = new ArrayList<>(Arrays.asList("one", null, "three"));
//    public List<Boolean> list3 = List.of(true, false);
//public List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3));


//        public Map<Integer, String> map = Map.of(1, "value1", 2, "value2");
//    public Map<String, Integer> map1 = Map.of("key1", 11, "key2", 22);
//    public Map<Integer, Integer> map2 = Map.of(1, 10, 2, 20);
//    public Map<String, String> map3 = Map.of("key1", "11", "key2", "22");

//    PhysData physiologicalData =
//            new PhysData(18, 70.4, 175.2, Sex.MALE);

//    BigDecimal bigDecimal = new BigDecimal("45").add(new BigDecimal("6"));
//
//    List<PhysiologicalData> list = new ArrayList<>(Arrays.asList(physiologicalData, physiologicalData));
//
//    Set<Integer> set = new HashSet<>(Set.of(1, 2, 3));


    public List<Box> boxList = new ArrayList<>(Arrays.asList(new Box(), new Box()));
    public List<List<Box>> listOfLists = new ArrayList<>(Arrays.asList(boxList, boxList));

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SomeTypes someTypes = (SomeTypes) o;
        return boxList.equals(someTypes.boxList) &&
                listOfLists.equals(someTypes.listOfLists);
    }

    @Override
    public int hashCode() {
        return Objects.hash(boxList, listOfLists);
    }



}


class Box{
    String s = "str";
    int i = 1;
    public List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3));
    public List<List<Integer>> listOfLists = new ArrayList<>(Arrays.asList(list, list));

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Box box = (Box) o;
        return i == box.i &&
                Objects.equals(s, box.s) &&
                Objects.equals(list, box.list) &&
                Objects.equals(listOfLists, box.listOfLists);
    }

    @Override
    public int hashCode() {
        return Objects.hash(s, i, list, listOfLists);
    }
}
