package alcohol;

public class AlcoholProducer extends Thread {

    private static final int ALCOHOL_PRODUCING_TIME = 3_000;
    private static final int GRAPE_QUANTITY = 25;
    private static final int BOTTLE_QUANTITY = 10;
    private Warehouse warehouse;
    private AlcoholShore store;
    private SugarFactory zaharniZavodi;
    private int sugar;

    public AlcoholProducer(Warehouse warehouse, AlcoholShore store, SugarFactory zaharniZavodi, String name) {
	this.warehouse = warehouse;
	this.store = store;
	this.zaharniZavodi = zaharniZavodi;
	setName(name);
    }

    @Override
    public void run() {
	while (true) {
	    GrapeType type = GrapeType.getRandom();
	    this.warehouse.getGrape(type, GRAPE_QUANTITY);
	    try {
		Thread.sleep(ALCOHOL_PRODUCING_TIME);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }

	    while (this.sugar < 50) {
		this.sugar += this.zaharniZavodi.getSugar(20);
	    }

	    AlcoholType alcoholType = AlcoholType.getRandom();
	    this.store.addBottle(BOTTLE_QUANTITY, alcoholType);
	    System.out.println("Produced: " + BOTTLE_QUANTITY + " bottles.");
	}
    }

}
