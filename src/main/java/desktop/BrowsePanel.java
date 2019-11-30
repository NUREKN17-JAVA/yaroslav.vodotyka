package desktop;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import nure.cs.vodotyka.usermanagment.User;
import db.DatabaseException;
import util.Messages;

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
		this.setName("browsePanel"); //$NON-NLS-1$
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
			detailButton.setText(Messages.getString("BrowsePanel.details")); //$NON-NLS-1$
			detailButton.setName("detailButton"); //$NON-NLS-1$
			detailButton.setActionCommand("details"); //$NON-NLS-1$
			detailButton.addActionListener(this);
		}
		return detailButton;
	}

	private JButton GetDeleteButton() {
		if(deleteButton == null){
			deleteButton = new JButton();
			deleteButton.setText(Messages.getString("BrowsePanel.delete")); //$NON-NLS-1$
			deleteButton.setName("deleteButton"); //$NON-NLS-1$
			deleteButton.setActionCommand("delete"); //$NON-NLS-1$
			deleteButton.addActionListener(this);
		}
		return deleteButton;
	}

	private JButton GetEditButton() {
		if(editButton == null){
			editButton = new JButton();
			editButton.setText(Messages.getString("BrowsePanel.edit")); //$NON-NLS-1$
			editButton.setName("editButton"); //$NON-NLS-1$
			editButton.setActionCommand("edit"); //$NON-NLS-1$
			editButton.addActionListener(this);
		}
		return editButton;
	}

	private JButton GetAddButton() {
		if(addButton == null){
			addButton = new JButton();
			addButton.setText(Messages.getString("BrowsePanel.add")); //$NON-NLS-1$
			addButton.setName("addButton"); //$NON-NLS-1$
			addButton.setActionCommand("add"); //$NON-NLS-1$
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
			userTable.setName("userTable"); //$NON-NLS-1$
		}
		return userTable;
	}

	public void InitTable(){
		UserTableModel model;
		try {
			model = new UserTableModel(parent.GetDao().GetAll());
		} catch (DatabaseException e) {
			model = new UserTableModel(new ArrayList());
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		userTable.setModel(model);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String actionCommand = e.getActionCommand();
		if(actionCommand.equalsIgnoreCase("add")){ //$NON-NLS-1$
			this.setVisible(false);
			parent.ShowAddPanel();
		}
		if(actionCommand.equalsIgnoreCase("edit")){
			int[] selectedRows = GetUserTable().getSelectedRows();
			if(selectedRows.length != 1){
				JOptionPane.showMessageDialog(this, "Select ONE user to edit.", "Alert", JOptionPane.WARNING_MESSAGE);
				return;
			}else{
				int rowIndex = selectedRows[0];
				Long userId = (Long)GetUserTable().getValueAt(rowIndex, 0);
				User user = null;
				try {
					user = parent.GetDao().GetUser(userId.longValue());
				} catch (DatabaseException e1) {
					JOptionPane.showMessageDialog(this, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
				this.setVisible(false);
				parent.ShowEditPanel(user);
			}
		}
		if(actionCommand.equalsIgnoreCase("details")){
			int[] selectedRows = GetUserTable().getSelectedRows();
			if(selectedRows.length != 1){
				JOptionPane.showMessageDialog(this, "Select ONE user to see information.", "Alert", JOptionPane.WARNING_MESSAGE);
				return;
			}else{
				int rowIndex = selectedRows[0];
				Long userId = (Long)GetUserTable().getValueAt(rowIndex, 0);
				User user = null;
				try{
					user = parent.GetDao().GetUser(userId.longValue());
				}catch(DatabaseException ex){
					JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
				this.setVisible(false);
				parent.ShowDetailsPanel(user);
			}
		}
		if(actionCommand.equalsIgnoreCase("delete")){
			int[] selectedRows = GetUserTable().getSelectedRows();
			if(selectedRows.length != 1){
				JOptionPane.showMessageDialog(this, "Select ONE user to delete.", "Alert", JOptionPane.WARNING_MESSAGE);
				return;
			}else{
				int rowIndex = selectedRows[0];
				Long userId = (Long)GetUserTable().getValueAt(rowIndex, 0);
				try{
					JOptionPane.
					parent.GetDao().DeleteUser(userId.longValue());
					InitTable();
					this.repaint();
				}catch(DatabaseException ex){
					JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}

}
