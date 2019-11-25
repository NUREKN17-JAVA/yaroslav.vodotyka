package desktop;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import util.Messages;

public class AddPanel extends JPanel implements ActionListener {

	private MainFrame parent;

	private JPanel buttonPanel;
	private JPanel fieldPanel;
	private JButton okButton;
	private JButton cancelButton;
	private JTextField birthDateField;
	private JTextField firstNameField;
	private JTextField lastNameField;
	
	public AddPanel(MainFrame mainFrame){
		this.parent = mainFrame;
		initialize();
	}

	private void initialize() {
		this.setName("addPanel"); //$NON-NLS-1$
		this.setLayout(new BorderLayout());
		this.add(GetFieldPanel(), BorderLayout.NORTH);
		this.add(GetButtonPanel(), BorderLayout.SOUTH);
		
	}

	private JPanel GetFieldPanel() {
		if(fieldPanel == null){
			fieldPanel = new JPanel();
			fieldPanel.setLayout(new GridLayout(3, 2));
			AddLabelField(fieldPanel, Messages.getString("AddPanel.first_name"), GetFirstNameField()); //$NON-NLS-1$
			AddLabelField(fieldPanel, Messages.getString("AddPanel.last_name"), GetLastNameField()); //$NON-NLS-1$
			AddLabelField(fieldPanel, Messages.getString("AddPanel.birth_date"), GetBirthDateField()); //$NON-NLS-1$
		}
		return fieldPanel;
	}

	private JTextField GetBirthDateField() {
		if(birthDateField == null){
			birthDateField = new JTextField();
			birthDateField.setName("birthDateField"); //$NON-NLS-1$
		}
		return birthDateField;
	}

	private JTextField GetLastNameField() {
		if(lastNameField == null){
			lastNameField = new JTextField();
			lastNameField.setName("lastNameField"); //$NON-NLS-1$
		}
		return lastNameField;
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
		}
		return firstNameField;
	}

	private JPanel GetButtonPanel() {
		if(buttonPanel == null){
			buttonPanel = new JPanel();
			buttonPanel.add(GetOkButton(), null);
			buttonPanel.add(GetCancelButton(), null);
		}
		return buttonPanel;
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
