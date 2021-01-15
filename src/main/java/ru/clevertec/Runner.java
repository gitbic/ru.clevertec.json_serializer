package ru.clevertec;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.clevertec.beans.JSong;
import ru.clevertec.testclasses.PhysiologicalData;
import ru.clevertec.testclasses.Sex;
import ru.clevertec.testclasses.SomeTypes;
import ru.clevertec.testclasses.Student;

import java.util.*;

public class Runner {
    public static void main(String[] args) {


        Gson gsonFormatted = new GsonBuilder().setPrettyPrinting().create();
        Gson gsonString = new Gson();


        SomeTypes someTypes = new SomeTypes();



        JSong jSong = new JSong(someTypes);
        jSong.setPrettyString(true);
        String stringJSong = "";

        try{
            stringJSong = jSong.serialize();
        } catch (Exception e) {
            System.out.println(e);
        }

        System.out.println(gsonFormatted.toJson(someTypes));
        System.out.println(stringJSong);

//        SomeTypes st = gsonString.fromJson(stringJSong, SomeTypes.class);
//        System.out.println(Arrays.toString(st.integerArray));

        //--
//        for (Map.Entry<Integer, String> integerStringEntry : st.map.entrySet()) {
//            System.out.println(integerStringEntry.getKey());
//            System.out.println(integerStringEntry.getValue());
//        }










        PhysiologicalData physiologicalData =
                new PhysiologicalData(18, 70.4, 175.2, Sex.MALE);
//
//        JSong jSong = new JSong(physiologicalData);
//        jSong.setPrettyString(true);
//
//        String customPhysData = "";
//        try {
//            customPhysData = jSong.serializeObject();
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//
//// get simple string phisData
//        String physData = gsonFormatted.toJson(physiologicalData);
//        System.out.println(physData);
//        System.out.println(customPhysData);

// test custom Json serialize
//        PhysiologicalData pd = gsonString.fromJson(physData, PhysiologicalData.class);
//        PhysiologicalData pd = gsonString.fromJson(customPhysData, PhysiologicalData.class);
//        Sex s = pd.getSex();
//        System.out.println(s);



        List<String> specs = new ArrayList<>();
        specs.add("engineer");
        specs.add("constructor");
        specs.add("master-flomaster");

        String[] languages = {"Russian", "Eanglish", "Italian"};

        Map<String, Boolean> passedTests = new HashMap<>();
        passedTests.put("math", true);
        passedTests.put("physics", false);
        passedTests.put("sopromat", true);

        Student student = new Student("Alex", 152, specs, languages, passedTests, physiologicalData);

// get formatted json string from object Student
//        String studentJson = gsonFormatted.toJson(student);
//        System.out.println(studentJson);

// get object Student from string with saved condition
//        Student studentFromJson = gsonFormatted.fromJson(studentJson, Student.class);


    }
}
