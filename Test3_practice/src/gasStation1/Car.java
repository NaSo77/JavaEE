package gasStation1;

import java.time.LocalDateTime;
import java.util.Random;

public class Car {

    public enum FuelType {
	PETROL, DIESEL, GAS;

	public static FuelType getRandom() {
	    return FuelType.values()[new Random().nextInt(FuelType.values().length)];
	}
    }

    private static int carIDNum = 1;
    private String name;
    private int amount;
    private FuelType fuelType;
    private boolean isRefuled;

    public int getAmount() {
	return amount;
    }

    public FuelType getFuelType() {
	return fuelType;
    }

    public String getCarName() {
	return this.name;
    }

    public Car() {
	this.name = "Car" + (carIDNum++);
    }

    public void refuel() {
	try {
	    this.amount = new Random().nextInt(31) + 10;
	    this.fuelType = FuelType.getRandom();
	    System.out.println(this.name + " fuels " + this.amount + "liter of " + this.fuelType);
	    DBManager DB = DBManager.getInstance();
	    DB.insertIntoDB(fuelType, amount, LocalDateTime.now(), this.name);
	    this.isRefuled = true;
	    Thread.sleep(1000);
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
    }

    public boolean isRefuled() {
	return isRefuled;
    }
}
