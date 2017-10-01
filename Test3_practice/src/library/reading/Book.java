package library.reading;

public class Book extends ReadingStuff implements Comparable<Book> {

    public Book(String name, String author, String date, String publisher, String genre) {
	super(name, author, date, publisher, genre);
	this.rentalDuration = 300; // represents 300sekundi
	this.price = 2;
    }

    @Override
    public int compareTo(Book o) {
	if (this.getGenre().equals(o.getGenre())) {
	    if (this.getDate().equals(o.getDate())) {
		return 0;
	    }
	    return this.getDate().compareTo(o.getDate());
	}
	return this.getGenre().compareTo(o.getGenre());
    }

    @Override
    public String toString() {
	return "[Name=" + getName() +
		", Author=" + getAuthor() +
		", Date=" + getDate() +
		", Publisher=" + getPublisher() +
		", Genre=" + getGenre() + "]";
    }
}
