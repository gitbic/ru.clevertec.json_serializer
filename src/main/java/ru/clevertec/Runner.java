package ru.clevertec;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import ru.clevertec.beans.JSong;
import ru.clevertec.testclasses.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Runner {
    public static void main(String[] args) throws IllegalAccessException {


        Student student = createStudentObject();

        System.out.println("-----------Custom json serializer------------");
        JSong jSong = new JSong(student);
        String jSongSimpleString = jSong.serialize();
        System.out.println(jSongSimpleString);
        System.out.println();

        System.out.println("-----------Gogle json serializer------------");
        Gson gson = new Gson();
        String gsonSimpleString = gson.toJson(student);
        System.out.println(gsonSimpleString);

        System.out.println("-----------Custom json serializer------------");
        jSong.setPrettyString(true);
        String jSongPrettyString = jSong.serialize();
        System.out.println(jSongPrettyString);

        System.out.println("-----------Gogle json serializer------------");
        gson = gson.newBuilder().setPrettyPrinting().create();
        String gsonPrettyString = gson.toJson(student);
        System.out.println(gsonPrettyString);

        //--------------------------------------------------
        System.out.println("-----------Custom json serialization multi-level object------------");
        SomeTypes someTypes = new SomeTypes();
        jSongPrettyString = jSong.setProcessedObject(someTypes).serialize();
        System.out.println(jSongPrettyString);

    }

    public static Student createStudentObject() {

        PhysData physData = new PhysData(18, 70.4, 175.2, Sex.MALE);

        List<String> specs = new ArrayList<>();
        specs.add("engineer");
        specs.add("constructor");
        specs.add("master-flomaster");

        LanguageKnowledge[] languageKnowledges = {
                new LanguageKnowledge("Russian", 8, KnowledgeRate.EXCELLENT),
                new LanguageKnowledge("Eanglish", 7, KnowledgeRate.GOOD),
                new LanguageKnowledge("Italian", 1, KnowledgeRate.BAD)
        };

        Map<String, Boolean> passedTests = new HashMap<>();
        passedTests.put("math", true);
        passedTests.put("physics", false);
        passedTests.put("sopromat", true);

        return new Student("Alex", 152, specs, languageKnowledges, passedTests, physData);
    }
}
