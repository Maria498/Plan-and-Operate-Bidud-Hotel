package control;

import java.io.File;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import entity.Consts;
import entity.Consts.Manipulation;
import entity.CovidTest;
import entity.Nurse;
import entity.TakingTest;




public class CovidTestingLogic {

private static CovidTestingLogic instance;
	
	private CovidTestingLogic() {}
	
	public static CovidTestingLogic getInstance() {
		if (instance == null)
			instance = new CovidTestingLogic();
		return instance;
	}
	
	private HashMap<Date,HashSet<Nurse>> nursesToTest = new HashMap<>();
	private HashMap<Date,HashSet<String>> timesToTest = new HashMap<>();
	
	
    public void importTestResultFromXML(String path) {
    	try {
			Document doc = DocumentBuilderFactory.newInstance()
								.newDocumentBuilder().parse(new File(path));
			doc.getDocumentElement().normalize();
			NodeList nl = doc.getElementsByTagName("TakingTest");
			
			int errors = 0;
			for (int i = 0; i < nl.getLength(); i++) {
				if (nl.item(i).getNodeType() == Node.ELEMENT_NODE) {
					Element el = (Element) nl.item(i);
					TakingTest tt = new TakingTest(Integer.parseInt(el.getElementsByTagName("testNum").item(0).getTextContent()),
							Integer.parseInt(el.getElementsByTagName("nurseID").item(0).getTextContent()),
							Integer.parseInt(el.getAttribute("ID")),
							el.getElementsByTagName("testResult").item(0).getTextContent());
					if (!manipulateTest(tt, Manipulation.SQL_INSERT) && 
							!manipulateTest(tt, Manipulation.SQL_UPDATE))
						errors++;
				}
			}
			
			System.out.println((errors == 0) ? "customers data imported successfully!" : 
				String.format("customers data imported with %d errors!", errors));
			
		} catch (SAXException | IOException | ParserConfigurationException e) {
			e.printStackTrace();
		}
    } 
	
	
    public boolean manipulateTest(TakingTest tt, Manipulation manipulation) {
    	try {
    		Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
    		try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
    				CallableStatement stmt = conn.prepareCall(
    						(manipulation.equals(Manipulation.SQL_UPDATE)) ? 
    								Consts.SQL_UPDATE_TAKINGTEST : 
    									(manipulation.equals(Manipulation.SQL_INSERT)) ? 
    											Consts.SQL_INSERT_TAKINGTEST : 
    												Consts.SQL_DELETE_TAKINGTEST)) {
    			allocateTestsParams(stmt, tt, manipulation);
    			stmt.executeUpdate();
    			return true;
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
    	} catch (ClassNotFoundException e) {
    		e.printStackTrace();
    	}
    	
    	return false;
    }
    
    
    private void allocateTestsParams(CallableStatement stmt, TakingTest tt, Manipulation m) throws SQLException {
    	int i = 1;
    	
    	if (!m.equals(Manipulation.SQL_UPDATE)) {{
    		stmt.setInt(i++, tt.getTestNum());
    		stmt.setInt(i++, tt.getNurseID());
    
    	}
    		
    		if (m.equals(Manipulation.SQL_DELETE))
    			return;
    	}
    	
    	stmt.setInt(i++, tt.getGuestID());
    	
    	if (tt.getTestResult() == null)
    		stmt.setNull(i++, java.sql.Types.VARCHAR);
    	else
    		stmt.setString(i++, tt.getTestResult());
    	
    	
    	if (m.equals(Manipulation.SQL_UPDATE)) {
    		stmt.setInt(i++, tt.getTestNum());
    		stmt.setInt(i++, tt.getNurseID());
    	}
    }
	
	// exports tests from db to xml.

    public void exportTestResultsToXML() {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
                    PreparedStatement stmt = conn.prepareStatement(
                            Consts.SQL_SEL_TAKINGTEST);
                    ResultSet rs = stmt.executeQuery()) {
                // create document object.
                Document doc = DocumentBuilderFactory.newInstance()
                        .newDocumentBuilder().newDocument();
                
                // push root element into document object.
                Element rootElement = doc.createElement("CovidTest_info");
                rootElement.setAttribute("exportDate", LocalDateTime.now().toString());
                doc.appendChild(rootElement);
                
                while (rs.next()) {     // run on all tests records..
                    // create test element.
                    Element test = doc.createElement("TakingTest");
                    // assign key to takingtTest.
                    Attr attr = doc.createAttribute("ID");
                    attr.setValue(rs.getString(3));
                    test.setAttributeNode(attr);
                    
                    // push elements to test.
                    for (int i = 1 ; i <= rs.getMetaData().getColumnCount(); i++) {
                        Element element = doc.createElement(
                                rs.getMetaData().getColumnName(i)); // push element to doc.
                        rs.getObject(i); // for wasNull() check..
                        element.appendChild(doc.createTextNode(
                                rs.wasNull() ? "" : rs.getString(i)));  // set element value.
                        test.appendChild(element);  // push element to customer.
                    }
                    
                    // push customer to document's root element.
                    rootElement.appendChild(test);
                }
                
                // write the content into xml file
                
                DOMSource source = new DOMSource(doc);
                File file = new File("xml/CovidResults.xml");
                file.getParentFile().mkdir(); // create xml folder if doesn't exist.
                StreamResult result = new StreamResult(file);
                TransformerFactory factory = TransformerFactory.newInstance();
                
                // IF CAUSES ISSUES, COMMENT THIS LINE.
                factory.setAttribute("indent-number", 2);
                //
                
                Transformer transformer = factory.newTransformer();
                
                // IF CAUSES ISSUES, COMMENT THESE 2 LINES.
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
                //
                
                transformer.transform(source, result);
                
                System.out.println("testResults data exported successfully!");
            } catch (SQLException | NullPointerException | ParserConfigurationException
                    | TransformerException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public ArrayList<Nurse> getAllNurses() {
		ArrayList<Nurse> nurses = new ArrayList<Nurse>();
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        
			try {Connection conn = DriverManager.getConnection(Consts.CONN_STR);              
                PreparedStatement stmt = conn.prepareStatement(Consts.SQL_SEL_NURSES);
                ResultSet rs = stmt.executeQuery();	               
        
        		while (rs.next()) {
        			int i = 1;
        			nurses.add(new Nurse(rs.getInt(i++), rs.getString(i++), rs.getString(i++), rs.getDate(i++)));
        		}
        	} catch (SQLException e) {
            e.printStackTrace();
        	}
    	} catch (ClassNotFoundException e) {
        e.printStackTrace();
    	}
		return nurses;
    }
    
    public ArrayList<CovidTest> getAllCovidTests() {
		ArrayList<CovidTest> tests = new ArrayList<CovidTest>();
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        
			try {Connection conn = DriverManager.getConnection(Consts.CONN_STR);              
                PreparedStatement stmt = conn.prepareStatement(Consts.SQL_SEL_COVIDTEST);
                ResultSet rs = stmt.executeQuery();	               
        
        		while (rs.next()) {
        			int i = 1;
        			tests.add(new CovidTest(rs.getInt(i++), rs.getDate(i++), rs.getString(i++)));
        		}
        	} catch (SQLException e) {
            e.printStackTrace();
        	}
    	} catch (ClassNotFoundException e) {
        e.printStackTrace();
    	}
		return tests;
    }
    
    public boolean defineTestTimes(Date date, HashSet<String> times) {
        for(String t: times) {
        	insTestTimes(date, t);
        }
		return true;
    }
    	
    public boolean insTestTimes(Date date, String t) { 
		try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

            try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
                    CallableStatement stmt = conn.prepareCall(Consts.SQL_INS_COVIDTEST)) {
        
                int i = 1;

                stmt.setDate(i++, date);
                stmt.setString(i++, t);
                
                stmt.executeUpdate();
                return true;
            	
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
		System.out.println("failed to defined times");
        return false;
	}
    
    public boolean insTakingTest(int testNum, int nurseID, int guestID, String result) { 
		try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

            try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
                    CallableStatement stmt = conn.prepareCall(Consts.SQL_INSERT_TAKINGTEST)) {
        
                int i = 1;

                stmt.setInt(i++, testNum);
                stmt.setInt(i++, nurseID);
                stmt.setInt(i++, guestID);
                stmt.setString(i++, result);
                
                stmt.executeUpdate();
                return true;
            	
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
		System.out.println("failed to defined times");
        return false;
	}
    
    public boolean defineAvailableNurses(Date date) {
    	if(!nursesToTest.get(date).isEmpty() && !timesToTest.get(date).isEmpty()) {
    		ArrayList<Integer> testNums = new ArrayList<>();
    		for(CovidTest ct: this.getAllCovidTests()) {
    			if(ct.getTestDate().equals(date))
    				testNums.add(ct.getTestNum());
    		}
    		for(Nurse n: nursesToTest.get(date)) {
    			for(int t: testNums) {
    				defineConductsTest(n.getId(), t);
    			}
    		}
    	}
        System.out.println("Test nums defined to "+ nursesToTest.get(date).size()+" nurses successfully");
		return true;
    }

    public boolean defineConductsTest(int nurseId, int testId) { 
		try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

            try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
                    CallableStatement stmt = conn.prepareCall(Consts.SQL_INS_CONDUCTSTEST)) {
                int i = 1;
                stmt.setInt(i++, nurseId);
                stmt.setInt(i++, testId);
                stmt.executeUpdate();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
		System.out.println("failed to defined nums to nurse");
        return false;
	}
    
    public boolean defineNursesDetails(int id, String fName, String lName, Date date) { 
		try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

            try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
                    CallableStatement stmt = conn.prepareCall(Consts.SQL_INS_NURSE)) {
                int i = 1;
                stmt.setInt(i++, id);
                stmt.setString(i++, fName);
                stmt.setString(i++, lName);
                stmt.setDate(i++, date);
                
                stmt.executeUpdate();
                System.out.println("test times defined successfully");
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
		System.out.println("failed to defined times");
        return false;
	}
    
    public boolean removeNurse(int id) {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

            try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
                    CallableStatement stmt = conn.prepareCall(Consts.SQL_DEL_NURSE)) {
                stmt.setLong(1, id);
                stmt.executeUpdate();
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public HashMap<String,ArrayList<String>> defineTestAndNurse(Date date) { 
    	HashMap<Date,HashMap<String,ArrayList<String>>> hm = new HashMap<>();
    	try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

            try {Connection conn = DriverManager.getConnection(Consts.CONN_STR);
                PreparedStatement stmt = conn.prepareCall(Consts.SQL_SEL_TESTNURSE);
                stmt.setDate(1, date);
                ResultSet rs = stmt.executeQuery();	
                if(!hm.containsKey(date)) {
                	hm.put(date, new HashMap<String,ArrayList<String>>());
                }
                	while (rs.next()) {
                		 if(!hm.get(date).containsKey(rs.getString(1))) {
                         	hm.get(date).put(rs.getString(1), new ArrayList<String>());
                         }
                		int j = 1;
                		if(hm.get(date).containsKey(rs.getString(1))) {
                			hm.get(date).get(rs.getString(j++)).add(rs.getString(j++));
                		}
                		else
                			hm.get(date).put(rs.getString(j++),new ArrayList<String>());
                	}
            }
        	 catch (SQLException e) {
            e.printStackTrace();
        	}
    	} catch (ClassNotFoundException e) {
        e.printStackTrace();
    	}
		return hm.get(date);
	}
        
    
    
	public HashMap<Date, HashSet<Nurse>> getNursesToTest() {
		return nursesToTest;
	}

	public void setNursesToTest(HashMap<Date, HashSet<Nurse>> nursesToTest) {
		this.nursesToTest = nursesToTest;
	}

	public HashMap<Date, HashSet<String>> getTimesToTest() {
		return timesToTest;
	}

	public void setTimesToTest(HashMap<Date, HashSet<String>> timesToTest) {
		this.timesToTest = timesToTest;
	}



	
	
}
