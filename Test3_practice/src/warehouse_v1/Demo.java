package warehouse_v1;

public class Demo {
    public static void main(String[] args) {
	Warehouse warehouse = new Warehouse();
	Supplier supplier = new Supplier(warehouse);

	Shop shop1 = new Shop(warehouse);
	Client shop1c1 = new Client("c1", shop1);
	Client shop1c2 = new Client("c2", shop1);
	Client shop1c3 = new Client("c3", shop1);

	new Thread(supplier).start();
	new Thread(shop1).start();
	new Thread(shop1c1).start();
	new Thread(shop1c2).start();
	new Thread(shop1c3).start();
    }
}
