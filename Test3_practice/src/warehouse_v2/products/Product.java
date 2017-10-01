package warehouse_v2.products;

public abstract class Product {

    private String type;
    private String name;
    private int quantityInStore = 0;

    public Product(String type, String name) {
	this.type = type;
	this.name = name;
    }

    public Product(String type, String name, int quantity) {
	this.type = type;
	this.name = name;
	this.quantityInStore = quantity;
    }

    public String getName() {
	return name;
    }
    public int getQuantityInStore() {
	return quantityInStore;
    }
    public void addQuantity(int pcs) {
	this.quantityInStore += pcs;
    }

    public void removeQuantity(int pcs) {
	this.quantityInStore -= pcs;
    }
}
