package producer_consumer.phone;

public class Demo {
    public static void main(String[] args) {
	Phone phone = new Phone();

	Thread charger = new Thread(new Charger(phone));
	Thread app = new Thread(new App(phone));

	charger.start();
	app.start();
    }
}
