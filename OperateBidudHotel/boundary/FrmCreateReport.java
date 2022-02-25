package boundary;

import java.net.URL;
import java.util.ResourceBundle;
import control.ReportLogic;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class FrmCreateReport implements Initializable{

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	public void btnProduceStatusReport() {
//		ReportLogic.getInstance().compileStatusReport()
//		   .setVisible(true);
	}
	
	 @FXML
	    private Button btnBack;
	public void btnBack() {
		
		ReportLogic.getInstance().openScene("/boundary/MainMenu.fxml", btnBack);
	}
	
   
}
