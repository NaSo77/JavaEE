package alcohol;

import java.util.HashMap;
import java.util.Map.Entry;

public class AlcoholShore {

    private static final int MIN_IN_CARTON = 0;
    private static final int MAX_IN_CARTON = 20;

    private HashMap<AlcoholType, Integer> cartons = new HashMap<>();

    public AlcoholShore() {
	generateCartons();
    }

    private void generateCartons() {
	for (AlcoholType type : AlcoholType.values()) {
	    this.cartons.put(type, 0);
	}
    }

    public synchronized void addBottle(int bottleQuantity, AlcoholType type) {
	int pcsInCarton = this.cartons.get(type);

	while (pcsInCarton + bottleQuantity > MAX_IN_CARTON) {
	    try {
		System.out.println(Thread.currentThread().getName() + " not enouth room for the bottles. Waiting...");
		this.wait();
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	}

	this.cartons.put(type, pcsInCarton + bottleQuantity);
	String name = Thread.currentThread().getName();
	System.out.println(
		name + " adds " + bottleQuantity + " bottles. Total: " + this.cartons.get(type) + "pcs " + type);
	this.notifyAll();
    }

    public synchronized void getBottle(AlcoholType type, int bottlesQuantity) {
	while (this.cartons.get(type) < bottlesQuantity) {
	    try {
		System.out.println(
			Thread.currentThread().getName() + " Not enougth " + type + " rakia. Waiting...");
		this.wait();
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	}
	this.cartons.put(type, this.cartons.get(type) - bottlesQuantity);
	String name = Thread.currentThread().getName();
	System.out.println(name + " get" + bottlesQuantity + ". Bottles left: " + this.cartons.get(type) + "pcs " + type
		+ " rakia");
	this.notifyAll();
    }

    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder();
	sb.append("RakiaStore -> ");
	for (Entry<AlcoholType, Integer> type : cartons.entrySet()) {
	    sb.append(type.getKey());
	    sb.append(" - ");
	    sb.append(type.getValue());
	    sb.append("pcs. ");
	}
	return sb.toString();
    }
}
