package bufferedStreams;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Demo {
    public static void main(String[] args) throws NumberFormatException, IOException {
	File original = new File("bufferedOriginal.txt");
	File copy = new File("bufferedCopy.txt");

	if (!copy.exists()) {
	    try {
		copy.createNewFile();
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}

	if (!original.exists()) {
	    try {
		original.createNewFile();
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}

	BufferedInputStream bis = new BufferedInputStream(new FileInputStream(original));
	BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(copy));

	while (bis.available() > 0) {
	    bos.write(bis.read());
	    bos.flush();
	}
    }
}
