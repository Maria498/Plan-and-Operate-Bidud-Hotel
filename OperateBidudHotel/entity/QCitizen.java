package entity;

public class QCitizen {
	
	private int id;
	private String email;
	private String city;
	private String hmo;
	
	public QCitizen(int id, String email, String city, String hmo) {
		super();
		this.id = id;
		this.email = email;
		this.city = city;
		this.hmo = hmo;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getHmo() {
		return hmo;
	}
	public void setHmo(String hmo) {
		this.hmo = hmo;
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
		QCitizen other = (QCitizen) obj;
		if (id != other.id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "QCitizen [id=" + id + ", email=" + email + ", city=" + city + ", hmo=" + hmo + "]";
	}
	
	

}
