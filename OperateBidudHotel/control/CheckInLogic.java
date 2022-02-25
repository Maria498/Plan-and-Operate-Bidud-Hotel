package control;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.Consts;
import entity.Guest;
import entity.Hotel;
import entity.Room;

public class CheckInLogic {

	
private static CheckInLogic instance;
	
	private CheckInLogic() {}
	
	public static CheckInLogic getInstance() {
		if (instance == null)
			instance = new CheckInLogic();
		return instance;
	}
	
	 public ArrayList<Guest> getAllGuests() {
			ArrayList<Guest> guests = new ArrayList<Guest>();
			try {
				Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
	        
				try {Connection conn = DriverManager.getConnection(Consts.CONN_STR);              
	                PreparedStatement stmt = conn.prepareStatement(Consts.SQL_SEL_GUESTS);
	                ResultSet rs = stmt.executeQuery();	               
	        
	        		while (rs.next()) {
	        			int i = 1;
	        			guests.add(new Guest(rs.getInt(i++), rs.getString(i++), rs.getString(i++), rs.getString(i++), rs.getString(i++)));
	        		}
	        	} catch (SQLException e) {
	            e.printStackTrace();
	        	}
	    	} catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    	}
			return guests;
	    }
	 
	 public ArrayList<Hotel> getAllHotels() {
			ArrayList<Hotel> hotels = new ArrayList<Hotel>();
			try {
				Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
	        
				try {Connection conn = DriverManager.getConnection(Consts.CONN_STR);              
	                PreparedStatement stmt = conn.prepareStatement(Consts.SQL_SEL_HOTELS);
	                ResultSet rs = stmt.executeQuery();	               
	        
	        		while (rs.next()) {
	        			int i = 1;
	        			hotels.add(new Hotel(rs.getInt(i++), rs.getString(i++), rs.getString(i++), rs.getInt(i++)));
	        		}
	        	} catch (SQLException e) {
	            e.printStackTrace();
	        	}
	    	} catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    	}
			return hotels;
	    }
	
	 public ArrayList<Integer> getRoomsByHotel(int hotelId) {
			ArrayList<Room> rooms = new ArrayList<Room>();
			ArrayList<Integer> roomNums = new ArrayList<Integer>();
			try {
				Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
	        
				try {Connection conn = DriverManager.getConnection(Consts.CONN_STR);              
	                PreparedStatement stmt = conn.prepareStatement(Consts.SQL_SEL_ROOMS);
	                ResultSet rs = stmt.executeQuery();	               
	        
	        		while (rs.next()) {
	        			int i = 1;
	        			rooms.add(new Room(rs.getInt(i++), rs.getInt(i++), rs.getBoolean(i++)));
	        		}
	        	} catch (SQLException e) {
	            e.printStackTrace();
	        	}
	    	} catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    	}
			for(Room r: rooms) {
				if(r.getHotelID()==hotelId && r.isVacant())
					roomNums.add(r.getNum());
			}
			return roomNums;
	    }
	
	 public boolean insGuest(int id, String firstName, String lastName, String phone, String foodPreference) { 
			try {
	            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

	            try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
	                    CallableStatement stmt = conn.prepareCall(Consts.SQL_INS_GUEST)) {
	        
	                int i = 1;

	                stmt.setInt(i++, id);
	                stmt.setString(i++, firstName);
	                stmt.setString(i++, lastName);
	                stmt.setString(i++, phone);
	                stmt.setString(i++, foodPreference);
	                
	                stmt.executeUpdate();
	                return true;
	            	
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }
			System.out.println("failed to insert " +id );
	        return false;
		}
	 
	 public boolean insQCitizen(int id, String email, String city, String hmo) { 
			try {
	            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

	            try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
	                    CallableStatement stmt = conn.prepareCall(Consts.SQL_INS_QCITIZEN)) {
	        
	                int i = 1;

	                stmt.setInt(i++, id);
	                stmt.setString(i++, email);
	                stmt.setString(i++, city);
	                stmt.setString(i++, hmo);
	                
	                stmt.executeUpdate();
	                return true;
	            	
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }
			System.out.println("failed to insert " +id );
	        return false;
		}
	 
	 public boolean insFamilyMember(int id, int qCitID, String relation) { 
			try {
	            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

	            try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
	                    CallableStatement stmt = conn.prepareCall(Consts.SQL_INS_FAMILY_MEMBER)) {
	        
	                int i = 1;

	                stmt.setInt(i++, id);
	                stmt.setInt(i++, qCitID);
	                stmt.setString(i++, relation);
	                
	                stmt.executeUpdate();
	                return true;
	            	
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }
			System.out.println("failed to insert: " +id );
	        return false;
		}
	
	 public boolean insAssignedToRoom(String roomNumber, String hotelID, String guestID, String checkInDate, String checkOutDate) { 
			try {
	            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

	            try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
	                    CallableStatement stmt = conn.prepareCall(Consts.SQL_INS_ASSIGNED_TO_ROOM)) {
	        
	                int i = 1;

	                stmt.setString(i++, roomNumber);
	                stmt.setString(i++, hotelID);
	                stmt.setString(i++, guestID);
	                stmt.setString(i++, checkInDate);
	                stmt.setString(i++, checkOutDate);
	                
	                stmt.executeUpdate();
	                return true;
	            	
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        }
			System.out.println("failed to insert to room: " +roomNumber );
	        return false;
		}
	 
		public boolean updateRoomStatus (boolean status, int roomNum, int hotelNum) {
			try {
	            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

	            try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
	                    CallableStatement stmt = conn.prepareCall(Consts.SQL_UPD_ROOM_STATUS)) {
	                int i = 1;

	                stmt.setBoolean(i++, status);
	                stmt.setInt(i++, roomNum);
	                stmt.setInt(i++, hotelNum);
	                
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
	
}
