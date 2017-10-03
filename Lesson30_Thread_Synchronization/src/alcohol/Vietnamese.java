package alcohol;

public class Vietnamese extends Thread {

    private static final int PRODUCING_TIME = 10_000;
    private static final int SUGAR_QUANTITY = 100;
    private SugarFactory zaharniZavodi;

    public Vietnamese(SugarFactory zaharniZavodi, String name) {
	this.zaharniZavodi = zaharniZavodi;
	setName(name);
    }

    @Override
    public void run() {
	while (true) {
	    this.zaharniZavodi.addSugar(SUGAR_QUANTITY);
	    try {
		Thread.sleep(PRODUCING_TIME);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	}
    }

}
