package gasStation1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import gasStation1.Car.FuelType;

public class DBManager {
    private static DBManager instance;

    private static final String DB_IP = "localhost";
    private static final String DB_PORT = "3306";
    private static final String DB_DBNAME = "gas_station";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "admin";
    private static final String NO_SSL_WARNING = "?autoReconnect=true&useSSL=false";
    private static final String connectionURL = "jdbc:mysql://" + DB_IP + ":" + DB_PORT + "/" + DB_DBNAME
	    + NO_SSL_WARNING;
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
	    con = DriverManager.getConnection(connectionURL, DB_USER, DB_PASS);
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }

    public String getInfoFromDB() {
	StringBuilder row = new StringBuilder();
	String sql = "SELECT gs.fuel_type, SUM(gs.fuel_quantity) AS total_liters FROM fueling_statistics AS gs GROUP BY fuel_type;";
	row.append("---------------------\n");
	PreparedStatement statement;
	try {
	    statement = con.prepareStatement(sql);
	    ResultSet result = statement.executeQuery();
	    while (result.next()) {
		String fuel_type = result.getString("fuel_type");
		int total_liters = result.getInt("total_liters");
		row.append(fuel_type + " : " + total_liters + "\n");
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return row.toString();
    }

    public void insertIntoDB(FuelType fuel, int amount, LocalDateTime date, String carName) {
	try {
	    String sql = "INSERT INTO fueling_statistics (fuel_type, fuel_quantity, fueling_time, car_name) VALUES (?, ?, ?,?)";
	    PreparedStatement statement = con.prepareStatement(sql);
	    statement.setString(1, fuel.toString());
	    statement.setInt(2, amount);
	    statement.setTimestamp(3, Timestamp.valueOf(date));
	    statement.setString(4, carName);
	    statement.executeUpdate();
	} catch (SQLException e) {
	    System.out.println("Something wrong with insertion in DB");
	    e.printStackTrace();
	}
    }
}
