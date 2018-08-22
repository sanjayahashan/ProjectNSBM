package application;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import classes.Table;
import classes.Undergraduate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class UndTableController implements Initializable{

    @FXML
    private TableView<Undergraduate> tableSubject;

    @FXML
    private TableColumn<Undergraduate, Integer> colId;

    @FXML
    private TableColumn<Undergraduate, String> colFName;

    @FXML
    private TableColumn<Undergraduate, String> colLName;
    
    @FXML
    private TableColumn<Undergraduate, String> colGender;

    @FXML
    private TableColumn<Undergraduate, String> colCity;

    @FXML
    private TableColumn<Undergraduate, String> colTel;

    @FXML
    private TableColumn<Undergraduate, String> colEmail;

    @FXML
    private TableColumn<Undergraduate, Integer> colFaculty;

    @FXML
    private TableColumn<Undergraduate, Integer> colCourse;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		initCols();
	}

    public void initCols()
	{
		colId.setCellValueFactory(new PropertyValueFactory<Undergraduate, Integer>("id"));
		colFName.setCellValueFactory(new PropertyValueFactory<Undergraduate, String>("fname"));
		colLName.setCellValueFactory(new PropertyValueFactory<Undergraduate, String>("lname"));
		colGender.setCellValueFactory(new PropertyValueFactory<Undergraduate, String>("gender"));
		colCity.setCellValueFactory(new PropertyValueFactory<Undergraduate, String>("add_3"));
		colTel.setCellValueFactory(new PropertyValueFactory<Undergraduate, String>("t_no"));
		colEmail.setCellValueFactory(new PropertyValueFactory<Undergraduate, String>("email"));
		colFaculty.setCellValueFactory(new PropertyValueFactory<Undergraduate, Integer>("fac_id"));
		colCourse.setCellValueFactory(new PropertyValueFactory<Undergraduate, Integer>("course_id"));
		
		List<Undergraduate> subs = Table.query("undergraduate", Undergraduate.class, "");
		ObservableList<Undergraduate> oSubs = FXCollections.observableArrayList(subs);
		
		tableSubject.setItems(oSubs); 
	}
}
