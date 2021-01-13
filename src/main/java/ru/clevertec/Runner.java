package ru.clevertec;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.util.ISO8601Utils;
import ru.clevertec.beans.JSong;
import ru.clevertec.testclasses.PhysiologicalData;
import ru.clevertec.testclasses.Sex;
import ru.clevertec.testclasses.Student;

import java.util.*;

public class Runner {
    public static void main(String[] args) {


        Gson gsonFormatted = new GsonBuilder().setPrettyPrinting().create();
        Gson gsonString = new Gson();

        PhysiologicalData physiologicalData =
                new PhysiologicalData(18, 70.4, 175.2, Sex.MALE);

//        JSong jSong = new JSong(physiologicalData);
//
//        String customPhysData = "";
//        try {
//            customPhysData = jSong.serialize();
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//
//// get simple string phisData
//        String physData = gsonString.toJson(physiologicalData);
////        System.out.println(physData);
//
//// test custom Json serialize
////        PhysiologicalData pd = gsonString.fromJson(physData, PhysiologicalData.class);
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
