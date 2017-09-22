package homework;

public class CarPart implements Runnable {

    private String name;
    private int buildTime;

    public CarPart(String name, int buildTime) {
	this.name = name;
	this.buildTime = buildTime;
    }

    @Override
    public void run() {
	try {
	    Thread.sleep(buildTime);
	} catch (InterruptedException e) {
	    System.out.println("Not able to build the " + this.name);
	}
	System.out.println(Thread.currentThread().getName() + " - " + name + " ready!");
    }

}
