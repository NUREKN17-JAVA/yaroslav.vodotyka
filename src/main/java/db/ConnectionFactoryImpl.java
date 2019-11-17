package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactoryImpl implements ConnectionFactory {

	public ConnectionFactoryImpl() {
		// TODO Auto-generated constructor stub
	}

	public Connection CreateConnection() throws DatabaseException {
		String url = "jdbc:hsqldb:file:db/usermanagment";
		String user = "sa";
		String password = "";
		String driver = "org.hsqldb.jdbcDriver";
		try{
			Class.forName(driver);
		}
		catch(ClassNotFoundException ex){
			throw new RuntimeException(ex);
		}
		
		try {
			return DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			throw new DatabaseException(e);
		}
	}

}