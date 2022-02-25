package control;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import entity.Citizen;
import entity.City;
import entity.Consts;
import entity.Hotel;
import entity.Request;
import entity.RequestCitizens;
import entity.Room;


public class RequestLogic {
	
	 private static RequestLogic instance;

	    private RequestLogic() { }

	    public static RequestLogic getInstance() {
	        if (instance == null)
	            instance = new RequestLogic();
	        return instance;
	    }

	
	
	public HashMap<String, Request> getFlightDetails(){
		 ArrayList<Request> flightdet= new ArrayList<Request>();
	        try {
	            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
	            
	            try {Connection conn = DriverManager.getConnection(Consts.CONN_STR);
	                    PreparedStatement stmt = conn.prepareStatement(Consts.SQL_SEL_FlIGHTS);
	                    ResultSet rs = stmt.executeQuery();	               
	            		while (rs.next()) {
	            			int i = 1;
	            			flightdet.add(new Request(rs.getString(i++),
	            					rs.getString(i++), rs.getInt(i++), rs.getBoolean(i++), rs.getDate(i++), rs.getTime(i++)));
	            		}
	            	} catch (SQLException e) {
	                e.printStackTrace();
	            	}
	        	} catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	        HashMap<String, Request> fdetByfNum = new HashMap<>();
	        for(Request r: flightdet) {
	        	fdetByfNum.put(r.getFlightNo(), r);
	        }
	        return fdetByfNum;
	}
	
	
	public HashMap<Integer, Request> getRequests(){
		 ArrayList<Request> requests= new ArrayList<Request>();
	        try {
	            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
	            
	            try {Connection conn = DriverManager.getConnection(Consts.CONN_STR);
	                    PreparedStatement stmt = conn.prepareStatement(Consts.SQL_SEL_REQUESTS);
	                    ResultSet rs = stmt.executeQuery();	               
	            		while (rs.next()) {
	            			int i = 1;
	            			requests.add(new Request(rs.getInt(i++), rs.getLong(i++), rs.getString(i++),
	                    		rs.getString(i++), rs.getString(i++), rs.getBoolean(i++), rs.getString(i++),
	                    		rs.getBoolean(i++), rs.getDate(i++), rs.getTime(i++), rs.getDate(i++)));
	            		}
	            	} catch (SQLException e) {
	                e.printStackTrace();
	            	}
	        	} catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	        HashMap<Integer, Request> requestByID = new HashMap<>();
	        for(Request r: requests) {
	        	requestByID.put(r.getRequestID(), r);
	        }
	        return requestByID;
	}
	
	public HashMap<Integer, Request> getCountryDetails(){
		 ArrayList<Request> countrydet= new ArrayList<Request>();
	        try {
	            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
	            
	            try {Connection conn = DriverManager.getConnection(Consts.CONN_STR);
	                    PreparedStatement stmt = conn.prepareStatement(Consts.SQL_SEL_COUNTRIES);
	                    ResultSet rs = stmt.executeQuery();	               
	            		while (rs.next()) {
	            			int i = 1;
	            			countrydet.add(new Request(rs.getInt(i++), rs.getString(i++), rs.getString(i++)));
	            		}
	            	} catch (SQLException e) {
	                e.printStackTrace();
	            	}
	        	} catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	        HashMap<Integer, Request> conntryDetByCID = new HashMap<>();
	        for(Request r: countrydet) {
	        	conntryDetByCID.put(r.getCountryID(), r);
	        }
	        return conntryDetByCID;
	}
	
	
	public ArrayList<Citizen> getAllCitizens() {
		ArrayList<Citizen> allcitizens = new ArrayList<Citizen>();
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        
			try {Connection conn = DriverManager.getConnection(Consts.CONN_STR);              
                PreparedStatement stmt = conn.prepareStatement(Consts.SQL_SEL_CITIZENS);
                ResultSet rs = stmt.executeQuery();	               
        
        		while (rs.next()) {
        			int i = 1;
        			allcitizens.add(new Citizen(rs.getLong(i++), rs.getString(i++), rs.getString(i++), rs.getDate(i++)));
        		}
        	} catch (SQLException e) {
            e.printStackTrace();
        	}
    	} catch (ClassNotFoundException e) {
        e.printStackTrace();
    	}
		return allcitizens;
    }
    
    
	public  ArrayList<Citizen> getCitizensByRequestID(int id) {
		ArrayList<RequestCitizens> reqCitizens = new ArrayList<RequestCitizens>();
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            
            try {Connection conn = DriverManager.getConnection(Consts.CONN_STR);
                    PreparedStatement stmt = conn.prepareStatement(Consts.SQL_SEL_REQUEST_CITIZENS);
                    ResultSet rs = stmt.executeQuery();	               
                    
            		while (rs.next()) {
            			int i = 1;
            			reqCitizens.add(new RequestCitizens(rs.getLong(i++), rs.getInt(i++), rs.getString(i++)));
            		}
            		
            	} catch (SQLException e) {
                e.printStackTrace();
            	}
        	} catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        HashMap<Integer, ArrayList<Citizen>> citizensByReqID = new HashMap<>();
        for(Request rq: this.getRequests().values()) {
        	citizensByReqID.put(rq.getRequestID(), new ArrayList<Citizen>());
        	 for(Citizen c: this.getAllCitizens()) {
             	if(c.getCitizenID() == rq.getTouristID()) {
             		citizensByReqID.get(rq.getRequestID()).add(c);
             	}
        	 }
        }
             	for(RequestCitizens rc: reqCitizens) {
             		for(Citizen c: this.getAllCitizens()) {
             		if(c.getCitizenID() == rc.getRelativesID())
             			citizensByReqID.get(rc.getRequestID()).add(c);
             		}
             	}

        return citizensByReqID.get(id);
}
	
	public ArrayList<Hotel> getHotels(){
		 ArrayList<Hotel> hotels= new ArrayList<Hotel>();
	        try {
	            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
	            
	            try {Connection conn = DriverManager.getConnection(Consts.CONN_STR);
	                    PreparedStatement stmt = conn.prepareStatement(Consts.SQL_SEL_HOTELS);
	                    ResultSet rs = stmt.executeQuery();	               
	            		while (rs.next()) {
	            			int i = 1;
	            			hotels.add(new Hotel(rs.getInt(i++), rs.getString(i++), rs.getString(i++),
	                    		rs.getString(i++), rs.getString(i++), rs.getString(i++), rs.getInt(i++), rs.getInt(i++)));
	            		}
	            	} catch (SQLException e) {
	                e.printStackTrace();
	            	}
	        	} catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	     
	        return hotels;
	}
	public ArrayList<City> getCities(){
		 ArrayList<City> cities= new ArrayList<City>();
	        try {
	            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
	            
	            try {Connection conn = DriverManager.getConnection(Consts.CONN_STR);
	                    PreparedStatement stmt = conn.prepareStatement(Consts.SQL_SEL_CITIES);
	                    ResultSet rs = stmt.executeQuery();	               
	            		while (rs.next()) {
	            			int i = 1;
	            			cities.add(new City(rs.getInt(i++), rs.getString(i++)));
	            		}
	            	} catch (SQLException e) {
	                e.printStackTrace();
	            	}
	        	} catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }
	        
	        return cities;
	}
	public int getVacantRoomsByHotelID(int id){
		 ArrayList<Room> rooms= new ArrayList<Room>();
		try {
	            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
	            
	            try {Connection conn = DriverManager.getConnection(Consts.CONN_STR);
	                    PreparedStatement stmt = conn.prepareStatement(Consts.SQL_SEL_ROOMS);
	                    ResultSet rs = stmt.executeQuery();	               
	            		while (rs.next()) {
	            			int i = 1;
	            			rooms.add(new Room(rs.getInt(i++), rs.getInt(i++), rs.getString(i++)));
	            		}
	            	} catch (SQLException e) {
	                e.printStackTrace();
	            	}
	        	} catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }
		HashMap<Integer, ArrayList<Room>> roomsByID = new HashMap<>();
		for(Room r: rooms) {
			roomsByID.putIfAbsent(r.getHotelID(), new ArrayList<Room>());
			roomsByID.get(r.getHotelID()).add(r);
		}
	        return roomsByID.get(id).size();
	}
	
	public boolean assignToHotel(int requestID, int hotelID, int rooms, Date checkInDate, 
			Date checkOutDate, boolean isCheckIn) {
		try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

            try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
                    CallableStatement stmt = conn.prepareCall(Consts.SQL_INS_ROOM_RESERVATION)) {
                int i = 1;

                stmt.setInt(i++, requestID); 
                stmt.setLong(i++, hotelID);
                stmt.setInt(i++, rooms);
                if (checkInDate != null)
                	stmt.setDate(i++, new java.sql.Date(checkInDate.getTime()));
                else
                	stmt.setNull(i++, java.sql.Types.DATE);
                if (checkOutDate != null)
                	stmt.setDate(i++, new java.sql.Date(checkOutDate.getTime()));
                else
                	stmt.setNull(i++, java.sql.Types.DATE);
                stmt.setBoolean(i++, isCheckIn);
               
                stmt.executeUpdate();
                System.out.println("assigned to hotel");
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
		System.out.println("failed to assign");
        return false;
	}
	
	public boolean assignToRoom(int hotelID, String status) {
		try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

            try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
                    CallableStatement stmt = conn.prepareCall(Consts.SQL_INS_ROOM_TO_RESERVATION)) {
                int i = 1;

                stmt.setLong(i++, hotelID);
                stmt.setString(i++, status);
                
                stmt.executeUpdate();
                System.out.println("assigned to room");
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
		System.out.println("failed to assign to room");
        return false;
	}
        
        
	public boolean updateReqStatus (String status, int id) {
		try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

            try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
                    CallableStatement stmt = conn.prepareCall(Consts.SQL_UPD_REQUEST)) {
                int i = 1;
                if (status != null)
                    stmt.setString(i++, status);
                else
                    stmt.setNull(i++, java.sql.Types.VARCHAR);
                stmt.setInt(i++, id);
                
                stmt.executeUpdate();
                System.out.println("status updated");
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
		System.out.println("failed to update");
        return false;

  }
	
/*	public boolean addRequest(long touristID, String email, String phone, String flightNo, 
			boolean relatives, String status, boolean entryStatus, Date date, Time time, Date date2)  {
    	  try {
              Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

              try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
                      CallableStatement stmt = conn.prepareCall(Consts.SQL_INS_REQUEST)) {
                  int i = 1;

                  stmt.setLong(i++, touristID); 
                  if (email != null)
                      stmt.setString(i++, email);
                  else
                      stmt.setNull(i++, java.sql.Types.VARCHAR);
                  if (phone != null)
                      stmt.setString(i++, phone);
                  else
                      stmt.setNull(i++, java.sql.Types.VARCHAR);
                  if (flightNo != null)
                      stmt.setString(i++, flightNo);
                  else
                      stmt.setNull(i++, java.sql.Types.VARCHAR);
                      stmt.setBoolean(i++, relatives);
                  if (status != null)
                      stmt.setString(i++, status);
                  else
                      stmt.setNull(i++, java.sql.Types.VARCHAR);
                      stmt.setBoolean(i++, entryStatus);
                  if (date != null)
                      stmt.setDate(i++, new java.sql.Date(date.getTime()));
                  else
                      stmt.setNull(i++, java.sql.Types.DATE);
                  if (time != null)
                      stmt.setDate(i++, new java.sql.Date(time.getTime()));
                  else
                      stmt.setNull(i++, java.sql.Types.DATE);
                  if (date2 != null)
                      stmt.setDate(i++, new java.sql.Date(date2.getTime()));
                  else
                      stmt.setNull(i++, java.sql.Types.VARCHAR);
                  

                  stmt.executeUpdate();
                  return true;
              } catch (SQLException e) {
                  e.printStackTrace();
              }
          } catch (ClassNotFoundException e) {
              e.printStackTrace();
          }
          return false;

    } */


	

}
