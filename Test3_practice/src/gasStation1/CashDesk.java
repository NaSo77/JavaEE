package gasStation1;

import java.util.LinkedList;
import java.util.Queue;

public class CashDesk {
    private GasStation gasStation;
    private static final int MAX_CAR_PER_DESK = 2;
    private Queue<Car> opashka = new LinkedList<>();

    public CashDesk(GasStation gasStation) {
	this.gasStation = gasStation;
	new Cashier(this).start();
	new Cashier(this).start();
    }

    public synchronized void add(Car car) {
	while (opashka.size() == MAX_CAR_PER_DESK) {
	    try {
		System.out.println("Too many people in cash desk. Waiting before entering");
		wait();
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	}
	this.opashka.offer(car);
	notifyAll();
    }

    public synchronized void remove() {
	String name = Thread.currentThread().getName();
	while (this.opashka.isEmpty()) {
	    try {
		System.out.println(name + " No cars for paying. Waiting.");
		wait();
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	}
	Car car = this.opashka.poll();
	System.out.println(name + ": " +
		car.getCarName() + " refuels " + car.getAmount() + "Liters of " + car.getFuelType());
	leaveCar(car);
	this.notifyAll();
    }

    private void leaveCar(Car c) {
	System.out.println(c.getCarName() + " leave gas station");
    }
}
