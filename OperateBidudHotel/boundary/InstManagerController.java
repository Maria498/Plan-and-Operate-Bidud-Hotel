package boundary;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import control.CovidTestingLogic;
import entity.Nurse;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class InstManagerController {
	
	ObservableList<Nurse> nurses = FXCollections.observableArrayList(CovidTestingLogic.getInstance().getAllNurses());

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button nurseRemoveButton;

    @FXML
    private Button backButton;

    @FXML
    private TextField idNurseField;

    @FXML
    private TextField fNameField;

    @FXML
    private TextField lNameField;

    @FXML
    private DatePicker vaccineDate;

    @FXML
    private Button saveNurseButton;

    @FXML
    private ComboBox<Nurse> nurseRemoveBox;

    @FXML
    private TextArea nurseRemoveArea;

    @FXML
    private Button sendXMLButton;
    
    @FXML
    private Button rcvXMLButton;
    


    @FXML
    void initialize() {
    	nurseRemoveBox.setItems(nurses);
    	backButton.setOnAction(event -> {
    		openScene("/boundary/MainMenu.fxml", backButton);
    	});
    	sendXMLButton.setOnAction(event -> {
    		CovidTestingLogic.getInstance().importTestResultFromXML("xml/CovidResults.xml");
    		showAlert("Covid test results", "XML report sent to OperateBidudHotel");
    	});
    	rcvXMLButton.setOnAction(event -> {
    		CovidTestingLogic.getInstance().exportTestResultsToXML();
    		showAlert("Covid test results", "XML report from OperateBidudHotel");
    	});
    	saveNurseButton.setOnAction(event -> {
    		int id = Integer.valueOf(idNurseField.getText());
    		String fn = fNameField.getText().trim();
    		String ln = lNameField.getText().trim();
    		Date d = Date.valueOf(vaccineDate.getValue());
    		if(id>0 && !fn.equals("") && !ln.equals("") && d!=null) {
    			CovidTestingLogic.getInstance().defineNursesDetails(id, fn, ln, d);
    			showAlert("Added staff","Nurse "+ fn + " "+ln+ "added succesfully");
    		}
    	});
    	nurseRemoveButton.setOnAction(event -> {
    		if(!nurseRemoveBox.getSelectionModel().isEmpty()) {
    			Nurse n = nurseRemoveBox.getValue();
    			if(CovidTestingLogic.getInstance().removeNurse(n.getId()))
    				showAlert("Deleted staff","Nurse "+ n.getFirstName() + " "+n.getLastName()+ " deleted succesfully");
    		}
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
