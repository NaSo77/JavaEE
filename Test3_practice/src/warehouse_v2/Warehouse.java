package warehouse_v2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

import warehouse_v2.products.Fruit;
import warehouse_v2.products.Meat;
import warehouse_v2.products.Product;
import warehouse_v2.products.Vegetable;

public class Warehouse {
    private static final int MIN_QUANTITY = 5;

    private HashSet<Product> goods;

    public Warehouse() {
	this.goods = new HashSet<>();
	this.goods.add(new Fruit("Banana", 15));
	this.goods.add(new Fruit("Orange", 15));
	this.goods.add(new Fruit("Apple", 15));
	this.goods.add(new Vegetable("Potato", 15));
	this.goods.add(new Vegetable("Eggplant", 15));
	this.goods.add(new Vegetable("Cucumber", 15));
	this.goods.add(new Meat("Pork", 15));
	this.goods.add(new Meat("Beef", 15));
	this.goods.add(new Meat("Chicken", 15));
    }

    public synchronized void addGoods() {
	String threadName = Thread.currentThread().getName();
	while (!hasDeficitProducts()) {
	    try {
		System.out.println(threadName + " No need to add Products. Waiting...");
		wait();
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	}
	System.out.println(threadName + " Adding 5pcs of each deficit products.");
	addDeficitProducts();
	notifyAll();
    }

    private void addDeficitProducts() {
	for (Product product : goods) {
	    if (product.getQuantityInStore() < MIN_QUANTITY) {
		product.addQuantity(25);
	    }
	}

    }

    private boolean hasDeficitProducts() {
	for (Product product : goods) {
	    if (product.getQuantityInStore() < MIN_QUANTITY) {
		return true;
	    }
	}
	return false;
    }

    public synchronized void getGoods(String name) {
	String threadName = Thread.currentThread().getName();
	while (hasLessThan5(name)) {
	    try {
		System.out.println(threadName + " Not enought pieces in warehouse. Waiting...");
		wait();
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	}
	notifyAll();
	System.out.println(threadName + " Get 5 pcs of " + name);
	for (Product product : this.goods) {
	    if (product.getName().equals(name)) {
		product.removeQuantity(5);
	    }
	}
    }

    private boolean hasLessThan5(String name) {
	for (Product product : this.goods) {
	    if (product.getName().equals(name)) {
		if (product.getQuantityInStore() < 5) {
		    return true;
		}
	    }
	}
	return false;
    }

    public String getRandomProductName() {
	ArrayList<String> allNames = new ArrayList<>();
	for (Product product : goods) {
	    allNames.add(product.getName());
	}
	return allNames.get(new Random().nextInt(allNames.size()));
    }
}
