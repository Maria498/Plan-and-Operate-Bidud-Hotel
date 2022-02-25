package entity;

public class Room {
	
	private int num;
	private int hotelID;
	private boolean isVacant;
	
	
	public Room(int num, int hotelID, boolean isVacant) {
		super();
		this.num = num;
		this.hotelID = hotelID;
		this.isVacant = isVacant;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getHotelID() {
		return hotelID;
	}
	public void setHotelID(int hotelID) {
		this.hotelID = hotelID;
	}
	public boolean isVacant() {
		return isVacant;
	}
	public void setVacant(boolean isVacant) {
		this.isVacant = isVacant;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + hotelID;
		result = prime * result + num;
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
		Room other = (Room) obj;
		if (hotelID != other.hotelID)
			return false;
		if (num != other.num)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Room [num=" + num + ", hotelID=" + hotelID + ", isVacant=" + isVacant + "]";
	}
	
	
}