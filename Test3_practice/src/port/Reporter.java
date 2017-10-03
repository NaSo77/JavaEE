package port;

import java.io.File;

public class Reporter extends Thread {
    private Harbor harbor;
    private File file = new File(".\\src\\port\\", "report.txt");

    public Reporter(Harbor harbor) {
	setDaemon(true);
	this.harbor = harbor;
    }

    @Override
    public void run() {
	while (true) {
	    try {
		sleep(5_000);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	    this.harbor.writeDiaryToFile(file);
	}
    }
}
