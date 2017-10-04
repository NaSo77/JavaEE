package port;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.Random;
import java.util.TreeMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Harbor {

    private static int uniqueID = 1;
    private String HarborID;
    private ArrayList<Dock> docks;
    private ArrayList<Warehouse> warehouses;
    private ArrayList<Crane> cranes;
    private static final int DOCKS = 5;
    private static final int WAREHOUSES = 2;
    private static final int CRANES = 2;

    private CopyOnWriteArrayList<Record> diary;

    public Harbor() {
	this.HarborID = "Harbor" + uniqueID++;
	this.diary = new CopyOnWriteArrayList<>();
	generateDocks();
	generateWarehouses();
	generateCranes();
	new Reporter(this).start();
    }

    public void addRecordRecordToDiary(Record record) {
	this.diary.add(record);
    }

    public void printAllUnloadedPackages() {
	// dock -> package, boat, crane, date, time
	TreeMap<String, ArrayList<String>> allPakages = new TreeMap<>();
	for (Record record : diary) {
	    String dock = record.getDockID();
	    if (!allPakages.containsKey(dock)) {
		allPakages.put(dock, new ArrayList<>());
	    }
	    
	    String newLine = record.getPackageID() + ", " + record.getBoatID() + ", " + record.getCraneID() + ", "
		    + record.getDate() + ", " + record.getTime();
	    allPakages.get(dock).add(newLine);
	}

	for (Entry<String, ArrayList<String>> entry : allPakages.entrySet()) {
	    System.out.println(entry.getKey());
	    for (String row : entry.getValue()) {
		System.out.println("    " + row);
	    }
	}
    }

    private void generateCranes() {
	this.cranes = new ArrayList<>();
	for (int i = 0; i < CRANES; i++) {
	    this.cranes.add(new Crane(warehouses, docks, this));
	}

	for (Crane crane : cranes) {
	    crane.start();
	}
    }

    private void generateWarehouses() {
	this.warehouses = new ArrayList<>();
	for (int i = 0; i < WAREHOUSES; i++) {
	    this.warehouses.add(new Warehouse());
	}
    }

    private void generateDocks() {
	this.docks = new ArrayList<>();
	for (int i = 0; i < DOCKS; i++) {
	    this.docks.add(new Dock());
	}
    }

    public void enterInHarbour(Boat boat) {
	int dockNumber = new Random().nextInt(this.docks.size());
	this.docks.get(dockNumber).addBoat(boat);
    }

    public void leaveHarbour(Boat boat) {
	System.out.println(boat.getName() + " has left " + HarborID);
    }

    public void writeDiaryToFile(File file) {
	PrintWriter pw = null;
	try {
	    pw = new PrintWriter(file);
	    for (Record record : diary) {
		pw.println(record);
	    }
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	} finally {
	    pw.close();
	}

    }
}
