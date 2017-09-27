package alcohol;

public class SugarFactory {
    
    private static final int MAX_QUANTITY = 5_000;
    private int currentQuantity = 0;

    public synchronized void addSugar(int addQuntity) {
	while (currentQuantity + addQuntity > MAX_QUANTITY) {
	    try {
		System.out.println("Max sugar level reached");
		this.wait();
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	}
	String name = Thread.currentThread().getName();
	System.out.println(name + " added sugar");

	this.currentQuantity += addQuntity;
	notifyAll();
    }

    public synchronized int getSugar(int removeQuntity) {
	while (currentQuantity > removeQuntity) {
	    try {
		System.out.println("Not enougth sugar. Waiting...");
		this.wait();
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	}
	String name = Thread.currentThread().getName();
	System.out.println(name + " get sugar");
	this.currentQuantity -= removeQuntity;
	notifyAll();
	return removeQuntity;
    }
}
