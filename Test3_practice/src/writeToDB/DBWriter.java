package writeToDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

public class DBWriter {

    // private static final String DB_IP = "192.168.8.22";
    // private static final String DB_PORT = "3306";
    // private static final String DB_DBNAME = "hr";
    // private static final String DB_USER = "ittstudent";
    // private static final String DB_PASS = "ittstudent-123";

    private static final String DB_IP = "localhost";
    private static final String DB_PORT = "3306";
    private static final String DB_DBNAME = "hr";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "admin";

    private static final String NO_SSL_WARNING = "?autoReconnect=true&useSSL=false";

    public static void main(String[] args) {

	try {
	    Class.forName("com.mysql.jdbc.Driver");
	} catch (ClassNotFoundException e1) {
	    e1.printStackTrace();
	}

	String connectionURL = "jdbc:mysql://" + DB_IP + ":" + DB_PORT + "/" + DB_DBNAME + NO_SSL_WARNING;

	try (Connection connection = DriverManager.getConnection(connectionURL, DB_USER, DB_PASS);) {

	    PreparedStatement stmt = connection.prepareStatement(
		    "INSERT INTO javari (name) VALUES (?)",
		    Statement.RETURN_GENERATED_KEYS);
	    stmt.setString(1, LocalDateTime.now().toString());
	    stmt.executeUpdate();

	} catch (SQLException e) {
	    e.printStackTrace();
	}
    }

}
