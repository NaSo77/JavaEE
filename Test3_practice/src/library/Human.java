package library;

import java.util.Random;

import library.reading.ReadingStuff;

public class Human implements Runnable {

    private Library library;
    private ReadingStuff currentReading;

    public Human(Library library) {
	this.library = library;
    }

    @Override
    public void run() {
	// while (true) {
	    takeSomethingToRead();
	    startReading();
	    payRental();
	    returnBook();
	// }
    }

    private void returnBook() {
	this.library.returnRent(this.currentReading);
	this.currentReading = null;
	System.out.println("returning the book");
    }

    private void payRental() {
	double tax = this.currentReading.taxToPay();
	System.out.println("Pay tax: " + tax);
    }

    private void startReading() {
	int duration = new Random().nextInt(10) + 5; // 1-5sec
	try {
	    new Thread(this.currentReading).start();
	    Thread.sleep(duration);
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
    }

    private void takeSomethingToRead() {
	while (true) {
	    try {
		this.currentReading = this.library.takeRent(library.getRandomReading());
		break;
	    } catch (IllegalOpeartionException e) {
		System.out.println(e.getMessage());
	    }
	}
    }

}
