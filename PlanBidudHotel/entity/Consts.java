package entity;

import java.net.URLDecoder;

public class Consts {

	private Consts() {
        throw new AssertionError();
    }

   
    protected static final String DB_FILEPATH = getDBPath();
    public static final String CONN_STR = "jdbc:ucanaccess://"  + DB_FILEPATH + ";COLUMNORDER=DISPLAY";
    
    public static final String SQL_SEL_REQUESTS = "SELECT * FROM TblRequest";
    public static final String SQL_SEL_FlIGHTS = "SELECT * FROM TblFlight";
    public static final String SQL_SEL_COUNTRIES = "SELECT * FROM TblCountry";
    public static final String SQL_SEL_HOTELS = "SELECT * FROM TblHotel";
    public static final String SQL_SEL_CITIES = "SELECT * FROM TblCity";
    public static final String SQL_SEL_REQUEST_CITIZENS = "SELECT * FROM TblRequestCitizens";
    public static final String SQL_SEL_CITIZENS = "SELECT * FROM TblCitizen";
    public static final String SQL_SEL_ROOMS = "SELECT * FROM TblRoom";
    public static final String SQL_INS_REQUEST = "{ call qryInsRequest(?,?,?,?,?,?,?,?,?,?) }";
    public static final String SQL_UPD_REQUEST = "{ call qryUpdRequestStatus(?,?) }";
    public static final String SQL_INS_ROOM_RESERVATION = "{ call qryInsRoomReservation(?,?,?,?,?,?) }";
    public static final String SQL_INS_ROOM_TO_RESERVATION = "{ call qryInsRoomToReservation(?,?) }";
	
	
    private static String getDBPath() {
    	try {
    		String path = Consts.class.getProtectionDomain().getCodeSource().getLocation().getPath();
    		String decoded = URLDecoder.decode(path, "UTF-8");
    		if (decoded.contains(".jar"))
    		{
    			decoded = decoded.substring(0, decoded.lastIndexOf('/'));
    			System.out.println(decoded  + "==============================================");
    	
    			return decoded + "/database/PlanBidudHotel.accdb";}
    		else {
    			decoded = decoded.substring(0, decoded.lastIndexOf("PlanBidudHotel/"));
    			System.out.println(decoded);
    	
    			return decoded + "/PlanBidudHotel/src/entity/PlanBidudHotel.accdb";}
    			
    		
    	} catch (Exception e) {
    		e.printStackTrace();
    		return null;
    	}

    }
	public static String getPath(String s) {
		 try {
		String path = Consts.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		String decoded = URLDecoder.decode(path, "UTF-8");
		if (decoded.contains(".jar")) {
		 decoded = decoded.substring(0, decoded.lastIndexOf("/"))+"/"+s;
		return decoded;
		} else {
		 return s;
		}
		} catch (Exception e) {
		 e.printStackTrace();
		 return null;
		 
		}
		}

	
	
}
