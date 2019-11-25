package desktop;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class BrowsePanel extends JPanel implements ActionListener {

	private MainFrame parent;
	private JPanel buttonPanel;
	private JScrollPane tablePanel;
	private JTable userTable;
	
	private JButton addButton;
	private JButton editButton;
	private JButton detailButton;
	private JButton deleteButton;
	
	public BrowsePanel(MainFrame mainFrame) {
		parent = mainFrame;
		initialize();
	}

	private void initialize() {
		this.setName("browsePanel");
		this.setLayout(new BorderLayout());
		this.add(GetTablePanel(), BorderLayout.CENTER);
		this.add(GetButtonsPanel(), BorderLayout.SOUTH);
		
	}

	private JPanel GetButtonsPanel() {
		if(buttonPanel == null){
			buttonPanel = new JPanel();
			buttonPanel.add(GetAddButton(), null);
			buttonPanel.add(GetEditButton(), null);
			buttonPanel.add(GetDeleteButton(), null);
			buttonPanel.add(GetDetailButton(), null);
		}
		return buttonPanel;
	}

	private JButton GetDetailButton() {
		if(detailButton == null){
			detailButton = new JButton();
			detailButton.setText("Details");
			detailButton.setName("detailButton");
			detailButton.addActionListener(this);
		}
		return detailButton;
	}

	private JButton GetDeleteButton() {
		if(deleteButton == null){
			deleteButton = new JButton();
			deleteButton.setText("Delete");
			deleteButton.setName("deleteButton");
			deleteButton.addActionListener(this);
		}
		return deleteButton;
	}

	private JButton GetEditButton() {
		if(editButton == null){
			editButton = new JButton();
			editButton.setText("Edit");
			editButton.setName("editButton");
			editButton.addActionListener(this);
		}
		return editButton;
	}

	private JButton GetAddButton() {
		if(addButton == null){
			addButton = new JButton();
			addButton.setText("Add");
			addButton.setName("addButton");
			addButton.addActionListener(this);
		}
		return addButton;
	}

	private JScrollPane GetTablePanel() {
		if(tablePanel == null){
			tablePanel = new JScrollPane(GetUserTable());
		}
		return tablePanel;
	}

	private JTable GetUserTable() {
		if(userTable == null){
			userTable = new JTable();
			userTable.setName("userTable");
		}
		return userTable;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
