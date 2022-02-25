package entity;

import java.sql.Date;
import java.sql.Time;

import Utils.CountryStatus;
import Utils.RequestStatus;

public class Request {
	
	private int requestID;
	private long touristID;
	private String email;
	private String phone;
	private String flightNo;
	private boolean relatives;
	private RequestStatus status;
	private boolean entryStatus;
	private Date entryDate;
	private Time entryTime;
	private Date requestDate;
	
	private boolean flightStatus;
	private String airline;
	private int countryID;
	private Date landingDate;
	private Time landingTime;
	
	private String country;
	private CountryStatus countryStatus;
	
	

	public Request(int requestID, long touristID, String email, String phone, String flightNo, boolean relatives, String status,
			boolean entryStatus, Date date, Time time, Date date2) {
		super();
		this.requestID = requestID;
		this.touristID = touristID;
		this.email = email;
		this.phone = phone;
		this.flightNo = flightNo;
		this.relatives = relatives;
		if(status.equals("pending"))
			this.status = RequestStatus.PENDING;
		else if(status.equals("approved"))
			this.status = RequestStatus.APPROVED;
		else if(status.equals("quarantine"))
			this.status = RequestStatus.QUARANTINE;
		else if(status.equals("RDBH"))
			this.status = RequestStatus.RESERVATION_DECLINED_BY_HOTEL;
		else if(status.equals("RABH"))
			this.status = RequestStatus.RESERVATION_APPROVED_BY_HOTEL;
		else if(status.equals("closed"))
			this.status = RequestStatus.CLOSED;
		else if(status.equals("frozen"))
			this.status = RequestStatus.FROZEN;
		this.entryStatus = entryStatus;
		this.entryDate = date;
		this.entryTime = time;
		this.requestDate = date2;
	}



	public Request(String flightNo, String airline, int countryID, boolean flightStatus, Date landingDate,
			Time landingTime) {
		super();
		this.flightNo = flightNo;
		this.flightStatus = flightStatus;
		this.airline = airline;
		this.countryID = countryID;
		this.landingDate = landingDate;
		this.landingTime = landingTime;
	}



	public Request(int countryID, String country, String countryStatus) {
		super();
		this.countryID = countryID;
		this.country = country;
		if(countryStatus.equals("Green"))
			this.setCountryStatus(CountryStatus.GREEN);
		else if(countryStatus.equals("RQ"))
			this.setCountryStatus(CountryStatus.REQUIRED_QUARANTINE);
		else if(countryStatus.equals("Red"))
			this.setCountryStatus(CountryStatus.RED);
	}



	
	public int getCountryID() {
		return countryID;
	}



	public void setCountryID(int countryID) {
		this.countryID = countryID;
	}



	public String getCountry() {
		return country;
	}



	public void setCountry(String country) {
		this.country = country;
	}



	public int getRequestID() {
		return requestID;
	}


	public void setRequestID(int requestID) {
		this.requestID = requestID;
	}


	public long getTouristID() {
		return touristID;
	}


	public void setTouristID(long touristID) {
		this.touristID = touristID;
	}

	
	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getFlightNo() {
		return flightNo;
	}


	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}


	public boolean isRelatives() {
		return relatives;
	}


	public void setRelatives(boolean relatives) {
		this.relatives = relatives;
	}


	public RequestStatus getStatus() {
		return status;
	}


	public void setStatus(RequestStatus status) {
		this.status = status;
	}


	public boolean isEntryStatus() {
		return entryStatus;
	}


	public void setEntryStatus(boolean entryStatus) {
		this.entryStatus = entryStatus;
	}


	public Date getEntryDate() {
		return entryDate;
	}


	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}


	public Time getEntryTime() {
		return entryTime;
	}


	public void setEntryTime(Time entryTime) {
		this.entryTime = entryTime;
	}


	public Date getRequestDate() {
		return requestDate;
	}


	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}


	public boolean isFlightStatus() {
		return flightStatus;
	}


	public void setFlightStatus(boolean flightStatus) {
		this.flightStatus = flightStatus;
	}


	public String getAirline() {
		return airline;
	}


	public void setAirline(String airline) {
		this.airline = airline;
	}


	public Date getLandingDate() {
		return landingDate;
	}


	public void setLandingDate(Date landingDate) {
		this.landingDate = landingDate;
	}


	public Time getLandingTime() {
		return landingTime;
	}


	public void setLandingTime(Time landingTime) {
		this.landingTime = landingTime;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + requestID;
		result = prime * result + (int) (touristID ^ (touristID >>> 32));
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
		Request other = (Request) obj;
		if (requestID != other.requestID)
			return false;
		if (touristID != other.touristID)
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Request [requestID=" + requestID + ", touristID=" + touristID + ", email=" + email + ", phone=" + phone
				+ ", flightNo=" + flightNo + ", relatives=" + relatives + ", status=" + status + ", entryStatus="
				+ entryStatus + ", entryDate=" + entryDate + ", entryTime=" + entryTime + ", requestDate=" + requestDate
				+ ", flightStatus=" + flightStatus + ", airline=" + airline + ", country=" + countryID + ", landingDate="
				+ landingDate + ", landingTime=" + landingTime + "]";
	}



	public CountryStatus getCountryStatus() {
		return countryStatus;
	}



	public void setCountryStatus(CountryStatus countryStatus) {
		this.countryStatus = countryStatus;
	}

	
	
}
