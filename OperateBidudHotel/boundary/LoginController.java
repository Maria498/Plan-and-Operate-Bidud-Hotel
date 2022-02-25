package boundary;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import control.CheckInLogic;
import control.CovidTestingLogic;
import entity.Guest;
import entity.Hotel;
import entity.Nurse;
import entity.Users;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
	
	private static int HOTELNUM;
	
	public static int getHotelNum() {
		return HOTELNUM;
	}
	
	public static int GUESTID;
	
	public static int getguestID() {
		return GUESTID;
	}
	
	ObservableList<Hotel> hotels = FXCollections.observableArrayList(CheckInLogic.getInstance().getAllHotels());

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button logInBtn;
    
    @FXML
    private TextField idField;
    
    @FXML
    private ComboBox<Hotel> hotelBox;
    
    @FXML
    private Button backButton;


    @FXML
    void initialize() {
    	
    	if(MainMenuController.getUser().equals(Users.GUEST) || MainMenuController.getUser().equals(Users.NURSE)) {
    		hotelBox.setVisible(false);
    	}
    	if(MainMenuController.getUser().equals(Users.FRONT_DESC)) {
    		idField.setVisible(false);
    		hotelBox.setItems(hotels);
    	}
    	logInBtn.setOnAction(event -> {
    		if(MainMenuController.getUser().equals(Users.GUEST) && loginUser(idField.getText().trim()))
    		openScene("/boundary/GuestMenu.fxml", logInBtn);
    		if(MainMenuController.getUser().equals(Users.NURSE) && loginNurse(idField.getText().trim()))
        		openScene("/boundary/NurseMenu.fxml", logInBtn);
    		if(MainMenuController.getUser().equals(Users.FRONT_DESC) && !hotelBox.getSelectionModel().isEmpty()) {
    			HOTELNUM = hotelBox.getSelectionModel().getSelectedItem().getId();
    			System.out.println(HOTELNUM);
        		openScene("/boundary/CheckInForm.fxml", logInBtn);
    		}
    		
    	});
    	
    	backButton.setOnAction(event -> {
    		openScene("/boundary/MainMenu.fxml", backButton);
    	});
    	
    }
    
    public boolean loginUser(String id) throws NumberFormatException {
 			int idNum = 0;
 			try {
 				idNum = Integer.parseInt(id);
 				
 			} catch (NumberFormatException e) {
 				showAlert("Incorrect password input", "Try again");
 			}
 			Guest g = new Guest(idNum);
 			for(Guest guest: CheckInLogic.getInstance().getAllGuests()) {
 				if(g.getId() == guest.getId()) {
 					System.out.println("Authorized guest");
 					GUESTID = g.getId();
 					return true;
 					}
 			}
 			showAlert("There is no account associated with the credentials you've entered.", "Try again");
 			return false;
 	}
    
    public boolean loginNurse(String id) throws NumberFormatException {
			int idNum = 0;
			try {
				idNum = Integer.parseInt(id);
				
			} catch (NumberFormatException e) {
				showAlert("Incorrect password input", "Try again");
			}
			Nurse n = new Nurse(idNum);
			for(Nurse nu: CovidTestingLogic.getInstance().getAllNurses()) {
				if(n.getId() == nu.getId()) {
					System.out.println("Authorized nurse");
					return true;
					}
			}
			showAlert("There is no account associated with the credentials you've entered.", "Try again");
			return false;
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
    
    public static void showAlert(String headerTxt, String contentTxt) {
    	Alert at = new Alert(Alert.AlertType.INFORMATION);
		at.setTitle("InformationDialogBox");
		at.setHeaderText(headerTxt);
		at.setContentText(contentTxt);
		at.show();
    }
 	
    
}
