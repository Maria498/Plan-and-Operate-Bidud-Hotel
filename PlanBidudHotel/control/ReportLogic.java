package control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

import javax.swing.JFrame;

import entity.Consts;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.swing.JRViewer;

public class ReportLogic {
	
	private static ReportLogic instance;
	
	public static ReportLogic getInstance() {
	        if (instance == null)
	            instance = new ReportLogic();
	        return instance;
	    }
	

	public void initiateStatusReport() {
		System.out.println("initiateStatusReport");
		compileStatusReport().setVisible(true);	
	}
	
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

	
	
	public JFrame compileStatusReport() {
		try {
			Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");

			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR)) {
				JasperPrint print = JasperFillManager.fillReport(

						getClass().getResourceAsStream("../boundary/ReportHotels.jasper"), 
						new HashMap<String, Object>(), conn);
				JFrame frame = new JFrame("Hotel Status Report");
				frame.getContentPane().add(new JRViewer(print));
				frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				frame.pack();
				return frame;

			}
			catch (SQLException | JRException | NullPointerException e) {
				e.printStackTrace();
			}
		}
		catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("compileStatusReport");
		return null;
	}
}
