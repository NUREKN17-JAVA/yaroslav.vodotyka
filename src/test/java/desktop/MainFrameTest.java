package desktop;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;

import junit.extensions.jfcunit.JFCTestCase;
import junit.extensions.jfcunit.JFCTestHelper;
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
		Find(JTable.class, "userTable");
		Find(JButton.class, "addButton");
		Find(JButton.class, "deleteButton");
		Find(JButton.class, "detailButton");
	}
	
	private Component Find(Class compClass, String name){
		NamedComponentFinder finder = new NamedComponentFinder(compClass, name);
		finder.setWait(0);
		Component comp = finder.find(mainFrame, 0);
		assertNotNull("Component was not found: " + name ,comp);
		return comp;
	}

}
