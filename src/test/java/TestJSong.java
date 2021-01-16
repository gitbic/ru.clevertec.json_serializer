import com.google.gson.Gson;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.clevertec.beans.JSong;
import ru.clevertec.testclasses.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class TestJSong {
    Student student;
    Gson gson;
    JSong jSong;

    @BeforeEach
    public void createObjectForTest() {

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

        student = new Student("Alex", 152, specs, languageKnowledges, passedTests, physData);
    }

    @BeforeEach
    public void initCustomAndGoogleJsonLibrary() {
        gson = new Gson();
        jSong = new JSong();
    }

    @AfterEach
    public void releaseResources() {
        student = null;
        gson = null;
        jSong = null;
    }

    @Test
    public void TestSerializedObjectToCustomJsonSimpleString() throws IllegalAccessException {

        String jSongSimpleString = jSong.setProcessedObject(student).serialize();
        assertNotSame("", jSongSimpleString);
    }

    @Test
    public void TestSerializeObjectToCustomJsonPrettyString() throws IllegalAccessException {

        jSong.setPrettyString(true);
        jSong.setProcessedObject(student);
        String jSongPrettyString = jSong.serialize();
        assertNotSame("", jSongPrettyString);
    }

    @Test
    public void TestCompareGsonToCustomJsonSimpleString() throws IllegalAccessException {

        String jSongSimpleString = jSong.setProcessedObject(student).serialize();
        String gsonSimpleString = gson.toJson(student);

        assertEquals(gsonSimpleString, jSongSimpleString);
    }

    @Test
    public void TestCompareGsonToCustomJsonPrettyString() throws IllegalAccessException {

        jSong.setPrettyString(true);
        jSong.setProcessedObject(student);
        String jSongPrettyString = jSong.serialize();

        gson = gson.newBuilder().setPrettyPrinting().create();
        String gsonPrettyString = gson.toJson(student);

        assertEquals(gsonPrettyString, jSongPrettyString);
    }

    @Test
    public void TestCompareObjectToDeserializedObjectFromCustomJsonSimpleString() throws IllegalAccessException {

        String jSongSimpleString = jSong.setProcessedObject(student).serialize();
        Student deserializedStudent = gson.fromJson(jSongSimpleString, Student.class);
        assertEquals(student, deserializedStudent);
    }

    @Test
    public void TestCompareObjectToDeserializedObjectFromCustomJsonPrettyString() throws IllegalAccessException {

        jSong.setPrettyString(true);
        jSong.setProcessedObject(student);
        String jSongPrettyString = jSong.serialize();

        Student deserializedStudent = gson.fromJson(jSongPrettyString, Student.class);
        assertEquals(student, deserializedStudent);
    }
}
