package entity;

import Utils.CountryStatus;

public class Country {
	
	private int id;
	private String country;
	private CountryStatus status;
	
	public Country(int id, String country, CountryStatus status) {
		super();
		this.id = id;
		this.country = country;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public CountryStatus getStatus() {
		return status;
	}

	public void setStatus(CountryStatus status) {
		this.status = status;
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
		Country other = (Country) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Country [id=" + id + ", country=" + country + ", status=" + status + "]";
	}
	
	

}
