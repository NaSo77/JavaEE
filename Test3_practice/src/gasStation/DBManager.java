package gasStation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

import gasStation.Car.FuelType;

public class DBManager {

    private static DBManager instance;

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
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	try {
	    con = DriverManager.getConnection("", "", "");
	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    /*
     * “station_loadings” и да съдържа следните колони: - kolonka_id - номера на
     * колонката за зареждане - fuel_type - тип гориво - бензин, дизел или газ -
     * fuel_quantity - заредено количество в литри - loading_time - време на
     * зареждане.
     */
    public void getAllLoadings() {
	String sql = "SELECT kolonka_id, fuel_type, fuel_quantity, loading_time FROM station_loadings";
	String sql2 = "SELECT kolonka_id, COUNT(*) FROM station_loadings GROUP BY kolonka_id HAVING Date(loading_time) = Date.now() ";
	String sql3 = "SELECT fuel_type, SUM(fuel_quantity) FROM station_loadings GROUP BY fuel_type";
	String sql4 = "SELECT fuel_type, SUM(fuel_quantity) FROM station_loadings GROUP BY fuel_type";

	PreparedStatement statement;
	try {
	    statement = con.prepareStatement(sql);
	    ResultSet result = statement.executeQuery();
	    TreeMap<String, TreeSet<Loading>> loadings = new TreeMap<>();
	    while (result.next()) {
		String kolonka = result.getString("kolonka_id");
		Loading l = new Loading(
			kolonka,
			result.getString("fuel_type"),
			result.getInt("fuel_quantity"),
			result.getTimestamp("loading_time").toLocalDateTime());
		if (!loadings.containsKey(kolonka)) {
		    loadings.put(kolonka, new TreeSet<>());
		}
		loadings.get(kolonka).add(l);
	    }
	    for (Map.Entry<String, TreeSet<Loading>> e : loadings.entrySet()) {
		System.out.println(e.getKey());
		for (Loading l : e.getValue()) {
		    System.out.println(l);
		}
	    }
	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }

    public void insertIntoDB(FuelType fuel, int amount, int kolonka, LocalDateTime date) {

	try {
	    String sql = "INSERT INTO loading_stations (fuel_type, fuel_quantity, kolonka_id, loading_time) VALUES (?, ?, ?, ?)";
	    PreparedStatement statement = con.prepareStatement(sql);
	    statement.setString(1, fuel.toString());
	    statement.setInt(2, amount);
	    statement.setString(3, "Kolonka " + kolonka);
	    statement.setTimestamp(4, Timestamp.valueOf(date));
	    statement.executeUpdate();
	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

    }
}
