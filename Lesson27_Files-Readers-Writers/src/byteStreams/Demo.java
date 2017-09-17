package byteStreams;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Demo {
    public static void main(String[] args) {

	File original = new File("dinoDev.jpg");
	File copy = new File("dinoDevCopy.jpg");

	try {
	    copy.createNewFile();
	} catch (IOException e1) {
	    e1.printStackTrace();
	}

	try (FileInputStream fis = new FileInputStream(original);
		FileOutputStream fos = new FileOutputStream(copy);) {
	    int b = fis.read();
	    while (b != -1) {
		fos.write(b);
		b = fis.read();
	    }
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
}
