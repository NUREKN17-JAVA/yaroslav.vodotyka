package db;

import junit.framework.TestCase;

public class DaoFactoryTest extends TestCase {

	
	
	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testGetUserDao() {
		try {
			DaoFactory factory = DaoFactory.GetInstance();
			assertNotNull("Factory instance is null" ,factory);
			UserDao userDao = factory.GetUserDao();
			assertNotNull("Factory returned null" ,userDao);
		} catch (Exception e) {
			fail(e.toString());
		}
	}

}
