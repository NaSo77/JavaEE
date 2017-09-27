package alcohol;

public class Man extends Thread {
    private static final int DRINKING_TIME = 1_000;
    private static final int BOTTLE_PCS = 5;
    private AlcoholShore store;

    public Man(AlcoholShore store, String name) {
	this.store = store;
	setName(name);
    }

    @Override
    public void run() {
	while (true) {
	    AlcoholType type = AlcoholType.getRandom();
	    this.store.getBottle(type, BOTTLE_PCS);
	    try {
		Thread.sleep(DRINKING_TIME);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	}
    }
}
