package port;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class DBManager {

    private static DBManager instance;
    private static String url = "jdbc:mysql://localhost:3306/port_shipments?autoReconnect=true&useSSL=false";
    private static Connection con;

    public static synchronized DBManager getInstance() {
	if (instance == null) {
	    instance = new DBManager();
	}
	return instance;
    }

    private DBManager() {
	try {
	    Class.forName("com.mysql.jdbc.Driver");
	} catch (ClassNotFoundException e) {
	    e.printStackTrace();
	}
	try {
	    con = DriverManager.getConnection(url, "root", "admin");
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }

    public void insertIntoDB(String boatID, String dockID, String craneID, LocalDateTime now, String packageID) {
	try {
	    String sql = "INSERT INTO port_shipments (boat_name, dock_id, crane_id, unloading_time,package_id) VALUES (?, ?, ?, ?,?)";
	    PreparedStatement statement = con.prepareStatement(sql);
	    statement.setString(1, boatID);
	    statement.setString(2, dockID);
	    statement.setString(3, craneID);
	    statement.setString(4, now.toString());
	    statement.setString(5, packageID);
	    statement.executeUpdate();
	} catch (SQLException e) {
	    e.printStackTrace();
	}

    }
}
