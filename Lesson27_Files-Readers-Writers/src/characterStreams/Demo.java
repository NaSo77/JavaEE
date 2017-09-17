package characterStreams;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Demo {
    public static void main(String[] args) {
	File file = new File("SomeTestFile.txt");

	try {
	    file.createNewFile();
	} catch (IOException e1) {
	    e1.printStackTrace();
	}

	try(FileReader fr = new FileReader(file);
		FileWriter fw = new FileWriter(file);) {
	    fw.write("line1\n");
	    fw.write("line2\n");
	    fw.write("line3\n");
	    fw.flush();
	    int b = fr.read();
	    while (b != -1) {
		System.out.print((char) b);
		b = fr.read();
	    }

	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
	
    }
}
