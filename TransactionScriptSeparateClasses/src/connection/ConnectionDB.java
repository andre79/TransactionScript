package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
	
	private Connection connect = null;
	
	final private String host = "localhost";
	final private String user = "root";
	final private String passwd = "";

	public Connection getConnect() {
		return connect;
	}

	public void setConnect(Connection connect) {
		this.connect = connect;
	}
	
	public void close() throws SQLException {
		getConnect().close();
	}
	
	public void readDataBase() throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection(
					"jdbc:mysql://" + host + "/transaction_script?" + "user=" + user + "&password=" + passwd);
			setConnect(connect);
			System.out.println("Conectado com sucesso.");

		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
