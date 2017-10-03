package producer_consumer.bob;

public class Gurne {

    private static final int MIN_BOB = 50;
    private static final int MAX_BOB = 200;

    private int bob = 100;// grams

    public synchronized void sipiBob() {
	while (this.bob >= MAX_BOB) {
	    try {
		this.wait();
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	}

	this.notifyAll();

	this.bob += 10;
	System.out.print(Thread.currentThread().getName() + " sipva.");
	System.out.println(" [Gyrneto veche e " + this.bob + "gr.]");
    }

    public synchronized void hapniBob() {
	while (this.bob <= MIN_BOB) {
	    try {
		this.wait();
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	}

	this.notifyAll();

	this.bob -= 10;
	System.out.print(Thread.currentThread().getName() + " hapva.");
	System.out.println(" [Gyrneto veche e " + this.bob + "gr.]");
    }
}
