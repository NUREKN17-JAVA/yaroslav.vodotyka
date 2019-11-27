package desktop;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import db.DatabaseException;
import nure.cs.vodotyka.usermanagment.User;
import util.Messages;

public class EditPanel extends JPanel implements ActionListener {

	private MainFrame parent;
	private JPanel buttonPanel;
	private JButton okButton;
	private JButton cancelButton;
	private JPanel fieldPanel;
	private JTextField firstNameField;
	private JTextField lastNameField;
	
	private User userToUpdate;
	
	public void SetUser(User user){
		userToUpdate = user;
		GetLastNameField().setText(userToUpdate.getLastName());
		GetFirstNameField().setText(userToUpdate.getFirstName());
	}
	
	public EditPanel(MainFrame mainFrame, User user){
		parent = mainFrame;
		SetUser(user);
		initialize();
	}
	
	private void initialize(){
		this.setName("editPanel");
		this.setLayout(new BorderLayout());
		this.add(GetFieldPanel(), BorderLayout.NORTH);
		this.add(GetButtonPanel(), BorderLayout.SOUTH);
	}

	private JPanel GetButtonPanel() {
		if(buttonPanel == null){
			buttonPanel = new JPanel();
			buttonPanel.add(GetOkButton(), null);
			buttonPanel.add(GetCancelButton(), null);
		}
		return buttonPanel;
	}

	private JButton GetOkButton() {
		if(okButton == null){
			okButton = new JButton();
			okButton.setText(Messages.getString("AddPanel.ok")); //$NON-NLS-1$
			okButton.setName("okButton"); //$NON-NLS-1$
			okButton.setActionCommand("ok"); //$NON-NLS-1$
			okButton.addActionListener(this);
		}
		return okButton;
	}
	
	private JButton GetCancelButton() {
		if(cancelButton == null){
			cancelButton = new JButton();
			cancelButton.setText(Messages.getString("AddPanel.cancel")); //$NON-NLS-1$
			cancelButton.setName("cancelButton"); //$NON-NLS-1$
			cancelButton.setActionCommand("cancel"); //$NON-NLS-1$
			cancelButton.addActionListener(this);
		}
		return cancelButton;
	}
	
	private Component GetFieldPanel() {
		if(fieldPanel == null){
			fieldPanel = new JPanel();
			fieldPanel.setLayout(new GridLayout(3, 2));
			AddLabelField(fieldPanel, Messages.getString("AddPanel.first_name"), GetFirstNameField()); //$NON-NLS-1$
			AddLabelField(fieldPanel, Messages.getString("AddPanel.last_name"), GetLastNameField()); //$NON-NLS-1$
		}
		return fieldPanel;
	}
	
	private void AddLabelField(JPanel panel, String text,
			JTextField textField) {
		JLabel label = new JLabel(text);
		label.setLabelFor(textField);
		panel.add(label);
		panel.add(textField);
		
	}

	private JTextField GetFirstNameField() {
		if(firstNameField == null){
			firstNameField = new JTextField();
			firstNameField.setName("firstNameField"); //$NON-NLS-1$
			firstNameField.setText(userToUpdate.getFirstName());
		}
		
		return firstNameField;
	}
	
	private JTextField GetLastNameField() {
		if(lastNameField == null){
			lastNameField = new JTextField();
			lastNameField.setName("lastNameField"); //$NON-NLS-1$
			lastNameField.setText(userToUpdate.getLastName());
		}
		
		return lastNameField;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equalsIgnoreCase("ok")){
			userToUpdate.setFirstName(GetFirstNameField().getText());
			userToUpdate.setLastName(GetLastNameField().getText());
			try {
				parent.GetDao().UpdateUser(userToUpdate);
			} catch (DatabaseException e1) {
				JOptionPane.showMessageDialog(this, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		this.setVisible(false);
		parent.ShowBrowsePanel();
	}
}
