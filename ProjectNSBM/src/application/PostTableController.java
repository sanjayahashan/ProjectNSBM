package application;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import classes.Table;
import classes.Postgraduate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class PostTableController implements Initializable{

    @FXML
    private TableView<Postgraduate> tableSubject;

    @FXML
    private TableColumn<Postgraduate, Integer> colId;

    @FXML
    private TableColumn<Postgraduate, String> colFName;

    @FXML
    private TableColumn<Postgraduate, String> colLName;
    
    @FXML
    private TableColumn<Postgraduate, String> colGender;

    @FXML
    private TableColumn<Postgraduate, String> colCity;

    @FXML
    private TableColumn<Postgraduate, String> colTel;

    @FXML
    private TableColumn<Postgraduate, String> colEmail;

    @FXML
    private TableColumn<Postgraduate, Integer> colFaculty;

    @FXML
    private TableColumn<Postgraduate, Integer> colCourse;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		initCols();
	}

    public void initCols()
	{
		colId.setCellValueFactory(new PropertyValueFactory<Postgraduate, Integer>("id"));
		colFName.setCellValueFactory(new PropertyValueFactory<Postgraduate, String>("fname"));
		colLName.setCellValueFactory(new PropertyValueFactory<Postgraduate, String>("lname"));
		colGender.setCellValueFactory(new PropertyValueFactory<Postgraduate, String>("gender"));
		colCity.setCellValueFactory(new PropertyValueFactory<Postgraduate, String>("add_3"));
		colTel.setCellValueFactory(new PropertyValueFactory<Postgraduate, String>("t_no"));
		colEmail.setCellValueFactory(new PropertyValueFactory<Postgraduate, String>("email"));
		colFaculty.setCellValueFactory(new PropertyValueFactory<Postgraduate, Integer>("fac_id"));
		colCourse.setCellValueFactory(new PropertyValueFactory<Postgraduate, Integer>("course_id"));
		
		List<Postgraduate> subs = Table.query("postgraduate", Postgraduate.class, "");
		ObservableList<Postgraduate> oSubs = FXCollections.observableArrayList(subs);
		
		tableSubject.setItems(oSubs); 
	}
}
