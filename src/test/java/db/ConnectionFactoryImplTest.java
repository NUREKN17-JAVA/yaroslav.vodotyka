package db;

import java.sql.Connection;
import java.util.Properties;

import junit.framework.TestCase;

public class ConnectionFactoryImplTest extends TestCase {

	private ConnectionFactoryImpl target;
	private Properties prop;
	
	protected void setUp() throws Exception {
		super.setUp();
		prop = new Properties();
		prop.load(getClass().getClassLoader().getResourceAsStream("settings.properties"));
		
		String user = prop.getProperty("connection.user");
		String url = prop.getProperty("connection.url");
		String driver = prop.getProperty("connection.driver");
		String password = prop.getProperty("connection.password");
		
		target = new ConnectionFactoryImpl(driver,url, user, password);
	}

	public void testCreateConnection() {
		assertNotNull("ConnectionFactory is null" ,target);
		try {
			Connection connection = target.CreateConnection();
			assertNotNull("Factory didnt return the connection" ,connection);
		} catch (DatabaseException e) {
			fail(e.toString());
		}
		
	}

}
