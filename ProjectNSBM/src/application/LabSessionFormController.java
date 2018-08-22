package application;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;

import classes.LabSession;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class LabSessionFormController implements Initializable{

    @FXML
    private JFXTextField txtInsId;

    @FXML
    private JFXTextField txtSubId;

    @FXML
    private JFXTextField txtLabId;

    @FXML
    private JFXTimePicker timeStart;

    @FXML
    private JFXTimePicker timeEnd;

    @FXML
    private JFXButton btnSave;
    
    private LabSession labS = new LabSession();
    private Boolean inEditMode = false;
    
    public int prev_ins_id;
	public int prev_sub_id;
	public int prev_lab_id;

	public LabSession getLabS() {
		return labS;
	}

	public void setLabS(LabSession labS) {
		this.labS = labS;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

	public void save(ActionEvent event)
	{
		labS.setIns_id(Integer.parseInt(txtInsId.getText()));
		labS.setSub_id(Integer.parseInt(txtSubId.getText()));
		labS.setLab_id(Integer.parseInt(txtLabId.getText()));
		labS.start_time = java.sql.Time.valueOf(timeStart.getValue());
		labS.end_time = java.sql.Time.valueOf(timeEnd.getValue());
		
		if(!inEditMode)
    		labS.insertRecord();
    	else
    		labS.updateRecord(prev_ins_id, prev_sub_id, prev_lab_id);
	}
	
	public void edit()
	{
//		System.out.println(labS.name);
		
		inEditMode = true;
		
		prev_ins_id = labS.ins_id;
		prev_sub_id = labS.sub_id;
		prev_lab_id = labS.lab_id;
		txtInsId.setText(String.valueOf(labS.ins_id));
		txtSubId.setText(String.valueOf(labS.sub_id));
		txtLabId.setText(String.valueOf(labS.lab_id));
		timeStart.setValue(labS.start_time.toLocalTime());
		timeEnd.setValue(labS.end_time.toLocalTime());
	}
}
