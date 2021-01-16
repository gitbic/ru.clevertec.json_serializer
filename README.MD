**Custom Json serializer**

This tool uses java reflection for serialize multi-level object to simple or pretty string.

This string can be deserialized using another tool.

**Examples**

Test class contain multi-level class Student;

Lets try to serialize object, and create simple and pretty Json string.

* Simple Json string

```java
JSong jSong = new JSong(student);
String jSongSimpleString = jSong.serialize();
System.out.println(jSongSimpleString);
		
{"name":"Alex","stipend":152.0,"specs":["engineer","constructor","master-flomaster"],"languageKnowledges":[{"language":"Russian","reading":8,"knowledgeRate":"EXCELLENT"},{"language":"Eanglish","reading":7,"knowledgeRate":"GOOD"},{"language":"Italian","reading":1,"knowledgeRate":"BAD"}],"passedTests":{"physics":false,"math":true,"sopromat":true},"physiologicalData":{"age":18,"weight":70.4,"height":175.2,"sex":"MALE"}}		
```

* Pretty Json string

```java
jSong.setPrettyString(true);
String jSongPrettyString = jSong.serialize();

{
  "name": "Alex",
  "stipend": 152.0,
  "specs": [
    "engineer",
    "constructor",
    "master-flomaster"
  ],
  "languageKnowledges": [
    {
      "language": "Russian",
      "reading": 8,
      "knowledgeRate": "EXCELLENT"
    },
    {
      "language": "Eanglish",
      "reading": 7,
      "knowledgeRate": "GOOD"
    },
    {
      "language": "Italian",
      "reading": 1,
      "knowledgeRate": "BAD"
    }
  ],
  "passedTests": {
    "physics": false,
    "math": true,
    "sopromat": true
  },
  "physiologicalData": {
    "age": 18,
    "weight": 70.4,
    "height": 175.2,
    "sex": "MALE"
  }
}
```

