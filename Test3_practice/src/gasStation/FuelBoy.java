package gasStation;

import java.util.Queue;
import java.util.Random;

import gasStation.Car.FuelType;

public class FuelBoy extends Thread {

    static GasStation station;

    @Override
    public void run() {
	while (true) {
	    for (int i = 0; i < station.getKolonki().size(); i++) {
		Queue<Car> kolonka = station.getKolonki().get(i);
		Car car;
		synchronized (kolonka) {
		    if (kolonka.isEmpty()) {
			// System.out.println(Thread.currentThread().getName() +
			// " - NO CAR HERE" );
			continue;
		    }
		    car = kolonka.poll();
		    // System.out.println(Thread.currentThread().getName() + " -
		    // WE GET THE CAR" );
		}
		try {
		    Thread.sleep(50);// zarejdame
		} catch (InterruptedException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		}
		FuelType fuelType = Car.FuelType.values()[new Random().nextInt(Car.FuelType.values().length)];
		System.out.println(Thread.currentThread().getName() + " - " + car);
		System.out.println(Thread.currentThread().getName() + " - " + fuelType);
		car.setFuel(fuelType);
		int amount = new Random().nextInt(31) + 10;
		car.setAmount(amount);
		car.setKolonka(i + 1);
		System.out.println("Kolata zaredi " + amount + " litra " + fuelType.toString());
		station.alignToPay(car);
	    }
	}
    }
}
