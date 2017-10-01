package library;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Random;
import java.util.TreeSet;

import library.reading.Book;
import library.reading.Magazine;
import library.reading.ReadingStuff;
import library.reading.TextBook;

public class Library {

    private HashMap<ReadingType, TreeSet<ReadingStuff>> catalog = new HashMap<>();
    private HashMap<ReadingStuff, LinkedList<History>> histories = new HashMap<>();

    public Library() {
	generateCatalog();
    }

    public ReadingStuff getRandomReading() {
	ArrayList<ReadingStuff> allReadingStuff = new ArrayList<>();
	for (TreeSet<ReadingStuff> read : this.catalog.values()) {
	    for (ReadingStuff readingStuff : read) {
		allReadingStuff.add(readingStuff);
	    }
	}
	return allReadingStuff.get(new Random().nextInt(allReadingStuff.size()));
    }

    private void generateCatalog() {
	this.catalog.put(ReadingType.BOOK, new TreeSet<>());
	this.catalog.get(ReadingType.BOOK).add(new Book("name1", "author1", "date1", "publisher1", "roman"));
	this.catalog.get(ReadingType.BOOK).add(new Book("name2", "author2", "date2", "publisher2", "roman"));
	this.catalog.get(ReadingType.BOOK).add(new Book("name3", "author3", "date3", "publisher3", "roman"));
	this.catalog.get(ReadingType.BOOK).add(new Book("name4", "author4", "date4", "publisher4", "thriller"));

	this.catalog.put(ReadingType.MAGAZINE, new TreeSet<>());
	this.catalog.get(ReadingType.MAGAZINE).add(new Magazine("name1", "publisher1", "fashion", 1, "date1"));
	this.catalog.get(ReadingType.MAGAZINE).add(new Magazine("name2", "publisher2", "scientific", 1, "date2"));
	this.catalog.get(ReadingType.MAGAZINE).add(new Magazine("name3", "publisher3", "scientific", 1, "date3"));
	this.catalog.get(ReadingType.MAGAZINE).add(new Magazine("name4", "publisher4", "scientific", 1, "date4"));

	this.catalog.put(ReadingType.TEXTBOOK, new TreeSet<>());
	this.catalog.get(ReadingType.TEXTBOOK).add(new TextBook("name1", "author1", "publisher1", "history"));
	this.catalog.get(ReadingType.TEXTBOOK).add(new TextBook("name2", "author2", "publisher2", "history"));
	this.catalog.get(ReadingType.TEXTBOOK).add(new TextBook("name3", "author3", "publisher3", "history"));
	this.catalog.get(ReadingType.TEXTBOOK).add(new TextBook("name4", "author4", "publisher4", "Programming"));
    }

    public void returnRent(ReadingStuff readingStuff) {
	String dateReturned = LocalDateTime.now().toString();
	readingStuff.returnReading(dateReturned);
	if (!this.histories.containsKey(readingStuff)) {
	    System.out.println("cannot return thing that is not rent");
	    return;
	}
	System.out.println(readingStuff + " has been returned");
    }

    public ReadingStuff takeRent(ReadingStuff readingStuff) throws IllegalOpeartionException {
	String dateTaken = LocalDateTime.now().toString();
	String dateReturned = LocalDateTime.now().plusDays(readingStuff.getRentalDuration()).toString();
	readingStuff.takeReading(dateTaken);
	if (!this.histories.containsKey(readingStuff)) {
	    this.histories.put(readingStuff, new LinkedList<>());
	}
	History history = new History(dateTaken);
	history.setDateReturned(dateReturned);
	this.histories.get(readingStuff).add(history);
	System.out.println(readingStuff + " has been taken");
	return readingStuff;
    }

    public void viewCatalog() {
	for (Entry<ReadingType, TreeSet<ReadingStuff>> entry : this.catalog.entrySet()) {
	    System.out.println(entry.getKey());
	    for (ReadingStuff readingStuff : entry.getValue()) {
		System.out.println(" - " + readingStuff);
	    }
	}
    }
}
