package db;

import java.util.Collection;
import java.util.Date;
import java.util.Properties;

import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;

import nure.cs.vodotyka.usermanagment.User;

public class HsqlDbUserDaoTest extends DatabaseTestCase {

	HsqlDbUserDao dao;
	ConnectionFactory connectionFactory;
	
	public HsqlDbUserDaoTest(){
		
	}
	
	protected void setUp() throws Exception {
		super.setUp();
		dao = new HsqlDbUserDao(connectionFactory);
	}

	public void testCreateUser() {
		try {
			User user = new User();
			user.setFirstName("Yaroslav");
			user.setLastName("Vodotyka");
			user.setDateOfBirth(new Date());
			assertNull(user.getId());
			user = dao.CreateUser(user);
			assertNotNull(user);
			assertNotNull(user.getId());
		} catch (DatabaseException e) {
			fail(e.toString());
		}
		
	}
	
	public void testGetAll(){
		try {
			Collection resultSet = dao.GetAll();
			assertNotNull("Collection is null" ,resultSet);
			assertEquals("Collection has an unexpected size" ,2, resultSet.size());
		} catch (DatabaseException e) {
			fail(e.toString());
		}
	}

	protected IDatabaseConnection getConnection() throws Exception {
		Properties prop = new Properties();
		prop.load(getClass().getClassLoader().getResourceAsStream("HsqlDbTestSettings.properties"));
		
		String user = prop.getProperty("connection.user");
		String url = prop.getProperty("connection.url");
		String driver = prop.getProperty("connection.driver");
		String password = prop.getProperty("connection.password");
		
		connectionFactory = new ConnectionFactoryImpl(driver,url, user, password);
		return new DatabaseConnection(connectionFactory.CreateConnection());
	}

	protected IDataSet getDataSet() throws Exception {
		IDataSet dataSet = new XmlDataSet(getClass().getClassLoader().getResourceAsStream("userDataSet.xml"));
		return dataSet;
	}

}
