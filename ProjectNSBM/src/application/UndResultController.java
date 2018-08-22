package application;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Map.Entry;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import classes.Subject;
import classes.Table;
import classes.UndResult;
import classes.Undergraduate;
import javafx.scene.control.Label;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TitledPane;

public class UndResultController implements Initializable{

    @FXML
    private JFXComboBox<String> cmbYr1Sem1Sub1;

    @FXML
    private JFXTextField txtYr1Sem1Sub1;

    @FXML
    private JFXComboBox<String> cmbYr1Sem1Sub2;

    @FXML
    private JFXTextField txtYr1Sem1Sub2;

    @FXML
    private JFXComboBox<String> cmbYr1Sem1Sub3;

    @FXML
    private JFXTextField txtYr1Sem1Sub3;

    @FXML
    private Label fee1;

    @FXML
    private Label fee2;

    @FXML
    private Label fee3;

    @FXML
    private JFXCheckBox chkYr1Sem1Sub1;

    @FXML
    private JFXCheckBox chkYr1Sem1Sub2;

    @FXML
    private JFXCheckBox chkYr1Sem1Sub3;
    
    @FXML
    private JFXButton btnSave;

    private boolean inEditMode = false;
    private HashMap<String, Integer> subVals = new HashMap<String, Integer>();
    private Undergraduate undergraduate;
    private UndResult result1 =new UndResult();
    private UndResult result2 =new UndResult();
    private UndResult result3 =new UndResult();
    
    private int prev_sub_id1;
    private int prev_sub_id2;
    private int prev_sub_id3;
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	} 
	
	public void setSubNames()
    {
		int courseId = undergraduate.course_id;
		String sql = "SELECT sub_id FROM bachelorsub WHERE bachCourse_id = " + courseId;
		System.out.println(sql);
		ResultSet rs = Table.executeQuery(sql);
		String where;
    	ObservableList<String> snames = FXCollections.observableArrayList();		
    	List<Subject> l;
    	
		int subId = 0;
		try {
			while(rs.next())
			{
				subId = rs.getInt("sub_id");
				where = "id = " + subId;
				l = Table.query("subject", Subject.class, where);
				
				Subject cSub = l.get(0);
				snames.add(cSub.name);
				subVals.put(cSub.name, cSub.id);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(!inEditMode)
		{
			cmbYr1Sem1Sub1.setItems(snames);
			cmbYr1Sem1Sub2.setItems(snames);
			cmbYr1Sem1Sub3.setItems(snames);
		}
    }

	public void insert(ActionEvent event)
	{
		result1.und_id = undergraduate.id;
		result1.sub_id = subVals.get(cmbYr1Sem1Sub1.getValue());
		result1.payment = chkYr1Sem1Sub1.isSelected();
		result1.result = txtYr1Sem1Sub1.getText();
		
		result2.und_id = undergraduate.id;		
		result2.sub_id = subVals.get(cmbYr1Sem1Sub2.getValue());
		result2.payment = chkYr1Sem1Sub2.isSelected();
		result2.result = txtYr1Sem1Sub2.getText();
		
		result3.und_id = undergraduate.id;		
		result3.sub_id = subVals.get(cmbYr1Sem1Sub3.getValue());
		result3.payment = chkYr1Sem1Sub3.isSelected();
		result3.result = txtYr1Sem1Sub3.getText();
		
		if(!inEditMode)
		{
			result1.insertRecord();
			result2.insertRecord();
			result3.insertRecord();
		}
		else
		{
			result1.updateRecord(prev_sub_id1);
			result2.updateRecord(prev_sub_id2);
			result3.updateRecord(prev_sub_id3);
		}
	}
	
	public void edit()
	{
		List<UndResult> ur = Table.query("undresult", UndResult.class, "und_id = " + undergraduate.id);
		if(!ur.isEmpty())
		{
			inEditMode = true;
			
			prev_sub_id1 = ur.get(0).sub_id;
			cmbYr1Sem1Sub1.setValue(getName(prev_sub_id1, subVals));
			txtYr1Sem1Sub1.setText(ur.get(0).result);
//			fee1.setText(ur.get(0));
			if(ur.get(0).payment)
				chkYr1Sem1Sub1.setSelected(true);
			
			prev_sub_id2 = ur.get(1).sub_id;
			cmbYr1Sem1Sub2.setValue(getName(prev_sub_id2, subVals));
			txtYr1Sem1Sub2.setText(ur.get(1).result);
			if(ur.get(1).payment)
				chkYr1Sem1Sub2.setSelected(true);
			
			prev_sub_id3 = ur.get(2).sub_id;
			cmbYr1Sem1Sub3.setValue(getName(prev_sub_id3, subVals));
			txtYr1Sem1Sub3.setText(ur.get(2).result);
			if(ur.get(2).payment)
				chkYr1Sem1Sub3.setSelected(true);
		}
	}
	
	public Undergraduate getUndergraduate() {
		return undergraduate;
	}

	public void setUndergraduate(Undergraduate u) {
		this.undergraduate = u;
	}
	
	public String getName(int id, HashMap<String, Integer> hmap)
    {
    	String s = null;
    	for(Entry<String, Integer> entry : hmap.entrySet())
    	{
    		if(entry.getValue().equals(id))
    		{
    			s = entry.getKey();
    			System.out.println(entry.getKey());
    		}
    	}
    	return s;
    }
}