package nure.cs.vodotyka.usermanagment;

import java.util.Calendar;
import java.util.Date;
import java.io.Serializable;
import java.time.*;

public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1823090563357131517L;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	private Long id;
	private String firstName;
	private String lastName;
	private Date dateOfBirth;
	
	//Custom methods
	public String GetFullName(){
		StringBuilder fullName = new StringBuilder();
		fullName.append(this.firstName);
		fullName.append(", ");
		fullName.append(this.lastName);
		
		return fullName.toString();
	}
	
	public int GetAge(){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateOfBirth);
		int yearOfBirth = calendar.get(Calendar.YEAR);
		int monthOfBirth = calendar.get(Calendar.MONTH);
		int dayOfBirth = calendar.get(Calendar.DATE);
		LocalDate birthDate = LocalDate.of(yearOfBirth,
				monthOfBirth, dayOfBirth);
		LocalDate currentDate = LocalDate.now();
		Period difference = Period.between(birthDate, currentDate);
		return difference.getYears();
	}
	
	//Ctors
	public User(){
		
	}
}
