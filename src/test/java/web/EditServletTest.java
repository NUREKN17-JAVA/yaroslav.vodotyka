package web;

import java.text.DateFormat;
import java.util.Date;

import nure.cs.vodotyka.usermanagment.User;

public class EditServletTest extends MockServletTestCase {

	protected void setUp() throws Exception{
		super.setUp();
		createServlet(EditServlet.class);
	}
	
	public void testEdit(){
		Date date = new Date();
		User user = new User(new Long(1), "Yaroslav", "Vodotyka", date);
		getMockUserDao().expect("UpdateUser", user);
		
		addRequestParameter("id", "1");
		addRequestParameter("firstName", "Yaroslav");
		addRequestParameter("lastName", "Vodotyka");
		addRequestParameter("date", DateFormat.getDateInstance().format(date));
		addRequestParameter("okButton");
		doPost();
	}
}
