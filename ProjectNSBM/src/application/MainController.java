package application;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;

import classes.Instructer;
import classes.Lecturer;
import classes.Postgraduate;
import classes.Table;
import classes.Undergraduate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainController implements Initializable{

	@FXML
    private JFXButton btnUndergrad;

    @FXML
    private JFXButton btnPostGrad;

    @FXML
    private JFXButton btnLecturer;

    @FXML
    private JFXButton btnInstructor;

    @FXML
    private JFXButton btnSubject;

    @FXML
    private JFXButton btnLab;

    @FXML
    private JFXButton btnHome;

    @FXML
    private AnchorPane PaneSubject;

    @FXML
    private AnchorPane PaneLab;

    @FXML
    private AnchorPane paneHome;

    @FXML
    private AnchorPane paneUnderGrad;

    @FXML
    private JFXTextField undID;

    @FXML
    private JFXButton btnUndEdit;

    @FXML
    private JFXButton btnUndNew;

    @FXML
    private AnchorPane PanePostGrad;

    @FXML
    private JFXTextField postID;

    @FXML
    private JFXButton btnPostEdit;

    @FXML
    private JFXButton btnPostNew;

    @FXML
    private AnchorPane PaneInstructor;

    @FXML
    private JFXTextField insID;

    @FXML
    private JFXButton btnInsEdit;

    @FXML
    private JFXButton btnInsNew;

    @FXML
    private AnchorPane PaneLecturer;

    @FXML
    private JFXTextField lecID;

    @FXML
    private JFXButton btnInsEdit1;

    @FXML
    private JFXButton btnLecNew;
    
    @FXML
    private StackPane rootPane;

    @FXML
    private AnchorPane rootAnchorPane;
    
    @FXML
    private void handleButtonAction(ActionEvent event)
    {
    	if(event.getSource() == btnUndergrad)
    	{
    		paneUnderGrad.toFront();
    	}
    	else if(event.getSource() == btnPostGrad)
    	{
    		PanePostGrad.toFront();
    	}
    	else if(event.getSource() == btnLecturer)
    	{
    		PaneLecturer.toFront();
    	}
    	else if(event.getSource() == btnInstructor)
    	{
    		PaneInstructor.toFront();
    	}
    	else if(event.getSource() == btnSubject)
    	{
    		PaneSubject.toFront();
    	}
    	else if(event.getSource() == btnLab)
    	{
    		PaneLab.toFront();
    	}
    	else if(event.getSource() == btnHome)
    	{
    		paneHome.toFront();
    	}
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
    
    public void editPost(ActionEvent event)
    {
    	String id = postID.getText();
    	String where = "id = " + id;
    	List<Postgraduate> ps = Table.query("postgraduate", Postgraduate.class, where);
    	
//    	System.out.println(u.dob);
    	
    	if(!ps.isEmpty())
    	{
    		Postgraduate p = ps.get(0);
	    	try
	    	{
	    		Stage primaryStage = new Stage();
	        	FXMLLoader loader = new FXMLLoader();
				Pane root = loader.load(getClass().getResource("/application/PostForm.fxml").openStream());
				PostFormController postf = (PostFormController)loader.getController();
				postf.setP(p);
				postf.edit();
				Scene scene = new Scene(root);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			    primaryStage.setResizable(false);
				primaryStage.setScene(scene);
				primaryStage.setTitle("Postgraduate Details");
				primaryStage.show();
	    	} catch (IOException e) {
	    		// TODO Auto-generated catch block
	    		e.printStackTrace();
	    	}
    	}
    	else
    	{
    		Functions.showDialogBox("No record found. Please check your ID", rootPane, rootAnchorPane);
    	}
    }

	public void newUnd(ActionEvent event)
	{
		try
    	{
    		Stage primaryStage = new Stage();
        	FXMLLoader loader = new FXMLLoader();
			Pane root = loader.load(getClass().getResource("/application/UndForm.fxml").openStream());
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		    primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Add Undergraduate");
			primaryStage.show();
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
	}
	
	public void newPost(ActionEvent event)
	{
		try
    	{
    		Stage primaryStage = new Stage();
        	FXMLLoader loader = new FXMLLoader();
			Pane root = loader.load(getClass().getResource("/application/PostForm.fxml").openStream());
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		    primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Add Postgraduate");
			primaryStage.show();
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
	}
	
	public void editUnd(ActionEvent event)
    {
    	String id = undID.getText();
    	String where = "id = " + id;
    	List<Undergraduate> us = Table.query("undergraduate", Undergraduate.class, where);
    	
//    	System.out.println(u.dob);
    	
    	if(!us.isEmpty())
    	{
    		Undergraduate u = us.get(0);
	    	try
	    	{
	    		Stage primaryStage = new Stage();
	        	FXMLLoader loader = new FXMLLoader();
				Pane root = loader.load(getClass().getResource("/application/UndForm.fxml").openStream());
				UndFormController undf = (UndFormController)loader.getController();
				undf.setU(u);
				undf.edit();
				Scene scene = new Scene(root);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			    primaryStage.setResizable(false);
				primaryStage.setScene(scene);
				primaryStage.setTitle("Undergraduate Details");
				primaryStage.show();
	    	} catch (IOException e) {
	    		// TODO Auto-generated catch block
	    		e.printStackTrace();
	    	}
    	}
    	else
    	{
    		Functions.showDialogBox("No record found. Please check your ID", rootPane, rootAnchorPane);
    	}
    }
	
	public void newLec(ActionEvent event)
	{
		try
    	{
    		Stage primaryStage = new Stage();
        	FXMLLoader loader = new FXMLLoader();
			Pane root = loader.load(getClass().getResource("/application/LecForm.fxml").openStream());
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		    primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Add New Lecturer");
			primaryStage.show();
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
	}
	
	public void editLec(ActionEvent event)
    {
    	String id = lecID.getText();
    	String where = "id = " + id;
    	List<Lecturer> us = Table.query("lecturer", Lecturer.class, where);
    
    	if(!us.isEmpty())
    	{
    		Lecturer l = us.get(0);
	    	try
	    	{
	    		Stage primaryStage = new Stage();
	        	FXMLLoader loader = new FXMLLoader();
				Pane root = loader.load(getClass().getResource("/application/LecForm.fxml").openStream());
				LecFormController lecf = (LecFormController)loader.getController();
				lecf.setL(l);;
				lecf.edit();
				Scene scene = new Scene(root);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			    primaryStage.setResizable(false);
				primaryStage.setScene(scene);
				primaryStage.setTitle("Lecturer Details");
				primaryStage.show();
	    	} catch (IOException e) {
	    		// TODO Auto-generated catch block
	    		e.printStackTrace();
	    	}
    	}
    	else
    	{
    		Functions.showDialogBox("No record found. Please check your ID", rootPane, rootAnchorPane);
    	}
    }
	
	public void newIns(ActionEvent event)
	{
		try
    	{
    		Stage primaryStage = new Stage();
        	FXMLLoader loader = new FXMLLoader();
			Pane root = loader.load(getClass().getResource("/application/InsForm.fxml").openStream());
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		    primaryStage.setResizable(false);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Add Instructor");
			primaryStage.show();
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
	}
	
	public void editIns(ActionEvent event)
    {
    	String id = insID.getText();
    	String where = "id = " + id;
    	List<Instructer> us = Table.query("instructer", Instructer.class, where);
    	
    	if(!us.isEmpty())
    	{
    		Instructer l = us.get(0);
	    	try
	    	{
	    		Stage primaryStage = new Stage();
	        	FXMLLoader loader = new FXMLLoader();
				Pane root = loader.load(getClass().getResource("/application/InsForm.fxml").openStream());
				InsFormController insf = (InsFormController)loader.getController();
				insf.setI(l);
				insf.edit();
				Scene scene = new Scene(root);
				scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			    primaryStage.setResizable(false);
				primaryStage.setScene(scene);
				primaryStage.setTitle("Instructor Details");
				primaryStage.show();
	    	} catch (IOException e) {
	    		// TODO Auto-generated catch block
	    		e.printStackTrace();
	    	}
    	}
    	else
    		Functions.showDialogBox("No record found. Please check your ID", rootPane, rootAnchorPane);
    }
}
