package homework;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Demo {
    public static void main(String[] args) {

	LocalDateTime start = LocalDateTime.now();

	ExecutorService assemblyLine = Executors.newFixedThreadPool(3);
	System.out.println("Executor start");

	assemblyLine.submit(new Engine());
	assemblyLine.submit(new Frame());

	assemblyLine.submit(new Seat());
	assemblyLine.submit(new Seat());
	assemblyLine.submit(new Seat());
	assemblyLine.submit(new Seat());
	assemblyLine.submit(new Seat());

	assemblyLine.submit(new Tyre());
	assemblyLine.submit(new Tyre());
	assemblyLine.submit(new Tyre());
	assemblyLine.submit(new Tyre());

	assemblyLine.shutdown();
	try {
	    assemblyLine.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
	    LocalDateTime end = LocalDateTime.now();

	    System.out.println("total time: " + Duration.between(start, end).toMillis() + " milisec");
	    System.out.println("Executor shut down");

	} catch (InterruptedException e) {
	    e.printStackTrace();
	}

    }

}
