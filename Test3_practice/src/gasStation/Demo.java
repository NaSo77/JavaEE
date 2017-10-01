package gasStation;

public class Demo {

    public static void main(String[] args) {

	GasStation station = new GasStation();
	for (int i = 0; i < 10; i++) {
	    station.enterGasStation(new Car());
	}
	try {
	    Thread.sleep(60 * 1000);
	} catch (InterruptedException e) {
	}
	station.printStatistics();
    }
}
