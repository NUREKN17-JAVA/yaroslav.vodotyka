package desktop;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {

	private static final int FRAME_HEIGHT = 600;
	private static final int FRAME_WIDTH = 800;
	private JPanel contentPanel;
	private JPanel browsePanel;
	
	public MainFrame(){
		super();
		initialize();
	}
	
	public static void main(String[] args) {
		MainFrame frame = new MainFrame();
		frame.setVisible(true);

	}

	private void initialize(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setTitle("User managment");
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
		return browsePanel;
	}
}
