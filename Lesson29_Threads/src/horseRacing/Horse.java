package horseRacing;

public class Horse extends Thread {

    private String name;

    public Horse(String name) {
	this.name = name;
    }

    @Override
    public void run() {
	for (int i = 0; i < 10; i++) {
	    System.out.println(this.name + " runs " + (i + 1) * 10 + "m");
	}
	System.out.println("----------- " + this.name + " finished!-----------");
    }

}
