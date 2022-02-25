package entity;


import Utils.RoomStatus;

public class Room {
	
	private int roomNo;
	private int hotelID;
	private RoomStatus status;
	private static final int ROOM_CAPACITY = 2;


	
	
	public Room(int roomNo, int hotelID, String status) {
		super();
		this.roomNo = roomNo;
		this.hotelID = hotelID;
		if(status.equals("Vacant"))
			this.status = RoomStatus.VACANT;
		else if(status.equals("Occupied"))
			this.status = RoomStatus.OCCUPIED;
		else if(status.equals("Reserved"))
			this.status = RoomStatus.RESERVED;
	} 
	


	public int getHotelID() {
		return hotelID;
	}
	public void setHotelID(int hotelID) {
		this.hotelID = hotelID;
	}
	public RoomStatus getStatus() {
		return status;
	}
	public void setStatus(RoomStatus status) {
		this.status = status;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + hotelID;
		result = prime * result + roomNo;
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
		if (roomNo != other.roomNo)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Room [roomNo=" + roomNo + ", hotelID=" + hotelID + ", status=" + status + ", roomCapacity="
				+ ROOM_CAPACITY + "]";
	}



	public int getROOM_CAPACITY() {
		return ROOM_CAPACITY;
	}
	
	
	

}
