package boundary;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import entity.Users;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainMenuController {
	
	private static Users USER;
	
    public static Users getUser() {
		return USER;
	}

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button guestButton;

    @FXML
    private Button frontDeskButton;

    @FXML
    private Button managerButton;

    @FXML
    private Button cateringButton;

    @FXML
    private Button nurseButton;

    /**
     * 
     */
    @FXML
    void initialize() {
    	guestButton.setOnAction(event -> {
    		USER = Users.GUEST;
    		openScene("/boundary/LoginForm.fxml", guestButton);
    	});
    	managerButton.setOnAction(event -> {
    		USER = Users.INST_MANAGER;
    		openScene("/boundary/InstManagerMenu.fxml", managerButton);
    	});
    	nurseButton.setOnAction(event -> {
    		USER = Users.NURSE;
    		openScene("/boundary/LoginForm.fxml", nurseButton);
    	});
    	frontDeskButton.setOnAction(event -> {
    		USER = Users.FRONT_DESC;
    		openScene("/boundary/LoginForm.fxml", frontDeskButton);
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
