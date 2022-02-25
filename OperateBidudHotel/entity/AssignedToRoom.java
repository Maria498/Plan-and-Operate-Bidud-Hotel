package entity;

public class AssignedToRoom {
	
	private String roomNumber;
	private String hotelID;
	private String guestID; 
	private String checkInDate; 
	private String checkOutDate;

	
	public AssignedToRoom(String roomNumber, String hotelID, String guestID, String checkInDate, String checkOutDate) {
		super();
		this.roomNumber = roomNumber;
		this.hotelID = hotelID;
		this.guestID = guestID;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((checkInDate == null) ? 0 : checkInDate.hashCode());
		result = prime * result + ((checkOutDate == null) ? 0 : checkOutDate.hashCode());
		result = prime * result + ((guestID == null) ? 0 : guestID.hashCode());
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
		AssignedToRoom other = (AssignedToRoom) obj;
		if (checkInDate == null) {
			if (other.checkInDate != null)
				return false;
		} else if (!checkInDate.equals(other.checkInDate))
			return false;
		if (checkOutDate == null) {
			if (other.checkOutDate != null)
				return false;
		} else if (!checkOutDate.equals(other.checkOutDate))
			return false;
		if (guestID == null) {
			if (other.guestID != null)
				return false;
		} else if (!guestID.equals(other.guestID))
			return false;
		return true;
	}



	public String getRoomNumber() {
		return roomNumber;
	}



	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}



	public String getHotelID() {
		return hotelID;
	}



	public void setHotelID(String hotelID) {
		this.hotelID = hotelID;
	}



	public String getGuestID() {
		return guestID;
	}



	public void setGuestID(String guestID) {
		this.guestID = guestID;
	}



	public String getCheckInDate() {
		return checkInDate;
	}



	public void setCheckInDate(String checkInDate) {
		this.checkInDate = checkInDate;
	}



	public String getCheckOutDate() {
		return checkOutDate;
	}



	public void setCheckOutDate(String checkOutDate) {
		this.checkOutDate = checkOutDate;
	}



	@Override
	public String toString() {
		return "AssignedToRoom [roomNumber=" + roomNumber + ", hotelID=" + hotelID + ", guestID=" + guestID
				+ ", checkInDate=" + checkInDate + ", checkOutDate=" + checkOutDate + "]";
	}
	
	

}
