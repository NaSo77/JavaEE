package gasStation1;

public class GasStation {

    private Kolonka kolonka = new Kolonka();
    private CashDesk cashDesk1 = new CashDesk(this);

    private boolean isGasStationFull = false;

    public GasStation() {
	startWorkers();
    }

    public synchronized void addCar(Car car) {
	while (this.kolonka.isKolonkaFull()) {
	    try {
		System.out.println("Kolonka is full. " + car.getCarName() + " is waiting.");
		this.wait();
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	}
	System.out.println(car.getCarName() + " entered in gas station");
	this.kolonka.addCar(car);
	try {
	    Thread.sleep(100);
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
	this.notifyAll();
    }

    public synchronized void refuelCars() {
	String name = Thread.currentThread().getName();
	while (!hasCarsForRefuling()) {
	    try {
		System.out.println(name + ": no car for refueling. I will sleep");
		this.wait();
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	}
	Car car = getCarFromKolonkas();
	System.out.println(name + " is refueling " + car.getCarName());
	car.refuel();
	cashDesk1.add(car);
	this.notifyAll();
    }

    private void startWorkers() {
	new FuelBoy(this).start();
	new FuelBoy(this).start();
    }

    private Car getCarFromKolonkas() {
	if (this.kolonka.hasCarAtOpashka()) {
	    return kolonka.getNextCar();
	}
	return null;
    }

    public boolean hasCarsForRefuling() {
	if (this.kolonka.hasCarAtOpashka()) {
	    return true;
	}
	return false;
    }
}
