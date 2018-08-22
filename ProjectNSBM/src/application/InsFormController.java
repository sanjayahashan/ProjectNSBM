package application;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;

import classes.Instructer;
import classes.Lecturer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ToggleGroup;

public class InsFormController implements Initializable{

    @FXML
    private JFXTextField txtID;

    @FXML
    private JFXTextField txtFname;

    @FXML
    private JFXTextField txtMname;

    @FXML
    private JFXTextField txtLname;

    @FXML
    private JFXTextField txtHome_no;

    @FXML
    private JFXTextField txtAdd_1;

    @FXML
    private JFXTextField txtAdd_2;

    @FXML
    private JFXTextField txtAdd_3;

    @FXML
    private JFXTextField txtT_no;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXRadioButton radioMale;

    @FXML
    private ToggleGroup gender;

    @FXML
    private JFXRadioButton radioFemale;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXDatePicker dateD;
    
    private boolean inEditMode = false;
	private Instructer i = new Instructer();
    
    @FXML
    void delete(ActionEvent event) {
    	i.deleteRecord();
    }

    @FXML
    void insert(ActionEvent event) {
    	if(inEditMode)
    		i.id = Integer.parseInt(txtID.getText());
    	i.fname = txtFname.getText();
    	i.mname = txtMname.getText();
    	i.lname = txtLname.getText();
    	i.gender = radioMale.isSelected() ? "M" : "F";
    	i.dob = java.sql.Date.valueOf(dateD.getValue());
    	i.home_no = txtHome_no.getText();
    	i.add_1 = txtAdd_1.getText();
    	i.add_2 = txtAdd_2.getText();
    	i.add_3 = txtAdd_3.getText();
    	i.t_no = txtT_no.getText();
    	i.email = txtEmail.getText();    	
    	if(!inEditMode)
    		i.insertRecord();
    	else
    		i.updateRecord();
    }
    
    public void edit()
	{
		inEditMode = true;
    	
    	txtID.setText(String.valueOf(i.id));
    	txtFname.setText(i.fname);
    	txtMname.setText(i.mname);
    	txtLname.setText(i.lname);
    	dateD.setValue(i.dob.toLocalDate());
    	
    	if(i.gender=="M")
    		radioMale.setSelected(true);
    	else if(i.gender=="F")
    		radioFemale.setSelected(true);
    	
    	txtHome_no.setText(i.home_no);
    	txtAdd_1.setText(i.add_1);
    	txtAdd_2.setText(i.add_2);
    	txtAdd_3.setText(i.add_3);
    	txtT_no.setText(i.t_no);
    	txtEmail.setText(i.email);
	}

	public Instructer getI() {
		return i;
	}

	public void setI(Instructer i) {
		this.i = i;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
