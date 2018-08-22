package application;

import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;

import classes.BachelorCourse;
import classes.BachelorSub;
import classes.Faculty;
import classes.Subject;
import classes.Table;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class SubjectFormController implements Initializable {

    @FXML
    private JFXTextField txtId;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtCredits;

    @FXML
    private JFXTextField txtFee;
    
    @FXML
    private JFXTextField txtLecId;

    @FXML
    private JFXTimePicker timeStart;

    @FXML
    private JFXTimePicker timeEnd;
    
    @FXML
    private JFXComboBox<String> cmbCourse;
    
    private Subject sub = new Subject();
    private BachelorSub bsub = new BachelorSub();
    private boolean inEditMode = false;
    private HashMap<String, Integer> courseVals = new HashMap<String, Integer>();

	public Subject getSub() {
		return sub;
	}

	public void setSub(Subject sub) {
		this.sub = sub;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	}

	public void save(ActionEvent event)
	{			
		sub.name = txtName.getText();
		sub.credits = Integer.parseInt(txtCredits.getText());
		sub.fee = Integer.parseInt(txtFee.getText());
		sub.lec_id = Integer.parseInt(txtLecId.getText());
		sub.start_time = java.sql.Time.valueOf(timeStart.getValue());
		sub.end_time = java.sql.Time.valueOf(timeEnd.getValue());
		
		if(!inEditMode)
		{
			bsub.bachcourse_id = courseVals.get(cmbCourse.getValue());
    		bsub.sub_id = sub.insertRecord();

			//to build relationship with bachelorSub table
			bsub.insertRecord();
		}
    	else
    		sub.updateRecord();
	}
	
	public void edit()
	{
		System.out.println(sub.name);
		
		inEditMode = true;
		
		txtId.setText(String.valueOf(sub.id));
		txtName.setText(sub.name);
		txtCredits.setText(String.valueOf(sub.credits));
		txtFee.setText(String.valueOf(sub.fee));
		txtLecId.setText(String.valueOf(sub.lec_id));
		timeStart.setValue(sub.start_time.toLocalTime());
		timeEnd.setValue(sub.end_time.toLocalTime());
	}
	
	public void setCourseNames()
    {
    	ObservableList<String> fnames = FXCollections.observableArrayList();
    	List<BachelorCourse> l = Table.query("bachelorcourse", BachelorCourse.class, "");
    	for(BachelorCourse f : l)
    	{
    		fnames.add(f.name);
    		courseVals.put(f.name, f.id);
    	}
    	cmbCourse.setItems(fnames);
    }
}
