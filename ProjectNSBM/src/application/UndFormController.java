package application;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;

import classes.BachelorCourse;
import classes.Course;
import classes.Faculty;
import classes.Table;
import classes.Undergraduate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Callback;

public class UndFormController implements Initializable{

    @FXML
    private JFXTextField txtID;

    @FXML
    private JFXTextField txtFname;

    @FXML
    private JFXTextField txtMname;

    @FXML
    private JFXTextField txtLname;

    @FXML
    private JFXDatePicker dateD;

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
    private JFXTextField txtIsland_rank;

    @FXML
    private JFXTextField txtDistrict_rank;

    @FXML
    private JFXTextField txtSub1;

    @FXML
    private JFXTextField txtSub2;

    @FXML
    private JFXTextField txtSub3;

    @FXML
    private JFXTextField txtFac;

    @FXML
    private JFXTextField txtCourse;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnAss;

    @FXML
    private JFXButton btnRes;
    
    @FXML
    private JFXTextField txtRyear;

    @FXML
    private JFXTextField txtRsem;
    
    @FXML
    private StackPane rootPane;

    @FXML
    private AnchorPane rootAnchor;
    
//    @FXML
//    private DatePicker dateD;
    
    @FXML
    private JFXComboBox<String> cmbFac;

    @FXML
    private JFXComboBox<String> cmbCourse;
    
    @FXML
    private ComboBox<String> cmbKKK;
    

	private Undergraduate u = new Undergraduate();
	private boolean inEditMode = false;

	private HashMap<String, Integer> facVals = new HashMap<String, Integer>();
	private HashMap<String, Integer> courseVals = new HashMap<String, Integer>();
    
	 public Undergraduate getU() {
			return u;
		}

	public void setU(Undergraduate u) {
		this.u = u;
	}
	
    @Override
	public void initialize(URL location, ResourceBundle resources) {
    	setFacNames();
    	
    	txtRyear.setText(String.valueOf(Year.now().getValue()));
    	if(inEditMode==false) {
    	cmbFac.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
//    		fac_id = facVals.get(newValue);
    		
    		ObservableList<String> fnames = FXCollections.observableArrayList();
        	String sql = "fac_id = " + facVals.get(newValue);
        	List<BachelorCourse> l = Table.query("BachelorCourse", BachelorCourse.class, sql);
        	for(BachelorCourse f : l)
        	{
        		fnames.add(f.name);
        		courseVals.put(f.name, f.id);
        	}
        	cmbCourse.setItems(fnames);
    	});}
	}
    
    public void setFacNames()
    {
    	ObservableList<String> fnames = FXCollections.observableArrayList();
    	List<Faculty> l = Table.query("faculty", Faculty.class, "");
    	for(Faculty f : l)
    	{
    		fnames.add(f.name);
    		facVals.put(f.name, f.id);
    	}
    	cmbFac.setItems(fnames);
    }
    
    public void insert(ActionEvent event)
    {    	
    	if(inEditMode)
    		u.id = Integer.parseInt(txtID.getText());
    	u.fname = txtFname.getText();
    	u.mname = txtMname.getText();
    	u.lname = txtLname.getText();
    	u.gender = radioMale.isSelected() ? "M" : "F";
    	u.dob = java.sql.Date.valueOf(dateD.getValue());
    	u.home_no = txtHome_no.getText();
    	u.add_1 = txtAdd_1.getText();
    	u.add_2 = txtAdd_2.getText();
    	u.add_3 = txtAdd_3.getText();
    	u.t_no = txtT_no.getText();
    	u.email = txtEmail.getText();
    	u.island_rank = Integer.parseInt(txtIsland_rank.getText());
    	u.district_rank = Integer.parseInt(txtDistrict_rank.getText());
    	u.sub1 = txtSub1.getText();
    	u.sub2 = txtSub2.getText();
    	u.sub3 = txtSub3.getText();
    	u.r_year = Integer.parseInt(txtRyear.getText());
    	u.r_semester = Integer.parseInt(txtRsem.getText());
    	u.fac_id = facVals.get(cmbFac.getValue());
    	u.course_id = courseVals.get(cmbCourse.getValue());
    	
    	if(!inEditMode)
    		u.id = u.insertRecord();
    	
    	else
    		u.updateRecord();
    }
    
    public void edit()
    {
    	inEditMode = true;
    	
    	txtID.setText(String.valueOf(u.id));
    	txtFname.setText(u.fname);
    	txtMname.setText(u.mname);
    	txtLname.setText(u.lname);
    	dateD.setValue(u.dob.toLocalDate());
    	
    	if(u.gender=="M")
    		radioMale.setSelected(true);
    	else if(u.gender=="F")
    		radioFemale.setSelected(true);
    	
    	txtHome_no.setText(u.home_no);
    	txtAdd_1.setText(u.add_1);
    	txtAdd_2.setText(u.add_2);
    	txtAdd_3.setText(u.add_3);
    	txtRyear.setText(String.valueOf(u.r_year));
    	txtRsem.setText(String.valueOf(u.r_semester));
    	txtT_no.setText(u.t_no);
    	txtEmail.setText(u.email);
    	txtIsland_rank.setText(String.valueOf(u.island_rank));
    	txtDistrict_rank.setText(String.valueOf(u.district_rank));
    	txtSub1.setText(u.sub1);
    	txtSub2.setText(u.sub2);
    	txtSub3.setText(u.sub3);
    	cmbFac.setValue(getName(u.fac_id, facVals));
    	cmbCourse.setValue(getName(u.course_id, courseVals));
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
    
    public void deleteUnd(ActionEvent event)
    {
    	u.deleteRecord();
    }
    
    public void subDetails(ActionEvent event)
    {
    	if(u.id!=0)
    	{
	    	try
	    	{
	    		Stage primaryStage = new Stage();
	        	FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("/application/UndResult.fxml"));
				Pane root = loader.load();
				UndResultController undr = (UndResultController)loader.getController();
				undr.setUndergraduate(u);
				undr.setSubNames();
				undr.edit();
				Scene scene = new Scene(root);
				primaryStage.setResizable(false);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.setTitle("Subjects for Current Semester");
				primaryStage.show();
	    	} catch (IOException e) {
	    		// TODO Auto-generated catch block
	    		e.printStackTrace();
	    	}
    	}
    	else {
    		Functions.showDialogBox("Please Save Before proceeding", rootPane, rootAnchor);
    	}
    }
}
