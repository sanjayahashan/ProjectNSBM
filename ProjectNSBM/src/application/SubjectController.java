package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import classes.Instructer;
import classes.Subject;
import classes.Table;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SubjectController implements Initializable{

    @FXML
    private TableView<Subject> tableSubject;

    @FXML
    private TableColumn<Subject, Integer> colId;

    @FXML
    private TableColumn<Subject, String> colName;

    @FXML
    private TableColumn<Subject, Integer> colCredits;

    @FXML
    private TableColumn<Subject, Integer> colFee;

    @FXML
    private TableColumn<Subject, Integer> colLecId;

    @FXML
    private TableColumn<Subject, java.sql.Time> colStartTime;

    @FXML
    private TableColumn<Subject, java.sql.Time> colEndTime;
    
    @FXML
    private JFXButton btnNew;

    @FXML
    private JFXButton btnRefresh;
    
    private Subject sub = new Subject();
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		initCols();
	}

	public void initCols()
	{
		colId.setCellValueFactory(new PropertyValueFactory<Subject, Integer>("id"));
		colName.setCellValueFactory(new PropertyValueFactory<Subject, String>("name"));
		colCredits.setCellValueFactory(new PropertyValueFactory<Subject, Integer>("credits"));
		colFee.setCellValueFactory(new PropertyValueFactory<Subject, Integer>("fee"));
		colLecId.setCellValueFactory(new PropertyValueFactory<Subject, Integer>("lec_id"));
		colStartTime.setCellValueFactory(new PropertyValueFactory<Subject, java.sql.Time>("start_time"));
		colEndTime.setCellValueFactory(new PropertyValueFactory<Subject, java.sql.Time>("end_time"));
		
		List<Subject> subs = Table.query("subject", Subject.class, "");
		ObservableList<Subject> oSubs = FXCollections.observableArrayList(subs);
		
		tableSubject.setItems(oSubs); 
	}
	
	public void delete(ActionEvent event)
	{
		sub = tableSubject.getSelectionModel().getSelectedItem();		
		sub.deleteRecord();
		initCols();
	}
	
	public void insert(ActionEvent event)
	{
    	try
    	{
    		Stage primaryStage = new Stage();
        	FXMLLoader loader = new FXMLLoader();
			Pane root = loader.load(getClass().getResource("/application/SubjectForm.fxml").openStream());
			SubjectFormController subf = (SubjectFormController)loader.getController();
			subf.setCourseNames();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Add Subject");
			primaryStage.show();
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    	initCols();
	}
	
	public void edit(ActionEvent event)
	{
		sub = tableSubject.getSelectionModel().getSelectedItem();
		try
    	{
    		Stage primaryStage = new Stage();
        	FXMLLoader loader = new FXMLLoader();
			Pane root = loader.load(getClass().getResource("/application/SubjectForm.fxml").openStream());
			SubjectFormController subf = (SubjectFormController)loader.getController();
			subf.setSub(sub);
			subf.edit();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Subject Details");
			primaryStage.show();
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
		initCols();
	}
}
