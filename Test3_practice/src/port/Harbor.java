package port;

import java.util.ArrayList;
import java.util.Random;

public class Harbor {

    private static int uniqueID = 1;
    private String HarborID;
    private ArrayList<Dock> docks;
    private ArrayList<Warehouse> warehouses;
    private ArrayList<Crane> cranes;
    private static final int DOCKS = 5;
    private static final int WAREHOUSES = 2;
    private static final int CRANES = 2;

    public Harbor() {
	this.HarborID = "Harbor" + uniqueID++;
	generateDocks();
	generateWarehouses();
	generateCranes();
    }

    private void generateCranes() {
	this.cranes = new ArrayList<>();
	for (int i = 0; i < CRANES; i++) {
	    this.cranes.add(new Crane(warehouses, docks));
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
}
