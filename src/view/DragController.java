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
	        	   GUIMain.xOffset = event.getSceneX();
	        	   GUIMain.yOffset = event.getSceneY();
	           }
	       });

	       grid.setOnMouseDragged(new EventHandler<MouseEvent>() {
	           @Override
	           public void handle(MouseEvent event) {
	               primaryStage.setX(event.getScreenX() - GUIMain.xOffset);
	               primaryStage.setY(event.getScreenY() - GUIMain.yOffset);
	           }
	       });
	}
}