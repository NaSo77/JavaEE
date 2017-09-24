package warehouse_v1;

import java.util.HashMap;
import java.util.Map.Entry;

public class Warehouse {
    private static final int MIN_QUANTITY = 5;
    private static final int SUPPLY_QUANTITY = 25;

    private HashMap<String, HashMap<String, Integer>> products;

    public Warehouse() {
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

    public synchronized void giveProducts(String name) {
	if (hasInsufficient(name)) {
	    try {
		wait();
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	}
	removeProduct(name);
	notifyAll();
    }

    private void removeProduct(String name) {
	for (HashMap<String, Integer> product : products.values()) {
	    for (Entry<String, Integer> prod : product.entrySet()) {
		if (prod.getKey().equals(name)) {
		    prod.setValue(prod.getValue() - 5);
		}
	    }
	}
    }

    private boolean hasInsufficient(String name) {
	for (HashMap<String, Integer> product : products.values()) {
	    for (Entry<String, Integer> prod : product.entrySet()) {
		if (prod.getKey().equals(name) && prod.getValue() < MIN_QUANTITY) {
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
	    System.out.println("Warehouse: fill all missing products from supplier");
	    supplyInsufficientProducts();
	    notifyAll();
	} else {
	    try {
		wait();
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	}
    }

    private void supplyInsufficientProducts() {
	for (HashMap<String, Integer> product : products.values()) {
	    for (Entry<String, Integer> pr : product.entrySet()) {
		if (pr.getValue() < MIN_QUANTITY) {
		    pr.setValue(pr.getValue() + SUPPLY_QUANTITY);
		}
	    }
	}
    }

    private boolean hasInsufficient() {
	for (HashMap<String, Integer> product : products.values()) {
	    for (Entry<String, Integer> pr : product.entrySet()) {
		if (pr.getValue() < MIN_QUANTITY) {
		    return true;
		}
	    }
	}
	return false;
    }
}
