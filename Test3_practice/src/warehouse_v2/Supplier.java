package warehouse_v2;

public class Supplier extends Thread {

    private Warehouse warehouse;

    public Supplier(Warehouse warehouse) {
	this.warehouse = warehouse;
	setName("Supplier");
    }

    @Override
    public void run() {
	while (true) {
	    this.warehouse.addGoods();
	}
    }

}
