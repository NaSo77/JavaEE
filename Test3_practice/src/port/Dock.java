package port;

import java.util.concurrent.ArrayBlockingQueue;

public class Dock {
    private static int uniqueID = 1;
    private String dockID;
    private ArrayBlockingQueue<Boat> opashka = new ArrayBlockingQueue<>(3);


    public Dock() {
	this.dockID = "Dock" + uniqueID++;
    }

    public String getDockID() {
	return dockID;
    }

    public void addBoat(Boat ship) {
	try {
	    System.out.println(ship.getName() + " entered in dock");
	    this.opashka.put(ship);
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
    }

    public Boat getBoat() {
	Boat boat = null;
	try {
	    boat = this.opashka.take();
	    // System.out.println(boat.getName() + " left the dock");
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
	return boat;
    }

    public boolean hasBoats() {
	return !opashka.isEmpty();
    }

}
