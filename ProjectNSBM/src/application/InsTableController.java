package application;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import classes.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class InsTableController implements Initializable{

    @FXML
    private TableView<Instructer> tableSubject;

    @FXML
    private TableColumn<Instructer, Integer> colId;

    @FXML
    private TableColumn<Instructer, String> colFName;

    @FXML
    private TableColumn<Instructer, String> colLName;
    
    @FXML
    private TableColumn<Instructer, String> colGender;

    @FXML
    private TableColumn<Instructer, String> colCity;

    @FXML
    private TableColumn<Instructer, String> colTel;

    @FXML
    private TableColumn<Instructer, String> colEmail;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		initCols();
	}

    public void initCols()
	{
		colId.setCellValueFactory(new PropertyValueFactory<Instructer, Integer>("id"));
		colFName.setCellValueFactory(new PropertyValueFactory<Instructer, String>("fname"));
		colLName.setCellValueFactory(new PropertyValueFactory<Instructer, String>("lname"));
		colGender.setCellValueFactory(new PropertyValueFactory<Instructer, String>("gender"));
		colCity.setCellValueFactory(new PropertyValueFactory<Instructer, String>("add_3"));
		colTel.setCellValueFactory(new PropertyValueFactory<Instructer, String>("t_no"));
		colEmail.setCellValueFactory(new PropertyValueFactory<Instructer, String>("email"));
		
		List<Instructer> subs = Table.query("instructer", Instructer.class, "");
		ObservableList<Instructer> oSubs = FXCollections.observableArrayList(subs);
		
		tableSubject.setItems(oSubs); 
	}
}
