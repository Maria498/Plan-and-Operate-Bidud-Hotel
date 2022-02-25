package control;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.json.simple.JsonArray;
import org.json.simple.JsonObject;
import org.json.simple.Jsoner;

import entity.Consts;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ReportLogic {
	
	private static ReportLogic instance;
	
	public static ReportLogic getInstance() {
	        if (instance == null)
	            instance = new ReportLogic();
	        return instance;
	    }
	

//	public void initiateStatusReport() {
//		System.out.println("initiateStatusReport");
//		compileStatusReport().setVisible(true);	
//	}
	
	public void openScene(String window, Button button) {
  		button.getScene().getWindow().hide();
  		FXMLLoader loader = new FXMLLoader();
  		loader.setLocation(getClass().getResource(window));
  		 try {
	 			loader.load();
	 		} catch (IOException e) {
	 			e.printStackTrace();
	 		}
	 		Parent root = loader.getRoot();
	 		Stage stage = new Stage();
	 		stage.setScene(new Scene(root));
	 		stage.show();
 }
	
	public void exportCheckInsToJSON() {
 	   try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
                    PreparedStatement stmt = conn.prepareStatement(
                            Consts.SQL_SEL_ASSIGNED_TO_ROOM);
                    ResultSet rs = stmt.executeQuery()) {
         	   JsonArray checkIns = new JsonArray();
                while (rs.next()) {
             	   JsonObject obj = new JsonObject();

             	  for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++)
           		   obj.put(rs.getMetaData().getColumnName(i), rs.getString(i));
             	   
             	  checkIns.add(obj);
                }
                
         	   JsonObject doc = new JsonObject();
         	   doc.put("CheckIn_info", checkIns);
                
                File file = new File("json/CheckIns.json");
                file.getParentFile().mkdir();
                
                try (FileWriter writer = new FileWriter(file)) {
             	   writer.write(Jsoner.prettyPrint(doc.toJson()));
             	   writer.flush();
             	  showAlert("JSON Report Status", "CheckIns data exported successfully!");
                } catch (IOException e) {
             	   e.printStackTrace();
                }
            } catch (SQLException | NullPointerException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }	
 }

	  public static void showAlert(String headerTxt, String contentTxt) {
	    	Alert at = new Alert(Alert.AlertType.INFORMATION);
			at.setTitle("InformationDialogBox");
			at.setHeaderText(headerTxt);
			at.setContentText(contentTxt);
			at.show();
	    }

}
