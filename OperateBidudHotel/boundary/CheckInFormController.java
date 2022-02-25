package boundary;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;

import control.CheckInLogic;
import control.ReportLogic;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CheckInFormController {
	
	int hotelID = LoginController.getHotelNum();
	ObservableList<String> cities = FXCollections.observableArrayList("Tel-Aviv", "Jerusalem", "Haifa");
	ObservableList<String> hmo = FXCollections.observableArrayList("Maccabi", "Clalit", "Meuhedet");
	ObservableList<String> meal = FXCollections.observableArrayList("vegetarian", "vegan", "gluten-free", "regular");
	ObservableList<Integer> rooms = FXCollections.observableArrayList(CheckInLogic.getInstance().getRoomsByHotel(hotelID));
	ObservableList<String> relation = FXCollections.observableArrayList("child", "partner");
	
	
    @FXML
    private AnchorPane familyMembersDetails;
    
    @FXML
    private ResourceBundle resources;
    
    @FXML
    private CheckBox famMembersCheckBox;

    @FXML
    private ComboBox<String> citiesBox;
    
    @FXML
    private URL location;

    @FXML
    private Button backButton;

    @FXML
    private TextField firstNameField;

    @FXML
    private ComboBox<String> cityCombo;

    @FXML
    private TextField lastNameField;

    @FXML
    private Button saveBtn;

    @FXML
    private TextField guestIDField;

    @FXML
    private TextField guestPhoneField;

    @FXML
    private TextField guestEmailField;

    @FXML
    private ComboBox<String> hmoCombo;

    @FXML
    private ComboBox<String> mealCombo;

    @FXML
    private TextField familyMemberIDField1;

    @FXML
    private TextField lNameFamilyField1;

    @FXML
    private TextField phoneFamilyMember1;

    @FXML
    private ComboBox<String> relationCombo1;

    @FXML
    private ComboBox<String> familyMemberMealCombo1;

    @FXML
    private TextField familyMemberIDField2;

    @FXML
    private TextField lNameFamilyField2;

    @FXML
    private TextField phoneFamilyMember2;

    @FXML
    private ComboBox<String> relationCombo2;

    @FXML
    private ComboBox<String> familyMemberMealCombo2;

    @FXML
    private TextField familyMemberIDField3;

    @FXML
    private TextField lNameFamilyField3;

    @FXML
    private TextField phoneFamilyMember3;

    @FXML
    private ComboBox<String> relationCombo3;

    @FXML
    private ComboBox<String> familyMemberMealCombo3;

    @FXML
    private TextField fNameFamilyField1;

    @FXML
    private TextField fNameFamilyField2;

    @FXML
    private TextField fNameFamilyField3;

    @FXML
    private ComboBox<Integer> roomsCombo;

    @FXML
    private ComboBox<Integer> roomsCombo1;
    
    @FXML
    private Text fmText;
    
    @FXML
    private Text fmText2;
    
    @FXML
    private Text fmText1;
    
    @FXML
    private Button checkInRepBtn;

    @FXML
    private Button checkOutRepBtn;

    @FXML
    void initialize() {
 
    	Date date = Date.valueOf(LocalDate.now());
		Date date2 = Date.valueOf(LocalDate.now().plusDays(14));
		String d1 = date.toString();
		String d2 = date2.toString();
		roomsCombo1.setVisible(false);
    	familyMembersDetails.setVisible(false);
    	familyMembersDetails.setManaged(false);
		roomsCombo.setItems(rooms);
		roomsCombo1.setItems(rooms);
		relationCombo1.setItems(relation);
		relationCombo2.setItems(relation);
		relationCombo3.setItems(relation);
		familyMemberMealCombo1.setItems(meal);
		familyMemberMealCombo2.setItems(meal);
		familyMemberMealCombo3.setItems(meal);
    	citiesBox.setItems(cities);
    	hmoCombo.setItems(hmo);
    	mealCombo.setItems(meal);
    	backButton.setOnAction(event -> {
    		openScene("/boundary/MainMenu.fxml", backButton);
    	});
    	famMembersCheckBox.setOnAction(event -> {
    		(familyMembersDetails).setVisible(true);
			(familyMembersDetails).setManaged(true);
			roomsCombo1.setVisible(true);
    	});
    	checkInRepBtn.setOnAction(event -> {
    		ReportLogic.getInstance().exportCheckInsToJSON();
    	});
    	saveBtn.setOnAction(event -> {
    		String hmo = "";
    		String city = "";
    		String meal = "";
    		String hotID = String.valueOf(hotelID);
    		int id = 0;
    		if(!guestIDField.getText().equals(""))
    			id = Integer.valueOf(guestIDField.getText());
    		String id1 = String.valueOf(id);
    		String fn = firstNameField.getText().trim();
    		String ln = lastNameField.getText().trim();
    		if(!citiesBox.getSelectionModel().isEmpty()) {
    			city = citiesBox.getValue();
    		}
    		String email = guestEmailField.getText().trim();
    		String phone = guestPhoneField.getText().trim();
    		if(!hmoCombo.getSelectionModel().isEmpty()) {
    			hmo = hmoCombo.getValue();
    		}
    		if(!mealCombo.getSelectionModel().isEmpty()) {
    			meal = mealCombo.getValue();
    		}
    		
    		if(id>0 && !fn.equals("") && !ln.equals("") && !email.equals("") && !phone.equals("") && !city.equals("") 
    				&& !hmo.equals("") && !meal.equals("") && !roomsCombo.getSelectionModel().isEmpty()) {
    			int roomNum = roomsCombo.getValue();
    			String rN = roomsCombo.getValue().toString();
    			if(CheckInLogic.getInstance().insGuest(id, fn, ln, phone, meal) &&
    			CheckInLogic.getInstance().insAssignedToRoom(rN, hotID, id1, d1, d2) &&
    			CheckInLogic.getInstance().insQCitizen(id, email, city, hmo)) {
    				System.out.println("Guest " + fn + " "+ ln + " assigned to room " +roomNum+ " till "+date2+ " in hotel " +hotelID);
    				CheckInLogic.getInstance().updateRoomStatus(false, roomNum, hotelID);
    			}
    		}
    		else
    			showAlert("Incorrect details", "Enter a valid details");
    		
    		if(!familyMemberIDField1.getText().equals("") && !fNameFamilyField1.getText().equals("") &&
    	        	!lNameFamilyField1.getText().equals("") &&
    	        	!familyMemberMealCombo1.getSelectionModel().isEmpty() &&
    	        	!relationCombo1.getSelectionModel().isEmpty() &&
    	        	!phoneFamilyMember1.getText().equals("")) {
    	    			int fmID = Integer.valueOf(familyMemberIDField1.getText());
    	        		String id2 = String.valueOf(fmID);
    	    			String fmfn = fNameFamilyField1.getText().trim();
    	        		String fmln = lNameFamilyField1.getText().trim();
    	        		String fmPhone = phoneFamilyMember1.getText().trim();
    	        		String relation = relationCombo1.getValue();
    	        		String fmMeal = familyMemberMealCombo1.getValue();
    	        		String roomNum = roomsCombo.getValue().toString();
    	        		if(CheckInLogic.getInstance().insGuest(fmID, fmfn, fmln, fmPhone, fmMeal) &&
    	            			CheckInLogic.getInstance().insAssignedToRoom(roomNum, hotID, id2, d1, d2) && 
    	            			CheckInLogic.getInstance().insFamilyMember(fmID, id, relation)) {
    	            				System.out.println("Guest " + fmfn + " "+ fmln + " assigned to room " +roomNum+ " till "+date2+ " in hotel " +hotelID);
    	            			}
    	    		}
    		
    		if(!familyMemberIDField2.getText().equals("") && !fNameFamilyField2.getText().equals("") &&
    	        	!lNameFamilyField2.getText().equals("") &&
    	        	!familyMemberMealCombo2.getSelectionModel().isEmpty() &&
    	        	!relationCombo2.getSelectionModel().isEmpty() &&
    	        	!phoneFamilyMember2.getText().equals("") && 
    	        	roomsCombo1.getValue()!=roomsCombo.getValue()) {
    	    			int fmID = Integer.valueOf(familyMemberIDField2.getText());
    	    			String id3 = String.valueOf(fmID);
    	    			String fmfn = fNameFamilyField2.getText().trim();
    	        		String fmln = lNameFamilyField2.getText().trim();
    	        		String fmPhone = phoneFamilyMember2.getText().trim();
    	        		String relation = relationCombo2.getValue();
    	        		String fmMeal = familyMemberMealCombo2.getValue();
    	        		String roomNum = roomsCombo1.getValue().toString();
    	        		if(CheckInLogic.getInstance().insGuest(fmID, fmfn, fmln, fmPhone, fmMeal) &&
    	            			CheckInLogic.getInstance().insAssignedToRoom(roomNum, hotID, id3, d1, d2) && 
    	            			CheckInLogic.getInstance().insFamilyMember(fmID, id, relation)) {
    	            				System.out.println("Guest " + fmfn + " "+ fmln + " assigned to room " +roomNum+ " till "+date2+ " in hotel " +hotelID);
    	            			}
    	    		}
    		else
    			showAlert("Incorrect details", "Enter a valid details");
    		
    		if(!familyMemberIDField3.getText().equals("") && !fNameFamilyField3.getText().equals("") &&
    	        	!lNameFamilyField3.getText().equals("") &&
    	        	!familyMemberMealCombo3.getSelectionModel().isEmpty() &&
    	        	!relationCombo3.getSelectionModel().isEmpty() &&
    	        	!phoneFamilyMember3.getText().equals("") && 
    	        	roomsCombo1.getValue()!=roomsCombo.getValue()) {
    	    			int fmID = Integer.valueOf(familyMemberIDField3.getText());
    	    			String id4 = String.valueOf(fmID);
    	    			String fmfn = fNameFamilyField3.getText().trim();
    	        		String fmln = lNameFamilyField3.getText().trim();
    	        		String fmPhone = phoneFamilyMember3.getText().trim();
    	        		String relation = relationCombo3.getValue();
    	        		String fmMeal = familyMemberMealCombo3.getValue();
    	        		String roomNum = roomsCombo.getValue().toString();
    	        		if(CheckInLogic.getInstance().insGuest(fmID, fmfn, fmln, fmPhone, fmMeal) &&
    	            			CheckInLogic.getInstance().insAssignedToRoom(roomNum, hotID, id4, d1, d2) && 
    	            			CheckInLogic.getInstance().insFamilyMember(fmID, id, relation)) {
    	            				System.out.println("Guest " + fmfn + " "+ fmln + " assigned to room " +roomNum+ " till "+date2+ " in hotel " +hotelID);
    	            			}
    	    		}
    		else
    			showAlert("Incorrect details", "Enter a valid details");
    		
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
