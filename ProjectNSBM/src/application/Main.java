package application;
	
import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import classes.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/Login.fxml"));
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
		    primaryStage.setResizable(false);
			primaryStage.setTitle("Log In");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
//		
//		User user = new User();
//		user.username = "Hello";
//		user.password = "world";
//		
////		LinkedHashMap<String, Object> hmap = user.properties();
////		System.out.println(hmap.values().toString());
////		String csv = String.join("','", hmap.keySet());
////		System.out.println(csv);
////		String s = user.createInsertStatement();
////		System.out.println(s);
////		
////		PreparedStatement st = user.createInsertPreparedStatement();
////		try {
////			st.executeUpdate();
////		} catch (SQLException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
//		
//		BachelorCourse bc = new BachelorCourse();
//		bc.name = "Bachelor of bla bla";
//		bc.credits = 20;
//		bc.fac_id = 1;
////		
////		bc.insertRecord();
//		
////		MasterCourse mc = new MasterCourse();
////		mc.name = "Master of Science";
////		mc.credits = 21;
////		mc.fac_id = 1;
////		mc.insertRecord();
//		
////		String s = mc.create;
//		
//		List<BachelorCourse> l = BachelorCourse.query("bachelorcourse", BachelorCourse.class, "");
//		System.out.println(l.get(1).name);
//		
//		List<Undergraduate> l1 = Undergraduate.query("undergraduate", Undergraduate.class, "");
//		System.out.println(l1.get(0).add_1);
//		
//		Postgraduate u = new Postgraduate();
//		u.fname = "Hello";
//		u.lname = "world";
//		u.dob = new Date("2001-01-01");
//		u.add_1 = "hhh";
//		u.add_2 = "jhdfjg";
//		u.add_3 = "hghd";
//		u.t_no = 6546464;
//		u.email = "hjfgghhguj";
//		u.type = "GJHGK";
//		u.institute = "dfgdjk";
//		u.grad_year = 2014;
//		u.r_year = 2015;
//		u.r_semester = 1;
//		u.fac_id = 2;
//		u.course_id = 2;
//		u.insertRecord();
//		
//		List<Undergraduate> unds = Table.query("undergraduate", Undergraduate.class, "id = 1");
////		Undergraduate und = unds.get(0);
//		
////		und.fname = "Maithreepala";
//		
////		PreparedStatement ps= Table.createPreparedStatement(und, "undergraduate", "id");
////		try {
//////			ps.executeUpdate();
////		} catch (SQLException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
////		und.updateRecord();
//	}
//		
//		UndResult ur = new UndResult();
//		ur.und_id =2;
//		ur.sub_id = 1;
//		ur.result = "A";
//		ur.payment = true;
//		
//		ur.insertRecord();
		
	}
}
