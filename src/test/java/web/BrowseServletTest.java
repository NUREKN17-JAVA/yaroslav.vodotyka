package web;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import nure.cs.vodotyka.usermanagment.User;

public class BrowseServletTest extends MockServletTestCase {

	protected void setUp() throws Exception {
		super.setUp();
		createServlet(BrowseServlet.class);
	}

	public void testBrowse(){
		User user = new User(new Long(1) ,"Yaroslav", "Vodotyka", new Date());
		List list = Collections.singletonList(user);
		getMockUserDao().expectAndReturn("GetAll", list);
		doGet();
		Collection collection = (Collection)getWebMockObjectFactory().getMockSession().getAttribute("users");
		assertNotNull("Couldn`t find list of users in session", collection);
		assertSame(list, collection);
	}
	
	public void testEdit(){
		User user = new User(new Long(1) ,"Yaroslav", "Vodotyka", new Date());
		getMockUserDao().expectAndReturn("GetUser", new Long(1), user);
		addRequestParameter("editButton", "Edit");
		addRequestParameter("id", "1");
		doPost();
		User userInsession = (User)getWebMockObjectFactory().getMockSession().getAttribute("user");
		assertNotNull("No user in session" ,userInsession);
		assertSame(user, userInsession);
	}
}
