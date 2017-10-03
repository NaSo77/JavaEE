package port;

public class Demo {
    public static void main(String[] args) {
	Harbor harbor = new Harbor();

	while (true) {
	    new Boat().enter(harbor);
	}
    }
}
