package horseRacing;

public class Demo {
    public static void main(String[] args) throws InterruptedException {
	// Best Horse Names
	// RANDOLPH,ISABELLE,JULIET,SNOWBALL,PARIS

	// 1.extends Threat
	Horse h1 = new Horse("NAPOLEON");
	// 2. implements Runnable
	Pony h2 = new Pony("SNOWBALL");

	// 2.1 create new Thread from runnable object
	Thread h2Thread = new Thread(h2);

	// 3. anonymous class extends Thread and overriding run method
	Thread h3Thread = new Thread() {
	    @Override
	    public void run() {
		for (int i = 0; i < 10; i++) {
		    System.out.println("Donkey walk " + (i + 1) * 10 + "m");
		}
		System.out.println("----------- Donkey walked to finish!-----------");
	    }
	};

	// 4. anonymous Runnable class and overriding run method
	Thread h4Thread = new Thread(new Runnable() {
	    @Override
	    public void run() {
		for (int i = 0; i < 10; i++) {
		    System.out.println("Mare walk " + (i + 1) * 10 + "m");
		}
		System.out.println("----------- Mare finish!-----------");
	    }
	});

	// 5. Lambda expression of the Runnable interface
	Thread h5Thread = new Thread(() -> {
	    for (int i = 0; i < 10; i++) {
		System.out.println("Unicorn flyes " + (i + 1) * 10 + "m");
	    }
	    System.out.println("----------- Unicorn finish!-----------");
	});

	System.out.println("3");
	Thread.sleep(1000);
	System.out.println("2");
	Thread.sleep(1000);
	System.out.println("1");
	Thread.sleep(1000);
	System.out.println("Start of race!");

	// start threads
	h1.start();
	h2Thread.start();
	h3Thread.start();
	h4Thread.start();
	h5Thread.start();

	// current (in this case main) thread wait other thread to finish
	h1.join();
	h2Thread.join();
	h3Thread.join();
	h4Thread.join();
	h5Thread.join();

	System.out.println("End of race!");
    }
}
