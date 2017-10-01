package library.reading;

import library.IllegalOpeartionException;

public class Magazine extends ReadingStuff implements Comparable<Magazine> {

    public Magazine(String name, String publisher, String category, int number, String date) {
	super(name, publisher, category, number, date);
    }

    @Override
    public void takeReading(String dateTaken) throws IllegalOpeartionException {
	throw new IllegalOpeartionException("Magazine can be read only in library");
    }

    @Override
    public int compareTo(Magazine o) {
	if (this.getName().equals(o.getName())) {
	    return this.getNumber() - o.getNumber();
	}
	return this.getName().compareTo(o.getName());
    }

    @Override
    public String toString() {
	return "[Name=" + getName() +
		", Publisher=" + getPublisher() +
		", Category=" + getCategory() +
		", Number=" + getNumber() +
		", Date=" + getDate() + "]";
    }

}
