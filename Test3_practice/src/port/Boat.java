package port;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Boat {

    private static int uniqueID = 1;
    private ArrayList<Package> packages = new ArrayList<>();
    private String name;
    private Harbor harbor;

    public List<Package> getPackages() {
	return Collections.unmodifiableList(packages);
    }

    public String getName() {
	return name;
    }

    public Boat() {
	this.name = "Boat" + uniqueID++;
	int numberPackages = new Random().nextInt(4) + 1;
	for (int i = 0; i < numberPackages; i++) {
	    this.packages.add(new Package());
	}
    }

    public void enter(Harbor harbor) {
	this.harbor = harbor;
	harbor.enterInHarbour(this);
    }

    public void leaveHarbour() {
	this.harbor.leaveHarbour(this);
	this.harbor = null;
    }

}
