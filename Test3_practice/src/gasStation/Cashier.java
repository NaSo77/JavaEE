package gasStation;

import java.time.LocalDateTime;
import java.util.concurrent.ArrayBlockingQueue;

public class Cashier extends Thread {

    static GasStation station;
    private ArrayBlockingQueue<Car> kasa;

    public Cashier(ArrayBlockingQueue<Car> kasa) {
	this.kasa = kasa;
    }

    @Override
    public void run() {
	while (true) {
	    try {
		Car car = kasa.take();
		Thread.sleep(50);
		// add to common collection - the car, the fuel and the amount
		System.out.println("Kolata si plati za " + car.getAmount() + " litra " + car.getFuel().toString());
		station.addData(car.getFuel(), car.getAmount(), car.getKolonka(), LocalDateTime.now());
	    } catch (InterruptedException e) {
		System.out.println("ops");
	    }
	}
    }

}
