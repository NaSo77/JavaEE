package alcohol;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class Report extends Thread {

    private final static String fileName = "report.txt";
    private AlcoholShore rakiaStore;
    private Warehouse sklad;

    public Report(AlcoholShore rakiaStore, Warehouse sklad) {
	this.rakiaStore = rakiaStore;
	this.sklad = sklad;
	setDaemon(true);
    }

    @Override
    public void run() {
	while (true) {
	    try {
		Thread.sleep(5_000);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	    writeToFile(fileName);
	}
    }

    private void writeToFile(String fileName) {
	File report = new File(fileName);

	if (!report.exists()) {
	    try {
		report.createNewFile();
	    } catch (IOException e) {
		System.out.println("File cannot be created!");
	    }
	}

	try (FileWriter fw = new FileWriter(report, true);) {
	    fw.write(LocalDateTime.now().toLocalTime().toString() + "\n");
	    fw.write(sklad.toString());
	    fw.write("\n");
	    fw.write(rakiaStore.toString());
	    fw.write("\n");
	} catch (IOException e) {
	    e.printStackTrace();
	}

    }

}
