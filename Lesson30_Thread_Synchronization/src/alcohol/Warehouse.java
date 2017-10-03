package alcohol;

import java.util.HashMap;
import java.util.Map.Entry;

public class Warehouse {

    private static final int MIN_IN_BIDON = 0;
    private static final int MAX_IN_BIDON = 100;

    private HashMap<GrapeType, Integer> bidoni = new HashMap<>();

    public Warehouse() {
	generateBidoni();
    }

    private void generateBidoni() {
	for (GrapeType type : GrapeType.values()) {
	    this.bidoni.put(type, 0);
	}
    }

    public synchronized void addGrape(GrapeType type, int kgNabranoGrozde) {
	int kgInBidon = this.bidoni.get(type);

	while (kgInBidon + kgNabranoGrozde > MAX_IN_BIDON) {
	    try {
		System.out.println(Thread.currentThread().getName() + " not enougth place for the grape. I will wait");
		this.wait();
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	}

	this.bidoni.put(type, kgInBidon + kgNabranoGrozde);
	String name = Thread.currentThread().getName();
	System.out.println(name + " dobavi s stanaha " + this.bidoni.get(type) + "kg " + type + " grape");
	this.notifyAll();
    }

    public synchronized void getGrape(GrapeType type, int getQuantity) {
	while (this.bidoni.get(type) < getQuantity) {
	    try {
		System.out.println(
			Thread.currentThread().getName() + " Not enought " + type + " grape. I will wait");
		this.wait();
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	}

	this.bidoni.put(type, this.bidoni.get(type) - getQuantity);
	String name = Thread.currentThread().getName();
	System.out.println(name + " get and there are " + this.bidoni.get(type) + "kg " + type + " grape");
	this.notifyAll();
    }

    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder();
	sb.append("Sklad -> ");
	for (Entry<GrapeType, Integer> type : bidoni.entrySet()) {
	    sb.append(type.getKey());
	    sb.append(" - ");
	    sb.append(type.getValue());
	    sb.append("kg. ");
	}
	return sb.toString();
    }

}
