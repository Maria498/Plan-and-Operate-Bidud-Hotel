package entity;

import control.RequestLogic;

public class Hotel {

	private int id;
	private String hotelName;
	private String directorFName;
	private String directorLName;
	private String directorPhone;
	private String email;
	private int cityID;
	private int totalRooms;
	private String cityName;
	private int vacantRooms;
	
	
	public Hotel(int id, String hotelName, String directorFName, String directorLName, String directorPhone,
			String email, int cityID, int totalRooms) {
		super();
		this.id = id;
		this.hotelName = hotelName;
		this.directorFName = directorFName;
		this.directorLName = directorLName;
		this.directorPhone = directorPhone;
		this.email = email;
		this.cityID = cityID;
		for(City c: RequestLogic.getInstance().getCities()) {
			if(this.cityID==c.getId())
				this.cityName = c.getName();
		}
		this.totalRooms = totalRooms;
		this.setVacantRooms(checkVacancy());
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getHotelName() {
		return hotelName;
	}
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	public String getDirectorFName() {
		return directorFName;
	}
	public void setDirectorFName(String directorFName) {
		this.directorFName = directorFName;
	}
	public String getDirectorLName() {
		return directorLName;
	}
	public void setDirectorLName(String directorLName) {
		this.directorLName = directorLName;
	}
	public String getDirectorPhone() {
		return directorPhone;
	}
	public void setDirectorPhone(String directorPhone) {
		this.directorPhone = directorPhone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getCityID() {
		return cityID;
	}
	public void setCityID(int cityID) {
		this.cityID = cityID;
	}
	public int getTotalRooms() {
		return totalRooms;
	}
	public void setTotalRooms(int totalRooms) {
		this.totalRooms = totalRooms;
	}
	
	public String getCityName() {
		return cityName;
	}
	
	public int checkVacancy() {
		
		return totalRooms - RequestLogic.getInstance().getVacantRoomsByHotelID(this.getId());
		
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
		Hotel other = (Hotel) obj;
		if (id != other.id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Hotel [id=" + id + ", hotelName=" + hotelName + ", directorFName=" + directorFName + ", directorLName="
				+ directorLName + ", directorPhone=" + directorPhone + ", email=" + email + ", cityID=" + cityID
				+ ", totalRooms=" + totalRooms + "]";
	}

	public int getVacantRooms() {
		return vacantRooms;
	}

	public void setVacantRooms(int vacantRooms) {
		this.vacantRooms = vacantRooms;
	}



	
	
}
