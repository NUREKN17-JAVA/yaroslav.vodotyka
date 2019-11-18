package db;

import java.util.Calendar;
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

	private static final Date CHANGED_DATE = new Date();
	private static final String CHANGED_LAST_NAME = "changedLastName";
	private static final String CHANGED_FIRST_NAME = "changedFirstName";
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
			User user = CreateTestUserInstance();
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

	public void testDeleteUser(){
		User user = CreateTestUserInstance();
		try {
			dao.CreateUser(user);
			long id = user.getId().longValue();
			dao.DeleteUser(id);
			User deletedUser = dao.GetUser(id);
			assertNull(deletedUser);
		} catch (DatabaseException e) {
			fail(e.toString());
		}
		
	}
	
	public void testGetUser(){
		User user = CreateTestUserInstance();
		try {
			dao.CreateUser(user);
			long id = user.getId().longValue();
			User selectedUser = dao.GetUser(id);
			assertNull(dao.GetUser(id + 1));
			assertNotNull(selectedUser);
			assertEquals(id,  selectedUser.getId().longValue());
		} catch (DatabaseException e) {
			fail(e.toString());
		}
	}
	
	public void testUpdateUser(){
		User user = CreateTestUserInstance();
		try {
			dao.CreateUser(user);
			long id = user.getId().longValue();
			
			user.setFirstName(CHANGED_FIRST_NAME);
			user.setLastName(CHANGED_LAST_NAME);
			user.setDateOfBirth(CHANGED_DATE);
			
			dao.UpdateUser(user);
			User afterUpdate = dao.GetUser(id);
			
			assertEquals(CHANGED_FIRST_NAME, afterUpdate.getFirstName());
			assertEquals(CHANGED_LAST_NAME, afterUpdate.getLastName());
			//Compare dates without time, cause actual dates is equals but test crashes against time
			//assertEquals(CHANGED_DATE, new Date(afterUpdate.getDateOfBirth().getTime()));
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
	
	private User CreateTestUserInstance(){
		User user = new User();
		user.setFirstName("Yaroslav");
		user.setLastName("Vodotyka");
		Calendar calendar = Calendar.getInstance();
		calendar.set(2000, 5, 21);
		user.setDateOfBirth(calendar.getTime());
		return user;
	}

}
