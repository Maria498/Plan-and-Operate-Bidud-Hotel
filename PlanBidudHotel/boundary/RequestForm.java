package boundary;


import java.io.IOException;
import java.sql.Date;

import Utils.CountryStatus;
import Utils.RelationType;
import Utils.RequestStatus;
import control.RequestLogic;
import entity.Citizen;
import entity.Hotel;
import entity.Request;
import entity.RequestCitizens;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class RequestForm {
	
	
	ObservableList<Integer> requests = FXCollections.observableArrayList(RequestLogic.getInstance().getRequests().keySet());
	
	
	
	@FXML
    private TextField requestStatusField;

    @FXML
    private TextField countryStatusField;

    @FXML
    private TextField requestDateField;

    @FXML
    private TextField landingTimeField;

    @FXML
    private TableView<Citizen> requestTable;

    @FXML
    private TableColumn<Citizen, Integer> citizenIDTab;

    @FXML
    private TableColumn<Citizen, String> fnameTab;

    @FXML
    private TableColumn<Citizen, String> lnameTab;

    @FXML
    private TableColumn<Citizen, Date> bDateTab;

    @FXML
    private TableColumn<RequestCitizens, RelationType> relationTab;

    @FXML
    private TableView<Hotel> hotelsTable;

    @FXML
    private TableColumn<Hotel, Integer> hotIDTab;

    @FXML
    private TableColumn<Hotel, String> hotNameTab;

    @FXML
    private TableColumn<Hotel, String> hotCityTab;

    @FXML
    private TableColumn<Hotel, Integer> totalRoomsTab;

    @FXML
    private TableColumn<Hotel, Integer> vacantRoomsTab;

    @FXML
    private TextField flightStatusField;

    @FXML
    private TextField enteredIsrField;

    @FXML
    private TextField numOfRoomsField;

    @FXML
    private DatePicker checkInField;

    @FXML
    private DatePicker checkOutField;

    @FXML
    private Button sendReservationButton;

    @FXML
    private Button nextButton;

    @FXML
    private Button BackButton;

    @FXML
    private TextField pageField;

    @FXML
    private ComboBox<Integer> requestIDBox;

    @FXML
    private TextField requestPhoneField;

    @FXML
    private TextField landingDateField;

    @FXML
    private TextField requestEmailField;

    @FXML
    private TextField totalCitizensField;

    @FXML
    private Button homeButton;

    @FXML
    private Button requestButton;

    @FXML
    private Button hotelsButton;

    
    
    @FXML
    void initialize() {
    	hotelsButton.setOnAction(event -> {
    		openScene("/boundary/CreateReport.fxml", hotelsButton);
    	});
    	requestIDBox.setItems(requests);
    	
    	requestIDBox.setOnAction(event -> {
    		if(!requestIDBox.getSelectionModel().isEmpty()) {
				Request r = RequestLogic.getInstance().getRequests().get(requestIDBox.getSelectionModel().getSelectedItem());
				Request fdet = RequestLogic.getInstance().getFlightDetails().get(r.getFlightNo());
				Request cdet = RequestLogic.getInstance().getCountryDetails().get(fdet.getCountryID());
    			requestDateField.setText(String.valueOf(r.getRequestDate()));
    			requestStatusField.setText(String.valueOf(r.getStatus()));
    			countryStatusField.setText(String.valueOf(r.getCountry()));
    			flightStatusField.setText(String.valueOf(fdet.isFlightStatus()));
    			landingDateField.setText(String.valueOf(fdet.getLandingDate()));
    			landingTimeField.setText(String.valueOf(fdet.getLandingTime()));
    			requestEmailField.setText(String.valueOf(r.getEmail()));
    			requestPhoneField.setText(String.valueOf(r.getPhone()));
    			enteredIsrField.setText(String.valueOf(r.isEntryStatus()));
    			countryStatusField.setText(String.valueOf(cdet.getCountryStatus()));
    			totalCitizensField.setText(String.valueOf(RequestLogic.getInstance().getCitizensByRequestID(r.getRequestID()).size()));
    			
    			int choice = requestIDBox.getValue();
    			requestTable.getItems().clear();
    			ObservableList<Citizen> citizen = FXCollections.observableArrayList(RequestLogic.getInstance().getCitizensByRequestID(choice));
    			citizenIDTab.setCellValueFactory(new PropertyValueFactory<>("citizenID"));
    			fnameTab.setCellValueFactory(new PropertyValueFactory<>("firstName"));
    			lnameTab.setCellValueFactory(new PropertyValueFactory<>("lastName"));
    			bDateTab.setCellValueFactory(new PropertyValueFactory<>("birthday"));
    			requestTable.setItems(citizen);
    			
    			if(cdet.getCountryStatus().equals(CountryStatus.REQUIRED_QUARANTINE) && !r.getStatus().equals(RequestStatus.CLOSED)) {
    				ObservableList<Hotel> hotels = FXCollections.observableArrayList(RequestLogic.getInstance().getHotels());
    		    	hotIDTab.setCellValueFactory(new PropertyValueFactory<>("id"));
    				hotNameTab.setCellValueFactory(new PropertyValueFactory<>("hotelName"));
    				totalRoomsTab.setCellValueFactory(new PropertyValueFactory<>("totalRooms"));
    				hotCityTab.setCellValueFactory(new PropertyValueFactory<>("cityName"));
    				vacantRoomsTab.setCellValueFactory(new PropertyValueFactory<>("vacantRooms"));
    				hotelsTable.setItems(hotels);
    			} else
    				hotelsTable.getItems().clear();
    			
    		}
    	});
    	sendReservationButton.setOnAction(event -> {
    		if(requestStatusField.getText().equals(RequestStatus.PENDING.toString()) || 
    				requestStatusField.getText().equals(RequestStatus.QUARANTINE.toString()) ||
    				requestStatusField.getText().equals(RequestStatus.RESERVATION_DECLINED_BY_HOTEL.toString())) {
    			if(!requestIDBox.getSelectionModel().isEmpty() && !hotelsTable.getSelectionModel().isEmpty() && !numOfRoomsField.getText().isEmpty()
    					&& checkInField.getValue()!= null && checkOutField.getValue()!= null) {
    				int reqID = requestIDBox.getSelectionModel().getSelectedItem();
    				int hotelID = hotelsTable.getSelectionModel().getSelectedItem().getId();
    				int rooms = Integer.parseInt(numOfRoomsField.getText());
    				Date checkInDate = Date.valueOf(checkInField.getValue());
    				Date checkOutDate = Date.valueOf(checkOutField.getValue());
    		
    				if(RequestLogic.getInstance().assignToHotel(reqID, hotelID, rooms, checkInDate, checkOutDate, false)) {
    					RequestLogic.getInstance().updateReqStatus("RABH", reqID);
    					RequestLogic.getInstance().assignToRoom(hotelID, "Reserved");
    					showAlert("Reservation status", "Complete: request " + reqID +
    							" assigned to hotel " + hotelsTable.getSelectionModel().getSelectedItem().getHotelName());
    				} else showAlert("Reservation status", "Failed");
    			
    			} else showAlert("Missing details", "Please fill in all details");
    			
    		} else showAlert("Reservation status", "Already completed");
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
