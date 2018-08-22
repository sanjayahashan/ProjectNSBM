package application;

import java.io.IOException;
import java.net.URL;
import java.time.Year;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Map.Entry;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;

import classes.Faculty;
import classes.MasterCourse;
import classes.Postgraduate;
import classes.Table;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class PostFormController implements Initializable{
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
    private JFXTextField txtInstitute;

    @FXML
    private JFXTextField txtType;

    @FXML
    private JFXTextField txtGradYear;

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
    private JFXDatePicker dateD;

    @FXML
    private JFXComboBox<String> cmbFac;

    @FXML
    private JFXComboBox<String> cmbCourse;
    
    @FXML
    private StackPane rootPane;

    @FXML
    private AnchorPane rootAnchor;
    

	private Postgraduate p = new Postgraduate();
    
    private boolean inEditMode = false;

	private HashMap<String, Integer> facVals = new HashMap<String, Integer>();
	private HashMap<String, Integer> courseVals = new HashMap<String, Integer>();
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
    	setFacNames();
    	
    	txtRyear.setText(String.valueOf(Year.now().getValue()));
    	if(inEditMode==false) {
    	cmbFac.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
//    		fac_id = facVals.get(newValue);
    		
    		ObservableList<String> fnames = FXCollections.observableArrayList();
        	String sql = "fac_id = " + facVals.get(newValue);
        	List<MasterCourse> l = Table.query("MasterCourse", MasterCourse.class, sql);
        	for(MasterCourse f : l)
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
    		p.id = Integer.parseInt(txtID.getText());
    	p.fname = txtFname.getText();
    	p.mname = txtMname.getText();
    	p.lname = txtLname.getText();
    	p.gender = radioMale.isSelected() ? "M" : "F";
    	p.dob = java.sql.Date.valueOf(dateD.getValue());
    	p.home_no = txtHome_no.getText();
    	p.add_1 = txtAdd_1.getText();
    	p.add_2 = txtAdd_2.getText();
    	p.add_3 = txtAdd_3.getText();
    	p.t_no = Integer.parseInt(txtT_no.getText());
    	p.email = txtEmail.getText();
    	p.institute = txtInstitute.getText();
    	p.type = txtType.getText();
    	p.grad_year = Integer.parseInt(txtGradYear.getText());
    	p.r_year = Integer.parseInt(txtRyear.getText());
    	p.r_semester = Integer.parseInt(txtRsem.getText());
    	p.fac_id = facVals.get(cmbFac.getValue());
    	p.course_id = courseVals.get(cmbCourse.getValue());
    	
    	if(!inEditMode)
    		p.id = p.insertRecord();
    	else
    		p.updateRecord();
    }
    
    public void edit()
    {
    	inEditMode = true;
    	
    	txtID.setText(String.valueOf(p.id));
    	txtFname.setText(p.fname);
    	txtMname.setText(p.mname);
    	txtLname.setText(p.lname);
    	dateD.setValue(p.dob.toLocalDate());
    	
    	if(p.gender=="M")
    		radioMale.setSelected(true);
    	else if(p.gender=="F")
    		radioFemale.setSelected(true);
    	
    	txtHome_no.setText(p.home_no);
    	txtAdd_1.setText(p.add_1);
    	txtAdd_2.setText(p.add_2);
    	txtAdd_3.setText(p.add_3);
    	txtRyear.setText(String.valueOf(p.r_year));
    	txtRsem.setText(String.valueOf(p.r_semester));
    	txtT_no.setText(String.valueOf(p.t_no));
    	txtEmail.setText(p.email);
    	txtInstitute.setText(p.institute);
    	txtType.setText(p.type);
    	txtGradYear.setText(String.valueOf(p.grad_year));
    	cmbFac.setValue(getName(p.fac_id, facVals));
    	cmbCourse.setValue(getName(p.course_id, courseVals));
    	
    	System.out.println(dateD.getValue());
    }
    
    public Postgraduate getP() {
		return p;
	}

	public void setP(Postgraduate p) {
		this.p = p;
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
    
    public void deletePost(ActionEvent event)
    {
    	p.id = Integer.parseInt(txtID.getText());
    	p.deleteRecord();
    }
    
    public void subDetails(ActionEvent event)
    {
    	if (p.id!=0) {
			try {
				Stage primaryStage = new Stage();
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(getClass().getResource("/application/PostResult.fxml"));
				Pane root = loader.load();
				PostResultController post = (PostResultController) loader.getController();
				post.setPostgraduate(p);
				post.setSubNames();
				post.edit();
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
