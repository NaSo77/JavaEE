package gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Demo {
    public static void main(String[] args) {

	// http://tutorials.jenkov.com/java-json/gson.html

	ArrayList<String> words = new ArrayList<>();
	words.add("My name is Hristo");
	words.add("I am practising json format");
	words.add("I want to be an java developer");

	Human human = new Human("Hristo", 31, true, words);
	File gson = new File(".\\src\\gson\\", "human.json");

	toJsonExample(human, gson);
	JsonParserExample(gson);


    }

    private static void JsonParserExample(File gson) {
	JsonParser parser = new JsonParser();
	JsonElement element = parser.parse(readFromJsonFile(gson));

	JsonObject jsonObject = element.getAsJsonObject();

	System.out.println("Human name: " + jsonObject.get("name").getAsString());
	System.out.println("Human age: " + jsonObject.get("age").getAsInt());
	System.out.println("Human is awesome : " + jsonObject.get("isАwesome").getAsBoolean());
	System.out.println("Human words: ");

	JsonArray arr = jsonObject.get("words").getAsJsonArray();

	for (JsonElement jsonElement : arr) {
	    System.out.print(jsonElement.getAsString() + " ");
	}
    }

    public static String readFromJsonFile(File file) {
	StringBuilder sb = new StringBuilder();
	if (isFileValid(file)) {
	    try (Scanner sc = new Scanner(file);) {
		while (sc.hasNextLine()) {
		    sb.append(sc.nextLine());
		}
	    } catch (FileNotFoundException e) {
		System.out.println("File not found.");
	    }
	} else {
	    System.out.println("Invalid file.");
	}
	return sb.toString();
    }

    public static boolean isFileValid(File file) {
	if (file == null) {
	    System.out.println("Invalid file");
	    return false;
	}
	if (!file.exists()) {
	    try {
		file.createNewFile();
	    } catch (IOException e) {
		System.out.println("The file already exists.");
	    }
	}
	return true;
    }

    private static void toJsonExample(Human human, File testFile) {
	Gson gson = new GsonBuilder().setPrettyPrinting().create();
	String jsonObject = gson.toJson(human);

	PrintWriter pw = null;
	try {
	    pw = new PrintWriter(testFile);
	    pw.println(jsonObject);
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	} finally {
	    pw.close();
	}

    }
}
