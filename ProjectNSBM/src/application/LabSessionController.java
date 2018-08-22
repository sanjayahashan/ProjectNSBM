package application;

import java.beans.Statement;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import classes.LabSession;
import classes.LabSession;
import classes.Table;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LabSessionController implements Initializable{

    @FXML
    private TableView<LabSession> tableLabSession;

    @FXML
    private TableColumn<LabSession, Integer> colInsId;

    @FXML
    private TableColumn<LabSession, Integer> colSubId;

    @FXML
    private TableColumn<LabSession, Integer> colLabId;

    @FXML
    private TableColumn<LabSession, java.sql.Time> colStartTime;

    @FXML
    private TableColumn<LabSession, java.sql.Time> colEndTime;

    @FXML
    private MenuItem contDel;

    @FXML
    private MenuItem contEdit;

    @FXML
    private JFXButton btnNew;

    private LabSession labS = new LabSession();
    
	public LabSession getLabS() {
		return labS;
	}

	public void setLabS(LabSession labS) {
		this.labS = labS;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		initCols();
		
	}

	public void initCols()
	{
		colInsId.setCellValueFactory(new PropertyValueFactory<LabSession, Integer>("ins_id"));
		colSubId.setCellValueFactory(new PropertyValueFactory<LabSession, Integer>("sub_id"));
		colLabId.setCellValueFactory(new PropertyValueFactory<LabSession, Integer>("lab_id"));
		colStartTime.setCellValueFactory(new PropertyValueFactory<LabSession, java.sql.Time>("start_time"));
		colEndTime.setCellValueFactory(new PropertyValueFactory<LabSession, java.sql.Time>("end_time"));
		
		List<LabSession> labS = Table.query("labsession", LabSession.class, "");
		ObservableList<LabSession> oLabS = FXCollections.observableArrayList(labS);
		
		System.out.println(oLabS.size());
		tableLabSession.setItems(oLabS);
	}
	
	public void delete(ActionEvent event)
	{
		labS = tableLabSession.getSelectionModel().getSelectedItem();		
		String sql = "DELETE FROM labsession WHERE ins_id = " + labS.ins_id + " AND sub_id = " + labS.lab_id + 
				" AND lab_id = " + labS.lab_id;
		Connection con = LabSession.getCon();
		java.sql.Statement st;
		try {
			st = con.createStatement();

			st.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void insert(ActionEvent event)
	{
    	try
    	{
    		Stage primaryStage = new Stage();
        	FXMLLoader loader = new FXMLLoader();
			Pane root = loader.load(getClass().getResource("/application/LabSessionForm.fxml").openStream());
//			LabSessionFormController subf = (LabSessionFormController)loader.getController();
			Scene scene = new Scene(root);
			primaryStage.setResizable(false);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Add Lab Session");
			primaryStage.show();
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    	initCols();
	}
	
	public void edit(ActionEvent event)
	{
		labS = tableLabSession.getSelectionModel().getSelectedItem();
		try
    	{
    		Stage primaryStage = new Stage();
        	FXMLLoader loader = new FXMLLoader();
			Pane root = loader.load(getClass().getResource("/application/LabSessionForm.fxml").openStream());
			LabSessionFormController labf = (LabSessionFormController)loader.getController();
			labf.setLabS(labS);
			labf.edit();
			Scene scene = new Scene(root);
			primaryStage.setResizable(false);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Edit Lab Session");
			primaryStage.show();
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
	}
}
