package classes;

import java.sql.*;

public class Database {
	private Connection conn;
	private String dbURL;
	
	public Database(String db)
	{
		try {
			dbURL = "jdbc:mysql://localhost/" + db;
			conn = DriverManager.getConnection(dbURL,"root","");
			System.out.println("Connection Success!");
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public Connection getConnection()
	{
		return conn;
	}
	
	public ResultSet query(String query)
	{
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			return rs;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
