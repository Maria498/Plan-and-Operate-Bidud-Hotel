package entity;

import java.sql.Date;

public class Citizen  {

	private long citizenID;
	private String firstName;
	private String lastName;
	private Date birthday;
	
	public Citizen(long citizenID, String firstName, String lastName, Date birthday) {
		super();
		this.citizenID = citizenID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthday = birthday;
	}

	public long getCitizenID() {
		return citizenID;
	}

	public void setCitizenID(long citizenID) {
		this.citizenID = citizenID;
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

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (citizenID ^ (citizenID >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Citizen other = (Citizen) obj;
		if (citizenID != other.citizenID)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Citizen [citizenID=" + citizenID + ", firstName=" + firstName + ", lastName=" + lastName + ", birthday="
				+ birthday + "]";
	}
	
	
	
}
