package gasStation1;

public class FuelBoy extends Thread {
    private static int ID = 1;
    private GasStation gasStation;

    public FuelBoy(GasStation gasStation) {
	this.gasStation = gasStation;
	setName("FuelBoy" + ID++);
    }

    @Override
    public void run() {
	while (true) {
	    gasStation.refuelCars();
	}
    }
}
