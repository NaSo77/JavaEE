package producer_consumer.phone;

public class App implements Runnable {

    private Phone phone;

    public App(Phone phone) {
	this.phone = phone;
    };

    @Override
    public void run() {
	while (true) {
	    this.phone.disCharge();
	}
    }
}
