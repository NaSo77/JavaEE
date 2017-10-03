package port;

public class Package {
    private static int uniqueID = 1;
    private String name;

    public String getName() {
	return name;
    }

    public Package() {
	this.name = "Package" + uniqueID++;
    }
}
