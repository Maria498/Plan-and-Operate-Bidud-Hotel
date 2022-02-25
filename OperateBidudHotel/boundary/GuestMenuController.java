package boundary;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;

import java.util.ResourceBundle;

import control.CovidTestingLogic;
import entity.CovidTest;
import entity.Nurse;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;



public class GuestMenuController {

	Date date = Date.valueOf(LocalDate.now());
	ObservableList<String> times = FXCollections.observableArrayList(CovidTestingLogic.getInstance().defineTestAndNurse(date).keySet());
	ObservableList<Nurse> nurses = FXCollections.observableArrayList();
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backButton;

    @FXML
    private TextField testDateField;

    @FXML
    private Button saveButton;

    @FXML
    private ComboBox<String> testTimeBox;

    @FXML
    private ComboBox<Nurse> testNurseBox;
    
    @FXML
    private Button earlyCOButton;

    @FXML
    private CheckBox positiveTestCB;


    @FXML
    private DatePicker datePicker;


    @FXML
    void initialize() {
    	testTimeBox.setItems(times);
    	testTimeBox.setOnAction(event -> {
    	String choice = testTimeBox.getValue();
    	for(String s: CovidTestingLogic.getInstance().defineTestAndNurse(date).get(choice)){
    		for (Nurse n: CovidTestingLogic.getInstance().getAllNurses()) {
    			if(s.equals(n.getLastName()) && !nurses.contains(n))
    			nurses.add(n);
    		}
    	}
    	testNurseBox.setItems(nurses);
    	});
    	backButton.setOnAction(event -> {
    		openScene("/boundary/MainMenu.fxml", backButton);
    	});
    	earlyCOButton.setOnAction(event -> {
    		if(positiveTestCB.isSelected()) {
    			showAlert("Early Request for CheckOut", "Please go to the reception");
    		}
    		else
    			showAlert("Early Request for CheckOut", "Only for Covid-positive");
    	});
    	saveButton.setOnAction(event -> {
    		int testNum = 0;
    		int nurseID = 0;
    		if(testNurseBox.getValue()!=null) {
    			Nurse n = testNurseBox.getValue();
    			nurseID = n.getId();
    		}
    		int gID = LoginController.getguestID();
    		String result = "pending";
    		String time = testTimeBox.getValue();
    		for(CovidTest ct: CovidTestingLogic.getInstance().getAllCovidTests()) {
    			if(ct.getTestDate().equals(date) && time.equals(ct.getTestTime()))
    				testNum = ct.getTestNum();
    		}
    		if(testNum!=0 && nurseID!=0 && gID!=0) {
    			CovidTestingLogic.getInstance().insTakingTest(testNum, nurseID, gID, result);
    			showAlert("Complete", "You signed up for a test");
    		}
    		else
    			showAlert("Missing Fields", "Try again");
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
    public static void showAlert(String headerTxt, String contentTxt) {
    	Alert at = new Alert(Alert.AlertType.INFORMATION);
		at.setTitle("InformationDialogBox");
		at.setHeaderText(headerTxt);
		at.setContentText(contentTxt);
		at.show();
    } 
}
