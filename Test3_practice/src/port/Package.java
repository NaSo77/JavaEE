package port;

public class Package {
    private static int uniqueID = 1;
    private int id;

    public int getId() {
	return id;
    }

    public Package() {
	this.id = uniqueID++;
    }
}
