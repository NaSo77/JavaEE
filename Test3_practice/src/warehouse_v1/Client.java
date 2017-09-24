package warehouse_v1;

import java.util.Random;

public class Client implements Runnable {

    private String name;
    private Shop shop;

    public Client(String name, Shop shop) {
	this.name = name;
	this.shop = shop;
    }

    @Override
    public void run() {
	while (true) {
	    int quantity = new Random().nextInt(3) + 1;
	    String productName = shop.randomProductName();

	    System.out.println(this.name + " take " + quantity + " " + productName);
	    this.shop.giveProducts(productName, quantity);
	    try {
		Thread.sleep(500);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	}
    }
}
