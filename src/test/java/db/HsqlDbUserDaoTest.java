package db;

import java.util.Date;

import nure.cs.vodotyka.usermanagment.User;
import junit.framework.TestCase;

public class HsqlDbUserDaoTest extends TestCase {

	HsqlDbUserDao dao;
	
	
	protected void setUp() throws Exception {
		super.setUp();
		dao = new HsqlDbUserDao();
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

}
