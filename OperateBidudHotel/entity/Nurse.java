package entity;

import java.sql.Date;

public class Nurse {
	
	private int id;
	private String firstName;
	private String lastName;
	private Date vaccineDate;
	
	public Nurse(int id, String firstName, String lastName, Date vaccineDate) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.vaccineDate = vaccineDate;
	}
	

	public Nurse(int id) {
		super();
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public Date getVaccineDate() {
		return vaccineDate;
	}

	public void setVaccineDate(Date vaccineDate) {
		this.vaccineDate = vaccineDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Nurse other = (Nurse) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return  firstName + " " + lastName + ", vaccineDate="
				+ vaccineDate +  "\n";
	}
	
	

}
