package library.reading;

import java.util.ArrayList;

import library.History;
import library.IllegalOpeartionException;

public abstract class ReadingStuff implements Runnable {

    private String name;
    private String author;
    private String date;
    private String publisher;
    private String genre;
    private String category;
    private int number;
    private String theme;
    protected int rentalDuration;
    protected double price;
    private double payingTax;
    private boolean isReturned = true;

    public String getTheme() {
	return theme;
    }

    public int getNumber() {
	return number;
    }

    public String getAuthor() {
	return author;
    }

    public String getPublisher() {
	return publisher;
    }

    public String getCategory() {
	return category;
    }

    public double getPrice() {
	return price;
    }

    public double getPayingTax() {
	return payingTax;
    }

    public boolean isReturned() {
	return isReturned;
    }

    public ArrayList<History> getTakingHistory() {
	return takingHistory;
    }

    public String getName() {
	return name;
    }

    public String getDate() {
	return date;
    }

    public String getGenre() {
	return genre;
    }
    public int getRentalDuration() {
	return rentalDuration;
    }

    @Override
    public void run() {
	this.isReturned = false;
	this.payingTax = this.price;
	try {
	    Thread.sleep(rentalDuration);
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
	if (!isReturned) {
	    chargeInterest();
	}
    }

    private void chargeInterest() {
	while (!isReturned) {
	    System.out.println(this.name + " not returned in time. Interest is charged.");
	    this.payingTax *= 1.01;
	    try {
		Thread.sleep(100);
	    } catch (InterruptedException e) {
		e.printStackTrace();
	    }
	}
    }


    private ArrayList<History> takingHistory = new ArrayList<>();

    public ReadingStuff(String name, String publisher, String category, int number, String date) {
	this.name = name;
	this.publisher = publisher;
	this.category = category;
	this.number = number;
	this.date = date;
    }

    public ReadingStuff(String name, String author, String publisher, String theme) {
	this.name = name;
	this.author = author;
	this.publisher = publisher;
	this.theme = theme;
    }

    public ReadingStuff(String name, String author, String date, String publisher, String genre) {
	this.name = name;
	this.author = author;
	this.date = date;
	this.publisher = publisher;
	this.genre = genre;
    }

    public void takeReading(String dateTaken) throws IllegalOpeartionException {
	History h = new History(dateTaken);
	this.takingHistory.add(h);
    }

    public void returnReading(String dateReturn) {
	this.isReturned = true;
	this.takingHistory.get(takingHistory.size() - 1).setDateReturned(dateReturn);
    }

    public double taxToPay() {
	return this.payingTax;
    }
}
