package application;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;

import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class Functions {
	public static void showDialogBox(String msg, StackPane pane, AnchorPane ancPane)
	{
		BoxBlur blur = new BoxBlur(3,3,3);
		
		JFXDialogLayout dialogLayout = new JFXDialogLayout();
		JFXButton button = new JFXButton("OK");
		
		JFXDialog dialog = new JFXDialog(pane, dialogLayout, JFXDialog.DialogTransition.TOP);
		button.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent mouseEvent) ->{
			dialog.close();

			ancPane.setEffect(null);
		});
		
		dialogLayout.setBody(new Text(msg));
		dialogLayout.setActions(button);
		dialog.show();
		ancPane.setEffect(blur);
	}
}
