package db;

import junit.framework.TestCase;

public class DaoFactoryTest extends TestCase {

	DaoFactory factory = DaoFactory.GetInstance();
	
	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testGetUserDao() {
		try {
			assertNotNull("Factory instance is null" ,factory);
			UserDao userDao = factory.GetUserDao();
			assertNotNull("Factory returned null" ,userDao);
		} catch (Exception e) {
			fail(e.toString());
		}
	}

}
