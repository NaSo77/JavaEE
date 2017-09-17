package gsonLibrary;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import com.google.gson.Gson;

import objects.Car;

public class Demo {
    public static void main(String[] args) {
	Gson gson = new Gson();
	Car car = new Car("Ford", 2000, "Black");

	String json = gson.toJson(car);
	File file = new File("object.json");
	
	writeToFile(file, json);

	String readJsonFromFile = readFromFile(file);
	Car car1 = gson.fromJson(readJsonFromFile, Car.class);
	System.out.println(car1);

    }

    private static String readFromFile(File file) {
	StringBuilder sb = new StringBuilder();
	if (fileIsValid(file)) {
	    try (Scanner sc = new Scanner(file);) {
		while (sc.hasNextLine()) {
		    sb.append(sc.nextLine());
		}
	    } catch (FileNotFoundException e) {
		System.out.println("File not found");
	    }
	}
	System.out.println("Successfully read");
	return sb.toString();
    }

    public static void writeToFile(File file, String text) {
	if (fileIsValid(file)) {
	    try (PrintWriter pw = new PrintWriter(file);) {
		pw.println(text);
	    } catch (FileNotFoundException e) {
		System.out.println("File not found");
	    }
	}
	System.out.println("Successfully written");
    }

    private static boolean fileIsValid(File file) {
	if (file == null) {
	    System.out.println("file cannot be null");
	    return false;
	}

	if (!file.exists()) {
	    try {
		file.createNewFile();
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}

	return true;
    }
}
