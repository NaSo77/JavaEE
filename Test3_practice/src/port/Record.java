package port;

import java.time.LocalDate;
import java.time.LocalTime;

public class Record {

    private String packageID;
    private String dockID;
    private String boatID;
    private String craneID;
    private LocalDate date;
    private LocalTime time;

    public Record(String packageID, String dockID, String boatID, String craneID, LocalDate date, LocalTime time) {
	this.packageID = packageID;
	this.dockID = dockID;
	this.boatID = boatID;
	this.craneID = craneID;
	this.date = date;
	this.time = time;
    }

    @Override
    public String toString() {
	return "Record [packageID=" + packageID + ", dockID=" + dockID + ", boatID=" + boatID + ", craneID=" + craneID
		+ ", date=" + date + ", time=" + time + "]";
    }

}
