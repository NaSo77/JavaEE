package producer_consumer.phone;

public class Charger implements Runnable {

    private Phone phone;

    public Charger(Phone phone) {
	this.phone = phone;
    }

    @Override
    public void run() {
	while (true) {
	    this.phone.charge();
	}
    }

}
