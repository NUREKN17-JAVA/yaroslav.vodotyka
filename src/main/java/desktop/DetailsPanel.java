package desktop;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import util.Messages;
import nure.cs.vodotyka.usermanagment.User;

public class DetailsPanel extends JPanel implements ActionListener {

	private MainFrame parent;
	private User user;
	
	private JLabel idLabel;
	private JLabel firstNameLabel;
	private JLabel lastNameLabel;
	private JLabel birthDateLabel;
	private JLabel ageLabel;
	private JButton cancelButton;
	
	private JPanel infoPanel;
	
	public void SetUser(User user){
		this.user = user;
		SetLabelsText();
	}
	
	public DetailsPanel(MainFrame mainFrame, User user){
		parent = mainFrame;
		this.user = user;
		initialize();
	}
	
	private void initialize(){
		this.setName("detailsPanel");
		this.setLayout(new BorderLayout());
		this.add(GetInfoPanel(), BorderLayout.CENTER);
		this.add(GetCancelButton(), BorderLayout.AFTER_LAST_LINE);
		SetLabelsText();
	}
	
	private JPanel GetInfoPanel(){
		if(infoPanel == null){
			infoPanel = new JPanel();
			infoPanel.setLayout(new GridLayout(5, 2));
			AddLabelField(infoPanel, "ID", GetIdLabel());
			AddLabelField(infoPanel, "First name", GetFirstNameLabel());
			AddLabelField(infoPanel, "Last name", GetLastNameLabel());
			AddLabelField(infoPanel, "Birth date", GetBirthDateLabel());
			AddLabelField(infoPanel, "Age", GetAgeLabel());
		}
		return infoPanel;
	}
	
	private JLabel GetIdLabel(){
		if(idLabel == null){
			idLabel = new JLabel();
			idLabel.setName("idLabel");
			
		}
		return idLabel;
	}
	
	private JLabel GetFirstNameLabel(){
		if(firstNameLabel == null){
			firstNameLabel = new JLabel();
			firstNameLabel.setName("firstNameLabel");
			
		}
		return firstNameLabel;
	}
	
	private JLabel GetLastNameLabel(){
		if(lastNameLabel == null){
			lastNameLabel = new JLabel();
			lastNameLabel.setName("lastNameLabel");
			
		}
		return lastNameLabel;
	}
	
	private JLabel GetBirthDateLabel(){
		if(birthDateLabel == null){
			birthDateLabel = new JLabel();
			birthDateLabel.setName("birthDateLabel");
			
		}
		return birthDateLabel;
	}
	
	private JLabel GetAgeLabel(){
		if(ageLabel == null){
			ageLabel = new JLabel();
			ageLabel.setName("ageLabel");
			
		}
		return ageLabel;
	}
	
	private void AddLabelField(JPanel panel, String text,
			JLabel outLabel) {
		JLabel label = new JLabel(text);
		label.setLabelFor(outLabel);
		panel.add(label);
		panel.add(outLabel);
		
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

	private void SetLabelsText(){
		String age = "" + user.GetAge();
		ageLabel.setText(age);
		
		String date = "";
		DateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
		date = formatter.format(user.getDateOfBirth());
		birthDateLabel.setText(date);
		
		lastNameLabel.setText(user.getLastName());
		
		firstNameLabel.setText(user.getFirstName());
		
		idLabel.setText(user.getId().toString());
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		this.setVisible(false);
		parent.ShowBrowsePanel();
	}
	
	
	
	
	
	
	
	
	
	
	
}
