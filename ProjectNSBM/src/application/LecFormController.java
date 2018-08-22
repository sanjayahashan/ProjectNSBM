package application;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;

import classes.Lecturer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ToggleGroup;

public class LecFormController implements Initializable{

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
	private Lecturer l = new Lecturer();

	public Lecturer getL() {
		return l;
	}

	public void setL(Lecturer l) {
		this.l = l;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	public void insert(ActionEvent event)
    {    	
    	if(inEditMode)
    		l.id = Integer.parseInt(txtID.getText());
    	l.fname = txtFname.getText();
    	l.mname = txtMname.getText();
    	l.lname = txtLname.getText();
    	l.gender = radioMale.isSelected() ? "M" : "F";
    	l.dob = java.sql.Date.valueOf(dateD.getValue());
    	l.home_no = txtHome_no.getText();
    	l.add_1 = txtAdd_1.getText();
    	l.add_2 = txtAdd_2.getText();
    	l.add_3 = txtAdd_3.getText();
    	l.t_no = txtT_no.getText();
    	l.email = txtEmail.getText();    	
    	if(!inEditMode)
    		l.insertRecord();
    	else
    		l.updateRecord();
    }

	public void edit()
	{
		inEditMode = true;
    	
    	txtID.setText(String.valueOf(l.id));
    	txtFname.setText(l.fname);
    	txtMname.setText(l.mname);
    	txtLname.setText(l.lname);
    	dateD.setValue(l.dob.toLocalDate());
    	
    	if(l.gender=="F")
    		radioFemale.setSelected(true);
    	else if(l.gender=="M")
    		radioMale.setSelected(true);
    	
    	txtHome_no.setText(l.home_no);
    	txtAdd_1.setText(l.add_1);
    	txtAdd_2.setText(l.add_2);
    	txtAdd_3.setText(l.add_3);
    	txtT_no.setText(l.t_no);
    	txtEmail.setText(l.email);
    	
    	System.out.println(l.gender);
	}
	
	public void delete(ActionEvent event)
	{
		l.deleteRecord();
	}
}
