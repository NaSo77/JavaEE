package library;

public class Demo {
    public static void main(String[] args) {
	Library lib = new Library();
	lib.viewCatalog();
	Thread readingman = new Thread(new Human(lib));
	readingman.run();
    }
}
