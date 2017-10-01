package warehouse_v2;

public class Client extends Thread {

    private Warehouse warehouse;

    public Client(String name, Warehouse warehouse) {
	this.warehouse = warehouse;
	setName(name);
    }

    @Override
    public void run() {
	while (true) {
	    this.warehouse.getGoods(warehouse.getRandomProductName());
	    try {
		sleep(2000);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	}
    }
}
