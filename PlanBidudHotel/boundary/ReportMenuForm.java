package boundary;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ReportMenuForm {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button mainMenuButton;

    @FXML
    private Button hotelReportButton;

    @FXML
    void initialize() {

    	mainMenuButton.setOnAction(event -> {
    		openScene("/boundary/RequestForm.fxml", mainMenuButton);
    	});
    	hotelReportButton.setOnAction(event -> {
    		openScene("/boundary/CreateReport.fxml", mainMenuButton);
    	});
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
	  	
}
