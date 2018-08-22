package application;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.List;

import com.jfoenix.controls.*;

import classes.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LoginController {
	@FXML
    private JFXButton btnLogin;

    @FXML
    private JFXTextField txtUsername;

    @FXML
    private JFXPasswordField txtPassword;
    
    public void login(ActionEvent event)
    {
    	String un = txtUsername.getText();
    	String pw = txtPassword.getText();
    	List<User> l = User.verifyUser(un, pw);
    	if (l.isEmpty())
    		System.out.println("Invalid username or password");
    	else
    	{
    		System.out.println("Login Successful");
        	try {
        		((Node)event.getSource()).getScene().getWindow().hide();
        		Stage primaryStage = new Stage();
            	FXMLLoader loader = new FXMLLoader();
            	loader.setLocation(getClass().getResource("/application/Main.fxml"));
    			Pane root = loader.load();
    			MainController mainController = (MainController)loader.getController();
    			Scene scene = new Scene(root);
    			scene.getStylesheets().add(getClass().getResource("dark_theme.css").toExternalForm());
    			primaryStage.setScene(scene);
    			primaryStage.setResizable(false);
    			primaryStage.setTitle("NSBM Student Management System");
    			primaryStage.show();
        	} catch (IOException e) {
        		// TODO Auto-generated catch block
        		e.printStackTrace();
        	}
    	}
    	
    	
    }
}

