package horseRacing;

public class Pony implements Runnable {

    private String name;

    public Pony(String name) {
	this.name = name;
    }

    @Override
    public void run() {
	for (int i = 0; i < 10; i++) {
	    System.out.println(this.name + " runs gracefully " + (i + 1) * 10 + "m");
	}
	System.out.println("----------- " + this.name + " finished with pride!-----------");
    }


}
