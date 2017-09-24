package producer_consumer.phone;

public class Phone {

    private int batteryLevel;

    public synchronized void charge() {
	if (batteryLevel >= 100) {
	    try {
		wait();
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	} else {
	    batteryLevel += 1;
	    System.out.println("+++ Battery Level: " + this.batteryLevel);
	    notifyAll();
	}
    }

    public synchronized void disCharge() {
	if (batteryLevel <= 0) {
	    try {
		wait();
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	} else {
	    batteryLevel -= 1;
	    System.out.println("--- Battery Level: " + this.batteryLevel);
	    notifyAll();
	}
    }
}
