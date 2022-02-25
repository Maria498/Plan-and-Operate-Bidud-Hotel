package entity;

import java.sql.Date;

public class RoomReservation {

	private int requestID;
	private int hotelID;
	private int rooms;
	private Date checkInDate;
	private Date checkOutDate;
	private boolean isCheckIn;
	
	
	public RoomReservation(int requestID, int hotelID, int rooms, Date checkInDate, Date checkOutDate,
			boolean isCheckIn) {
		super();
		this.requestID = requestID;
		this.hotelID = hotelID;
		this.rooms = rooms;
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.isCheckIn = isCheckIn;
	}


	public int getRequestID() {
		return requestID;
	}


	public void setRequestID(int requestID) {
		this.requestID = requestID;
	}


	public int getHotelID() {
		return hotelID;
	}


	public void setHotelID(int hotelID) {
		this.hotelID = hotelID;
	}


	public int getRooms() {
		return rooms;
	}


	public void setRooms(int rooms) {
		this.rooms = rooms;
	}


	public Date getCheckInDate() {
		return checkInDate;
	}


	public void setCheckInDate(Date checkInDate) {
		this.checkInDate = checkInDate;
	}


	public Date getCheckOutDate() {
		return checkOutDate;
	}


	public void setCheckOutDate(Date checkOutDate) {
		this.checkOutDate = checkOutDate;
	}


	public boolean isCheckIn() {
		return isCheckIn;
	}


	public void setCheckIn(boolean isCheckIn) {
		this.isCheckIn = isCheckIn;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + requestID;
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
		RoomReservation other = (RoomReservation) obj;
		if (requestID != other.requestID)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "RoomReservation [requestID=" + requestID + ", hotelID=" + hotelID + ", rooms=" + rooms
				+ ", checkInDate=" + checkInDate + ", checkOutDate=" + checkOutDate + ", isCheckIn=" + isCheckIn + "]";
	}
	
	
	
	
	
}
