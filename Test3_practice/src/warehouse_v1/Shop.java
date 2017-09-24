package warehouse_v1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;

public class Shop implements Runnable {
    private static final int MIN_QUANTITY = 5;
    private static final int SUPPLY_QUANTITY = 25;

    private Warehouse warehouse;

    private HashMap<String, HashMap<String, Integer>> products;

    public Shop(Warehouse warehouse) {
	this.warehouse = warehouse;
	this.products = new HashMap<>();
	this.products.put("FRUITS", new HashMap<>());
	this.products.get("FRUITS").put("Banana", 15);
	this.products.get("FRUITS").put("Orange", 15);
	this.products.get("FRUITS").put("Apple", 15);

	this.products.put("VEGETABLES", new HashMap<>());
	this.products.get("VEGETABLES").put("Potato", 15);
	this.products.get("VEGETABLES").put("Eggplant", 15);
	this.products.get("VEGETABLES").put("Cucumber", 15);

	this.products.put("MEATS", new HashMap<>());
	this.products.get("MEATS").put("Pork", 15);
	this.products.get("MEATS").put("Beaf", 15);
	this.products.get("MEATS").put("Chicken", 15);
    }

    public synchronized void giveProducts(String name, int quantity) {
	if (hasInsufficient(name, quantity)) {
	    try {
		wait();
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	}
	removeProduct(name, quantity);
	notifyAll();
    }

    private void removeProduct(String name, int quantity) {
	for (HashMap<String, Integer> product : products.values()) {
	    for (Entry<String, Integer> prod : product.entrySet()) {
		if (prod.getKey().equals(name)) {
		    prod.setValue(prod.getValue() - quantity);
		}
	    }
	}
    }

    private boolean hasInsufficient(String name, int quantity) {
	for (HashMap<String, Integer> product : products.values()) {
	    for (Entry<String, Integer> prod : product.entrySet()) {
		if (prod.getKey().equals(name) && prod.getValue() < quantity) {
		    return true;
		} else {
		    return false;
		}
	    }
	}
	return false;
    }

    public synchronized void takeProducts() {
	if (hasInsufficient()) {
	    supplyInsufficientProducts();
	    notifyAll();
	}

	try {
	    wait();
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
    }

    private void supplyInsufficientProducts() {
	for (HashMap<String, Integer> product : products.values()) {
	    for (Entry<String, Integer> prod : product.entrySet()) {
		if (prod.getValue() < MIN_QUANTITY) {
		    System.out.println("Shop: take 5 " + prod.getKey() + " from warehouse");
		    this.warehouse.giveProducts(prod.getKey());
		}
	    }
	}
    }

    private boolean hasInsufficient() {
	for (HashMap<String, Integer> product : products.values()) {
	    for (Integer pcs : product.values()) {
		if (pcs < MIN_QUANTITY) {
		    return true;
		}
	    }
	}
	return false;
    }

    @Override
    public void run() {
	while (true) {
	    this.takeProducts();
	}
    }

    public String randomProductName() {
	ArrayList<String> arrayList = new ArrayList<>();
	for (HashMap<String, Integer> asd : this.products.values()) {
	    arrayList.addAll(asd.keySet());
	}
	return arrayList.get(new Random().nextInt(arrayList.size()));
    }
}
