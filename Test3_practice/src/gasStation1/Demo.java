package gasStation1;

public class Demo {
    public static void main(String[] args) {
	GasStation gasStation = new GasStation();

	new Reporter().start();

	while (true) {
	    Car car = new Car();
	    gasStation.addCar(car);
	}
    }
}
