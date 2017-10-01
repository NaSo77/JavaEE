package warehouse_v2.products;

public class Vegetable extends Product {
    public Vegetable(String name) {
	super("Vegetable", name);
    }

    public Vegetable(String name, int quantity) {
	super("Vegetable", name, quantity);
    }
}
