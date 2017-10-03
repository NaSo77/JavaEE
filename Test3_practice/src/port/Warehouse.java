package port;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

public class Warehouse {

    private static final int PACKAGE_PROCESSING_TIME = 5000;
    private static final int AVAILABLE_SPACE = 10;
    private static int uniqueID = 1;
    private String name;

    public String getName() {
	return name;
    }

    public Warehouse() {
	new Distributor(this).start();
	this.name = "Warehouse" + uniqueID++;
    }

    ArrayBlockingQueue<Package> allPackages = new ArrayBlockingQueue<>(AVAILABLE_SPACE);

    public void addPackages(List<Package> packages) {
	try {
	    for (Package p : packages) {
		allPackages.put(p);
	    }
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
    }

    public void takePackage() {
	try {
	    Thread.sleep(PACKAGE_PROCESSING_TIME);
	    Package p = this.allPackages.take();
	    System.out.println(Thread.currentThread().getName() + " takes out the package with ID: " + p.getName());
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
    }

    public boolean hasFreeSpace() {
	return allPackages.size() < AVAILABLE_SPACE;
    }
}
