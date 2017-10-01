package gasStation1;

import java.util.LinkedList;
import java.util.Queue;

public class Kolonka {
    private static final int MAX_CAR_PER_KOLONKA = 1;
    private Queue<Car> opashka;

    public Kolonka() {
	this.opashka = new LinkedList<>();
    }

    public Car getNextCar() {
	return this.opashka.poll();
    }

    public boolean hasCarAtOpashka() {
	if (opashka.size() == 0) {
	    return false;
	}
	return true;
    }

    public boolean isKolonkaFull() {
	if (this.opashka.size() >= MAX_CAR_PER_KOLONKA) {
	    return true;
	}
	return false;
    }

    public void addCar(Car car) {
	this.opashka.offer(car);
    }

    public boolean hasReadyCar() {
	return this.opashka.peek().isRefuled();
    }

}
