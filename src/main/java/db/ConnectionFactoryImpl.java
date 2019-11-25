package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactoryImpl implements ConnectionFactory {

	private String url;
	
	private String driver;
	
	private String user;
	
	private String password;
	
	public ConnectionFactoryImpl() {
		// TODO Auto-generated constructor stub
	}

	public ConnectionFactoryImpl(String driver, String url, String user, String password){
		this.driver = driver;
		this.url = url;
		this.user = user;
		this.password = password;
	}

	public ConnectionFactoryImpl(Properties prop){
		this.user = prop.getProperty("connection.user");
		this.url = prop.getProperty("connection.url");
		this.driver = prop.getProperty("connection.driver");
		this.password = prop.getProperty("connection.password");
	}
	
	public Connection CreateConnection() throws DatabaseException {
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
