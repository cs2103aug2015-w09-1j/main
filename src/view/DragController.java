package view;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class DragController {
	public static void dragStage(GridPane grid, final Stage primaryStage){
		grid.setOnMousePressed(new EventHandler<MouseEvent>() {
	           @Override
	           public void handle(MouseEvent event) {
	        	   GUI_try.xOffset = event.getSceneX();
	        	   GUI_try.yOffset = event.getSceneY();
	           }
	       });

	       grid.setOnMouseDragged(new EventHandler<MouseEvent>() {
	           @Override
	           public void handle(MouseEvent event) {
	               primaryStage.setX(event.getScreenX() - GUI_try.xOffset);
	               primaryStage.setY(event.getScreenY() - GUI_try.yOffset);
	           }
	       });
	}
}