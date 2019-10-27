package nure.cs.vodotyka.usermanagment;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import junit.framework.Assert;
import junit.framework.TestCase;

public class UserTest extends TestCase {

	private User user;
	private Calendar calendar;
	
	protected void setUp() throws Exception {
		super.setUp();
		user = new User();
		
		calendar = Calendar.getInstance();
		calendar.set(2000, Calendar.MAY, 21);
	}

	public void testGetFullName(){
		user.setFirstName("Yaroslav");
		user.setLastName("Vodotyka");
		assertEquals("Yaroslav, Vodotyka", user.GetFullName());
	}
	
	public void testGetAge(){
		user.setDateOfBirth(calendar.getTime());
		assertEquals(19, user.GetAge());
	}
	
	public void testGetAgeWithMonthPrecise(){
		LocalDate currentDate = LocalDate.now();
		calendar.set(2000, currentDate.getMonthValue()+1, 21);
		user.setDateOfBirth(calendar.getTime());
		assertEquals(18, user.GetAge());
	}
	
	public void testGetAgeWithDaysPrecise(){
		LocalDate currentDate = LocalDate.now();
		calendar.set(2000, currentDate.getMonthValue(), currentDate.getDayOfMonth()+1);
		user.setDateOfBirth(calendar.getTime());
		assertEquals(18, user.GetAge());
	}
	
	public void testGetAgeOnCurrentDay(){
		LocalDate currentDate = LocalDate.now();
		calendar.set(2000, currentDate.getMonthValue(), currentDate.getDayOfMonth());
		user.setDateOfBirth(calendar.getTime());
		assertEquals(19, user.GetAge());
	}
	
	public void testGetAgeSpecific(){
		calendar.set(2000, Calendar.FEBRUARY, 29);
		user.setDateOfBirth(calendar.getTime());
		assertEquals(19, user.GetAge());
	}
}
