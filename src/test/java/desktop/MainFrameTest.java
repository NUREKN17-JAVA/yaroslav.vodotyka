package desktop;

import java.awt.Component;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import nure.cs.vodotyka.usermanagment.User;

import com.mockobjects.dynamic.Mock;

import db.DaoFactory;
import db.DaoFactoryImpl;
import db.MockDaoFactory;
import db.MockUserDao;
import db.UserDao;
import junit.extensions.jfcunit.JFCTestCase;
import junit.extensions.jfcunit.JFCTestHelper;
import junit.extensions.jfcunit.eventdata.JTableMouseEventData;
import junit.extensions.jfcunit.eventdata.MouseEventData;
import junit.extensions.jfcunit.eventdata.StringEventData;
import junit.extensions.jfcunit.finder.NamedComponentFinder;

public class MainFrameTest extends JFCTestCase {

	private MainFrame mainFrame;
	private Mock mockUserDao;
	private ArrayList users;
	
	protected void setUp() throws Exception {
		super.setUp();
		try {
			Properties properties = new Properties();
			properties.setProperty("dao.factory", MockDaoFactory.class.getName());
			DaoFactory.init(properties);
			mockUserDao = ((MockDaoFactory)DaoFactory.GetInstance()).GetMockUserDao();
			
			users = new ArrayList();
			
			User user = new User();
			user.setId(new Long(1));
			user.setFirstName("Yaroslav");
			user.setLastName("Vodotyka");
			user.setDateOfBirth(new Date());
			users.add(user);
			mockUserDao.expectAndReturn("GetAll", users);
			
			setHelper(new JFCTestHelper());
			mainFrame = new MainFrame();
			mainFrame.SetDao((UserDao)mockUserDao.proxy());
		} catch (Exception e) {
			e.printStackTrace();
		}
		mainFrame.setVisible(true);
	}

	protected void tearDown() throws Exception {
		try {
			mockUserDao.verify();
			mainFrame.setVisible(false);
			getHelper().cleanUp(this);
			super.tearDown();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//Test methods here
	
	public void testBrowseControls(){
		Find(JPanel.class, "browsePanel");
		JTable table = (JTable)Find(JTable.class, "userTable");
		assertEquals(3, table.getColumnCount());
		assertEquals("ID", table.getColumnName(0));
		assertEquals("Name", table.getColumnName(1));
		assertEquals("LastName", table.getColumnName(2));
		
		Find(JButton.class, "addButton");
		Find(JButton.class, "deleteButton");
		Find(JButton.class, "detailButton");
	}
	
	public void testAddUser(){
		String firstName = "Yaroslav";
		String lastName = "Vodotyka";
		Date date = new Date();
		
		User user = new User();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setDateOfBirth(date);
		
		User expectedUser = new User();
		expectedUser.setId(new Long(1));
		expectedUser.setFirstName(firstName);
		expectedUser.setLastName(lastName);
		expectedUser.setDateOfBirth(date);
		
		mockUserDao.expectAndReturn("CreateUser", user, expectedUser);
		users.add(expectedUser);
		mockUserDao.expectAndReturn("GetAll", users);
		
		JTable table = (JTable)Find(JTable.class, "userTable");
		assertEquals(1, table.getRowCount());
		
		JButton addButton = (JButton)Find(JButton.class, "addButton");
		getHelper().enterClickAndLeave(new MouseEventData(this, addButton));
		
		Find(JPanel.class, "addPanel");
		
		JTextField firstNameField = (JTextField)Find(JTextField.class, "firstNameField");
		JTextField lastNameField = (JTextField)Find(JTextField.class, "lastNameField");
		JTextField birthDateField = (JTextField)Find(JTextField.class, "birthDateField");
		JButton okButton = (JButton)Find(JButton.class, "okButton");
		Find(JButton.class, "cancelButton");
		
		DateFormat formatter = DateFormat.getDateInstance();
		String dateStr = formatter.format(date);
		getHelper().sendString(new StringEventData(this, firstNameField, firstName));
		getHelper().sendString(new StringEventData(this, lastNameField, lastName));
		getHelper().sendString(new StringEventData(this, birthDateField, dateStr));
		
		getHelper().enterClickAndLeave(new MouseEventData(this, okButton));
		
		Find(JPanel.class, "browsePanel");
		table = (JTable)Find(JTable.class, "userTable");
		assertEquals(2, table.getRowCount());
	}
	
	public void testEditUser(){
		User expectedUser = new User();
		expectedUser.setId(new Long(1));
		expectedUser.setFirstName("Yaroslav");
		expectedUser.setLastName("Vodotyka");
		expectedUser.setDateOfBirth(new Date());
		
		mockUserDao.expect("UpdateUser", expectedUser);
		
		JTable table = (JTable)Find(JTable.class, "userTable");
		assertEquals(1, table.getRowCount());
		
		
		JButton editButton = (JButton)Find(JButton.class, "editButton");
		getHelper().enterClickAndLeave(new JTableMouseEventData(this, table, 0, 0, 1));
		getHelper().enterClickAndLeave(new MouseEventData(this, editButton));
		
		Find(JPanel.class, "editPanel");
		
		JTextField firstNameField = (JTextField)Find(JTextField.class, "firstNameField");
		JTextField lastNameField = (JTextField)Find(JTextField.class, "lastNameField");
		JTextField birthDateField = (JTextField)Find(JTextField.class, "birthDateField");
		
		getHelper().sendString(new StringEventData(this, firstNameField, "newFirst"));
		getHelper().sendString(new StringEventData(this, lastNameField, "newLast"));
		
		JButton okButton = (JButton)Find(JButton.class, "okButton");
		getHelper().enterClickAndLeave(new MouseEventData(this, okButton));
		
		Find(JPanel.class, "browsePanel");
		
		table = (JTable)Find(JTable.class, "userTable");
		assertEquals(1, table.getRowCount());
	}
	
	private Component Find(Class compClass, String name){
		NamedComponentFinder finder = new NamedComponentFinder(compClass, name);
		finder.setWait(0);
		Component comp = finder.find(mainFrame, 0);
		assertNotNull("Component was not found: " + name ,comp);
		return comp;
	}

}
