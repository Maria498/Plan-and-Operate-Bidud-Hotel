package entity;

import java.net.URLDecoder;

public class Consts {

	private Consts() {
        throw new AssertionError();
    }


    protected static final String DB_FILEPATH = getDBPath();
    public static final String CONN_STR = "jdbc:ucanaccess://"  + DB_FILEPATH + ";COLUMNORDER=DISPLAY";
    
    public static final String SQL_SEL_TAKINGTEST = "SELECT * FROM TblTakingTest";
    public static final String SQL_SEL_ASSIGNED_TO_ROOM = "SELECT * FROM TblAssignedTo";
    public static final String SQL_INSERT_TAKINGTEST = "{ call qryInsTakingTest(?,?,?,?) }";
    public static final String SQL_UPDATE_TAKINGTEST = "{ call qryUpdTakingTest(?,?,?,?) }";
	public static final String SQL_DELETE_TAKINGTEST = "{ call qryDeleteTakingTest(?) }";
    public static final String SQL_SEL_NURSES = "SELECT * FROM TblNurse";
    public static final String SQL_SEL_GUESTS = "SELECT * FROM TblGuest";
    public static final String SQL_SEL_HOTELS = "SELECT * FROM TblHotel";
    public static final String SQL_SEL_COVIDTEST = "SELECT * FROM TblCovidTest";
    public static final String SQL_INS_COVIDTEST = "{ call qryInsCovidTest(?,?) }";
    public static final String SQL_INS_NURSE = "{ call qryInsNurse(?,?,?,?) }";
    public static final String SQL_INS_GUEST = "{ call qryInsGuest(?,?,?,?,?) }";
    public static final String SQL_INS_QCITIZEN = "{ call qryInsQCitizen(?,?,?,?) }";
    public static final String SQL_INS_FAMILY_MEMBER = "{ call qryInsFamilyMember(?,?,?) }";
    public static final String SQL_INS_ASSIGNED_TO_ROOM = "{ call qryInsAssignedToRoom(?,?,?,?,?) }";
    public static final String SQL_INS_CONDUCTSTEST = "{ call qryInsConductsTest(?,?) }";
    public static final String SQL_DEL_NURSE = "{ call qryDelNurse(?) }";
    public static final String SQL_SEL_TEST_AND_NURSE = "{ call qryTestByDate(?) }";
    public static final String SQL_SEL_ROOMS = "SELECT * FROM TblRoom";
    public static final String SQL_UPD_ROOM_STATUS = "{ call qryUpdRoomStatus(?,?,?) }";
    public static final String SQL_SEL_TESTNURSE =" SELECT TblCovidTest.testTime,  TblNurse.lastName\r\n"
    		+ "    FROM TblNurse INNER JOIN (TblCovidTest INNER JOIN TblConductsTest ON TblCovidTest.testNum = TblConductsTest.testNum) ON TblNurse.ID = TblConductsTest.nurseID\r\n"
    		+ "    WHERE TblCovidTest.testDate = ?;";
   
    
    
    public enum Manipulation {
    	SQL_UPDATE, SQL_INSERT, SQL_DELETE;
    }
	
    private static String getDBPath() {
    	try {
    		String path = Consts.class.getProtectionDomain().getCodeSource().getLocation().getPath();
    		String decoded = URLDecoder.decode(path, "UTF-8");
    		if (decoded.contains(".jar"))
    		{
    			decoded = decoded.substring(0, decoded.lastIndexOf('/'));
    	
    			return decoded + "/database/OperateBidudHotel.accdb";}
    		else {
    			decoded = decoded.substring(0, decoded.lastIndexOf("OperateBidudHotel/"));
    	
    			return decoded + "/OperateBidudHotel/src/entity/OperateBidudHotel.accdb";}
    			
    		
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
