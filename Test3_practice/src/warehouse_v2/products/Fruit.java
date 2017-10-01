package warehouse_v2.products;

public class Fruit extends Product {
    public Fruit(String name) {
	super("Fruit", name);
    }

    public Fruit(String name, int quantity) {
	super("Fruit", name, quantity);
    }
}
