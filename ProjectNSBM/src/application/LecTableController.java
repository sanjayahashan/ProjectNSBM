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

public class LecTableController implements Initializable{

    @FXML
    private TableView<Lecturer> tableSubject;

    @FXML
    private TableColumn<Lecturer, Integer> colId;

    @FXML
    private TableColumn<Lecturer, String> colFName;

    @FXML
    private TableColumn<Lecturer, String> colLName;
    
    @FXML
    private TableColumn<Lecturer, String> colGender;

    @FXML
    private TableColumn<Lecturer, String> colCity;

    @FXML
    private TableColumn<Lecturer, String> colTel;

    @FXML
    private TableColumn<Lecturer, String> colEmail;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		initCols();
	}

    public void initCols()
	{
		colId.setCellValueFactory(new PropertyValueFactory<Lecturer, Integer>("id"));
		colFName.setCellValueFactory(new PropertyValueFactory<Lecturer, String>("fname"));
		colLName.setCellValueFactory(new PropertyValueFactory<Lecturer, String>("lname"));
		colGender.setCellValueFactory(new PropertyValueFactory<Lecturer, String>("gender"));
		colCity.setCellValueFactory(new PropertyValueFactory<Lecturer, String>("add_3"));
		colTel.setCellValueFactory(new PropertyValueFactory<Lecturer, String>("t_no"));
		colEmail.setCellValueFactory(new PropertyValueFactory<Lecturer, String>("email"));
		
		List<Lecturer> subs = Table.query("lecturer", Lecturer.class, "");
		ObservableList<Lecturer> oSubs = FXCollections.observableArrayList(subs);
		
		tableSubject.setItems(oSubs); 
	}
}
