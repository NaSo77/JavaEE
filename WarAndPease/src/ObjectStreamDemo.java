import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ObjectStreamDemo {
    public static void main(String[] args) throws FileNotFoundException, IOException {
	ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(new File("text.txt")));
    }
}
