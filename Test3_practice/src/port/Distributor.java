package port;

public class Distributor extends Thread {

    private static int uniqueID = 1;
    private Warehouse warehouse;

    public Distributor(Warehouse warehouse) {
	this.warehouse = warehouse;
	setName("Distributor" + uniqueID++);
    }

    @Override
    public void run() {
	while (true) {
	    this.warehouse.takePackage();
	}
    }

}
