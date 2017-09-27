package alcohol;

public class GrapeProducer extends Thread {
    private static final int GRAPE_QUANTITY = 10;
    private static final int PRODUCING_TIME = 2_000;
    private Warehouse warehouse;

    public GrapeProducer(Warehouse warehouse, String name) {
	this.warehouse = warehouse;
	setName(name);
    }

    @Override
    public void run() {
	while (true) {
	    try {
		Thread.sleep(PRODUCING_TIME);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	    GrapeType type = GrapeType.getRandom();
	    this.warehouse.addGrape(type, GRAPE_QUANTITY);
	}
    }

}
