package desktop;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import junit.extensions.jfcunit.JFCTestCase;
import junit.extensions.jfcunit.JFCTestHelper;
import junit.extensions.jfcunit.eventdata.MouseEventData;
import junit.extensions.jfcunit.finder.NamedComponentFinder;

public class MainFrameTest extends JFCTestCase {

	private MainFrame mainFrame;
	
	protected void setUp() throws Exception {
		super.setUp();
		setHelper(new JFCTestHelper());
		mainFrame = new MainFrame();
		mainFrame.setVisible(true);
	}

	protected void tearDown() throws Exception {
		mainFrame.setVisible(false);
		getHelper().cleanUp(this);
		super.tearDown();
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
		JButton addButton = (JButton)Find(JButton.class, "addButton");
		getHelper().enterClickAndLeave(new MouseEventData(this, addButton));
		
		Find(JPanel.class, "addPanel");
		
		Find(JTextField.class, "firstNameField");
		Find(JTextField.class, "lastNameField");
		Find(JTextField.class, "birthDateField");
		Find(JButton.class, "okButton");
		Find(JButton.class, "cancelButton");
	}
	
	private Component Find(Class compClass, String name){
		NamedComponentFinder finder = new NamedComponentFinder(compClass, name);
		finder.setWait(0);
		Component comp = finder.find(mainFrame, 0);
		assertNotNull("Component was not found: " + name ,comp);
		return comp;
	}

}
