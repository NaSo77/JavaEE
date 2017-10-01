package warehouse_v2.products;

public class Meat extends Product {
    public Meat(String name) {
	super("Meat", name);
    }

    public Meat(String name, int quantity) {
	super("Meat", name, quantity);
    }
}