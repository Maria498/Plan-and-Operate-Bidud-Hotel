package boundary;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
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
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class NurseMenuController {
	
	ObservableList<Nurse> nurses = FXCollections.observableArrayList(CovidTestingLogic.getInstance().getAllNurses());
	ObservableList<Integer> hours = FXCollections.observableArrayList();
	ObservableList<Integer> min = FXCollections.observableArrayList();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button saveButton;

    @FXML
    private TextArea nurseTextField;

    @FXML
    private Button addTimeButton;

    @FXML
    private ComboBox<Nurse> nurseBox;
    
    @FXML
    private ComboBox<Integer> fromHourBox;

    @FXML
    private ComboBox<Integer> toHourBox;

    @FXML
    private ComboBox<Integer> everyMinuteBox;

    @FXML
    private Button addNurseButton;

    @FXML
    private Button backButton;
   
    
    
	HashSet<String> times = new HashSet<>();
    boolean validTimes;
	boolean validNurses;
	String time;
	Nurse n;
	Date date = Date.valueOf(LocalDate.now());

    @FXML
    void initialize() {

    	
    	for (int i=8; i<=20; i++) {
    		hours.add(i);
    	}
    	for (int i=10; i<=30; i=i+10) {
    		min.add(i);
    	}
    	fromHourBox.setItems(hours);
    	toHourBox.setItems(hours);
    	everyMinuteBox.setItems(min);
    	backButton.setOnAction(event -> {
    		openScene("/boundary/MainMenu.fxml", backButton);
    	});
    	
    	nurseBox.setItems(nurses);
    	addNurseButton.setOnAction(event -> {

    			if(!nurseBox.getSelectionModel().isEmpty()) {
    		    	validNurses = true;
    				n = nurseBox.getValue();
    				if(!CovidTestingLogic.getInstance().getNursesToTest().containsKey(date)) {
    					CovidTestingLogic.getInstance().getNursesToTest().put(date, new HashSet<Nurse>());
    					CovidTestingLogic.getInstance().getNursesToTest().get(date).add(n);
    				}
    				else 
    					CovidTestingLogic.getInstance().getNursesToTest().get(date).add(n);
    				if(!CovidTestingLogic.getInstance().getNursesToTest().isEmpty()) {
    					nurseTextField.setText(CovidTestingLogic.getInstance().getNursesToTest().values().toString());
    				}
    			}
    			else
        			showAlert("Missing nurse details", "Choose a nurse");
    	
    	});
    	

    	saveButton.setOnAction(event -> {
    		validTimes();
			CovidTestingLogic.getInstance().defineTestTimes(date, times);
        	if(validNurses && validTimes) {
        		CovidTestingLogic.getInstance().defineAvailableNurses(date);
        	}
        	else
        		System.out.println("not valid" +validNurses+ " "+validTimes);
        	});
    	
    }
    public boolean validTimes() {
			if(fromHourBox.getValue()!=null && toHourBox.getValue()!=null && everyMinuteBox.getValue()!=null) {
				int fhour = fromHourBox.getValue();
				int thour = toHourBox.getValue();
				int interval = everyMinuteBox.getValue();
				for(int h = fhour; h<=thour; h++) {
					for(int m = 0; m<60; m=m+interval) {
						String mm = "";
						if(m==0)
							mm = m+"0";
						else 
							mm = mm+m;
						String time = h +":" +mm;
						times.add(time);
						System.out.println(time);
					}
				}
				validTimes = true;
				if(!CovidTestingLogic.getInstance().getTimesToTest().containsKey(date)) {
					CovidTestingLogic.getInstance().getTimesToTest().put(date, new HashSet<String>());
					CovidTestingLogic.getInstance().getTimesToTest().get(date).addAll(times);
				}
				else {
					CovidTestingLogic.getInstance().getTimesToTest().get(date).addAll(times);
				}
				showAlert("Test times", "Test times defined successfully");
			}
			else
    			showAlert("Missing time", "Enter a time");
		return validTimes;
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
