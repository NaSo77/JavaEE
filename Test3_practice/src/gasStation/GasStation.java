package gasStation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

import gasStation.Car.FuelType;

public class GasStation {

    private Cashier penka;
    private Cashier minka;
    private FuelBoy pesho = new FuelBoy();
    private FuelBoy gosho = new FuelBoy();

    private static class Reporter extends Thread {
	@Override
	public void run() {
	    while (true) {
		// do some db magic
		try {
		    Thread.sleep(50000);
		} catch (InterruptedException e) {
		    System.out.println("ops");
		}
	    }
	}
    }

    private ArrayList<Queue<Car>> kolonki = new ArrayList<>();
    private ArrayList<ArrayBlockingQueue<Car>> kasi = new ArrayList<>();
    // kolonka -> fueltype -> date, amount
    private HashMap<Integer, HashMap<FuelType, ConcurrentHashMap<LocalDateTime, Integer>>> statistics;

    public GasStation() {
	statistics = new HashMap<>();
	for (int i = 0; i < 5; i++) {
	    kolonki.add(new LinkedList<>());
	    statistics.put(i + 1, new HashMap<>());
	    statistics.get(i + 1).put(Car.FuelType.DIESEL, new ConcurrentHashMap<>());
	    statistics.get(i + 1).put(Car.FuelType.GAS, new ConcurrentHashMap<>());
	    statistics.get(i + 1).put(Car.FuelType.PETROL, new ConcurrentHashMap<>());
	}
	for (int i = 0; i < 2; i++) {
	    kasi.add(new ArrayBlockingQueue<>(15));
	}
	penka = new Cashier(kasi.get(0));
	minka = new Cashier(kasi.get(1));
	FuelBoy.station = this;
	Cashier.station = this;
	penka.start();
	minka.start();
	pesho.start();
	gosho.start();
	Reporter r = new Reporter();
	r.setDaemon(true);
	r.start();
    }

    public void enterGasStation(Car c) {
	kolonki.get(new Random().nextInt(kolonki.size())).offer(c);
    }

    public List<Queue<Car>> getKolonki() {
	return Collections.unmodifiableList(kolonki);
    }

    public void alignToPay(Car car) {
	kasi.get(new Random().nextInt(kasi.size())).offer(car);
    }

    public void addData(FuelType fuel, int amount, int kolonka, LocalDateTime date) {
	statistics.get(kolonka).get(fuel).put(date, amount);
	DBManager.getInstance().insertIntoDB(fuel, amount, kolonka, date);
    }

    public void printStatistics() {
	for (Integer kolonka : statistics.keySet()) {
	    System.out.println("Na kolonka " + kolonka + " sa zaredili:");
	    for (FuelType type : statistics.get(kolonka).keySet()) {
		System.out.println("    " + type.toString() + ":");
		for (Entry<LocalDateTime, Integer> e : statistics.get(kolonka).get(type).entrySet()) {
		    System.out.println("        " + e.getValue() + " litra na " + e.getKey());
		}
	    }
	}
    }
}
