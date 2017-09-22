package homework;

import java.util.LinkedList;
import java.util.Queue;

public class Demo1 {
    public static void main(String[] args) {

	Queue<Runnable> carParts = new LinkedList<>();
	carParts.offer(new Frame());
	carParts.offer(new Engine());

	carParts.offer(new Tyre());
	carParts.offer(new Tyre());
	carParts.offer(new Tyre());
	carParts.offer(new Tyre());

	carParts.offer(new Seat());
	carParts.offer(new Seat());
	carParts.offer(new Seat());
	carParts.offer(new Seat());
	carParts.offer(new Seat());

	long start = System.currentTimeMillis();

	Thread t1 = new Thread(carParts.poll());
	Thread t2 = new Thread(carParts.poll());
	Thread t3 = new Thread(carParts.poll());

	t1.start();
	t2.start();
	t3.start();


	while (true) {
	    if (carParts.isEmpty()) {
		break;
	    }
	    if (!t1.isAlive()) {
		t1 = new Thread(carParts.poll());
		t1.start();
	    }
	    if (!t2.isAlive()) {
		t2 = new Thread(carParts.poll());
		t2.start();
	    }
	    if (!t3.isAlive()) {
		t3 = new Thread(carParts.poll());
		t3.start();
	    }
	}

	try {
	    t1.join();
	    t2.join();
	    t3.join();
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}

	long end = System.currentTimeMillis();

	System.out.println("ready in " + (end - start));

    }
}
