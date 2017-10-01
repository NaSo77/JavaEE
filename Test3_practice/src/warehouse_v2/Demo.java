package warehouse_v2;

public class Demo {
    public static void main(String[] args) {
	Warehouse warehouse = new Warehouse();
	Client client1 = new Client("Client 1", warehouse);
	Client client2 = new Client("Client 1", warehouse);
	Client client3 = new Client("Client 1", warehouse);
	Supplier supplier = new Supplier(warehouse);

	client1.start();
	client2.start();
	client3.start();
	supplier.start();
    }
}
