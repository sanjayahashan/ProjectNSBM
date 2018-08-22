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
import classes.PostResult;
import classes.Postgraduate;
import javafx.scene.control.Label;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TitledPane;

public class PostResultController implements Initializable{

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
    private Postgraduate postgraduate;
    private PostResult result1 =new PostResult();
    private PostResult result2 =new PostResult();
    private PostResult result3 =new PostResult();

	public Postgraduate getPostgraduate() {
		return postgraduate;
	}


	public void setPostgraduate(Postgraduate postgraduate) {
		this.postgraduate = postgraduate;
	}


	public PostResult getResult1() {
		return result1;
	}


	public void setResult1(PostResult result1) {
		this.result1 = result1;
	}


	public PostResult getResult2() {
		return result2;
	}


	public void setResult2(PostResult result2) {
		this.result2 = result2;
	}


	public PostResult getResult3() {
		return result3;
	}


	public void setResult3(PostResult result3) {
		this.result3 = result3;
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
//		this.setSubNames();
	} 
	
	
	public void setSubNames()
    {
		int courseId = postgraduate.course_id;
		String sql = "SELECT sub_id FROM mastersub WHERE masterCourse_id = " + courseId;
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
		
    	
//    	for(Subject f : l)
//    	{
//    		snames.add(f.name);
//    		subVals.put(f.name, f.id);
//    	}
    	cmbYr1Sem1Sub1.setItems(snames);
    	cmbYr1Sem1Sub2.setItems(snames);
    	cmbYr1Sem1Sub3.setItems(snames);
    }

	public void insert(ActionEvent event)
	{
		result1.post_id = postgraduate.id;
		result1.sub_id = subVals.get(cmbYr1Sem1Sub1.getValue());
		result1.payment = chkYr1Sem1Sub1.isSelected();
		result1.result = txtYr1Sem1Sub1.getText();
		
		result2.post_id = postgraduate.id;		
		result2.sub_id = subVals.get(cmbYr1Sem1Sub2.getValue());
		result2.payment = chkYr1Sem1Sub2.isSelected();
		result2.result = txtYr1Sem1Sub2.getText();
		
		result3.post_id = postgraduate.id;		
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
			System.out.println("Edit called");
			result1.updateRecord();
			result2.updateRecord();
			result3.updateRecord();
		}
	}
	
	public void edit()
	{
		List<PostResult> ur = Table.query("postresult", PostResult.class, "post_id = " + postgraduate.id);
		if(!ur.isEmpty())
		{
			inEditMode = true;
			cmbYr1Sem1Sub1.setValue(getName(ur.get(0).sub_id, subVals));
			txtYr1Sem1Sub1.setText(ur.get(0).result);
//			fee1.setText(ur.get(0));
			if(ur.get(0).payment)
				chkYr1Sem1Sub1.setSelected(true);
			
			cmbYr1Sem1Sub2.setValue(getName(ur.get(1).sub_id, subVals));
			txtYr1Sem1Sub2.setText(ur.get(1).result);
			if(ur.get(1).payment)
				chkYr1Sem1Sub2.setSelected(true);
			
			cmbYr1Sem1Sub3.setValue(getName(ur.get(2).sub_id, subVals));
			txtYr1Sem1Sub3.setText(ur.get(2).result);
			if(ur.get(2).payment)
				chkYr1Sem1Sub3.setSelected(true);
		}
	}
	
	public Postgraduate getUndergraduate() {
		return postgraduate;
	}

	public void setUndergraduate(Postgraduate u) {
		this.postgraduate = u;
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