package xStreamLibrary;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import objects.Car;

public class Demo {
    public static void main(String[] args) {
	Car car = new Car("Ford", 2000, "Black");
	StaxDriver staxDriver = new StaxDriver(); // parser
	XStream xStream = new XStream(new StaxDriver());
	String xml = xStream.toXML(car);

	File file = new File("object.xml");

	writeToFile(file, xml);

	String readXMLFromFile = readFromFile(file);
	Car car1 = (Car) xStream.fromXML(readXMLFromFile);

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
