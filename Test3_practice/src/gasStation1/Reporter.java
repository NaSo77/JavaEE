package gasStation1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Reporter extends Thread {

    public Reporter() {
	setDaemon(true);
    }

    @Override
    public void run() {
	while (true) {
	    try {
		Thread.sleep(2000);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }

	    File statistic = new File("stat.txt");
	    DBManager db = DBManager.getInstance();

	    try (FileWriter writer = new FileWriter(statistic, true);) {
		writer.write(db.getInfoFromDB());
	    } catch (FileNotFoundException e) {
		e.printStackTrace();
	    } catch (IOException e1) {
		e1.printStackTrace();
	    }

	}
    }
}
