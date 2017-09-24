package warehouse_v1;

public class Supplier implements Runnable {
    private Warehouse warehouse;

    public Supplier(Warehouse warehouse) {
	this.warehouse = warehouse;
    }

    @Override
    public void run() {
	while (true) {
	    this.warehouse.takeProducts();
	}
    }
}
