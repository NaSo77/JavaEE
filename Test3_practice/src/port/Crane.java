package port;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Crane extends Thread {

    private DBManager dbManager = DBManager.getInstance();
    private static int uniqueID = 1;
    private ArrayList<Warehouse> warehouses;
    private ArrayList<Dock> docks;
    private Harbor harbor;

    public Crane(ArrayList<Warehouse> warehouses, ArrayList<Dock> docks, Harbor harbor) {
	this.warehouses = warehouses;
	this.docks = docks;
	this.harbor = harbor;
	setName("Crane" + uniqueID++);
    }

    @Override
    public void run() {
	while (true) {
	    Dock dock = getRandomDock();
	    Boat boat = dock.getBoat();
	    List<Package> packages = boat.getPackages();

	    System.out.println(Thread.currentThread().getName() + " is unloading the packages from " + boat.getName());

	    int secondsForUnloading = packages.size() * 2 * 1000;
	    try {
		Thread.sleep(secondsForUnloading);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }

	    for (Package p : packages) {
		dbManager.insertIntoDB(boat.getName(), dock.getDockID(), this.getName(), LocalDateTime.now(),
			p.getName());

		Record record = new Record(p.getName(), dock.getDockID(), boat.getName(), this.getName(),
			LocalDate.now(), LocalTime.now());

		this.harbor.addRecordRecordToDiary(record);
	    }

	    Warehouse w = getRandomWarehouse();
	    System.out.println(Thread.currentThread().getName() + " put all packages to " + w.getName());
	    w.addPackages(packages);
	}
    }


    private Dock getRandomDock() {
	Dock result = null;
	while (result == null) {
	    for (Dock dock : docks) {
		if (dock.hasBoats()) {
		    result = dock;
		}
	    }
	}
	return result;
    }

    private Warehouse getRandomWarehouse() {
	Warehouse result = null;
	while (result == null) {
	    for (Warehouse warehouse : warehouses) {
		if (warehouse.hasFreeSpace()) {
		    result = warehouse;
		}
	    }
	}
	return result;
    }
}
