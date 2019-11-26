package desktop;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import db.DaoFactory;
import db.UserDao;
import util.Messages;

public class MainFrame extends JFrame {

	private static final int FRAME_HEIGHT = 600;
	private static final int FRAME_WIDTH = 800;
	private JPanel contentPanel;
	private JPanel browsePanel;
	private JPanel addPanel;
	private UserDao dao;
	
	public MainFrame(){
		super();
		SetDao(DaoFactory.GetInstance().GetUserDao());
		initialize();
	}
	
	public static void main(String[] args) {
		MainFrame frame = new MainFrame();
		frame.setVisible(true);

	}

	private void initialize(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setTitle(Messages.getString("MainFrame.user_managment")); //$NON-NLS-1$
		this.setContentPane(GetContentPanel());
	}
	
	private JPanel GetContentPanel(){
		if(contentPanel == null){
			contentPanel = new JPanel();
			contentPanel.setLayout(new BorderLayout());
			contentPanel.add(GetBrowsePanel());
		}
		return contentPanel;
	}
	
	private JPanel GetBrowsePanel(){
		if(browsePanel == null){
			browsePanel = new BrowsePanel(this);
		}
		((BrowsePanel)browsePanel).InitTable();
		return browsePanel;
	}
	
	private JPanel GetAddPanel(){
		if(addPanel == null){
			addPanel = new AddPanel(this);
			
		}
		return addPanel;
	}

	public void ShowAddPanel() {
		showPanel(GetAddPanel());
	}
	
	public void ShowBrowsePanel(){
		showPanel(GetBrowsePanel());
	}

	private void showPanel(JPanel panel) {
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setVisible(true);
		panel.repaint();
	}

	public UserDao GetDao() {
		return dao;
	}

	public void SetDao(UserDao dao) {
		this.dao = dao;
	}
}
