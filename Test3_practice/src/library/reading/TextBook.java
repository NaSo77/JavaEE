package library.reading;

public class TextBook extends ReadingStuff implements Comparable<TextBook> {

    public TextBook(String name, String author, String publisher, String theme) {
	super(name, author, publisher, theme);
	this.rentalDuration = 150; // represents 150sekundi
	this.price = 2;
    }


    @Override
    public String toString() {
	return "[Name=" + getName() + ", Author=" + getAuthor() + ", Publisher="
		+ getPublisher() + ", Theme=" + getTheme() + "]";
    }

    @Override
    public int compareTo(TextBook o) {
	if (this.getTheme().equals(o.getTheme())) {
	    return this.getName().compareTo(o.getName());
	}
	return this.getTheme().compareTo(o.getTheme());
    }


}
