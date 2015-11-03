package view;

import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GUI_try extends Application {
	protected static String userCommand;
	protected static String returnCommand;
	protected static double xOffset = 0;
	protected static double yOffset = 0;
	private static Logger logger = Logger.getLogger("MotionCatcher");
	public static Image listBkImage1;
	public static Image listBkImage2;
	public static Image listBkImage;

	public static void main(String args[]) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Slent Jarvis");
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));
		primaryStage.show();

		
		
		GridPane grid = new GridPane();
		grid.setHgap(5);
		grid.setVgap(20);
		grid.setPadding(new Insets(10, 5, 5, 25));

		//*set background
		Image background = new Image(getClass().getResourceAsStream("back4.png"));
		ImageView bk = new ImageView(background);
		Group backg = new Group();
		backg.getChildren().addAll(bk,grid);
		
		
		/*Image iconYui = new Image(getClass().getResourceAsStream("transparency.png"));
		grid.add(new ImageView(iconYui), 0, 0);*/

		// add text box to show message
		/*final TextArea showBox = new TextArea();
		showBox.setPrefSize(300, 100);
		/*Background back=new Background(new BackgroundImage(new Image(getClass().getResourceAsStream("qqq.png")), null , null, null, null));
		showBox.setBackground(back);*/
		//showBox.setStyle("-fx-background-color: rgba(0, 100, 100, 0.5); -fx-background-radius: 10;");
		/*grid.add(showBox, 0, 1);
		showBox.setEditable(false);*/
		Font font=Font.font("Stencil Std", FontWeight.BOLD, FontPosture.REGULAR, 22);
		
		final Label label = new Label("Welcome to SilentJarvis!");
	    label.setFont(font);
	    label.setTextFill(Color.web("#ffffff"));

	    final Label label3 = new Label("tasks are listed below");
	    label3.setFont(font);
	    label3.setTextFill(Color.web("#ffffff"));
	    
	    font=Font.font("Stencil Std", FontWeight.BOLD, FontPosture.REGULAR, 30);
	    
	    final Label label2 = new Label("Today's");
	    label2.setFont(font);
	    label2.setTextFill(Color.web("#ffffff"));
	    

	    //label2.setStyle("-fx-text-fill: goldenrod; -fx-font-style: italic; -fx-font-weight: bold; -fx-padding: 0 0 20 0;");

	    StackPane glass = new StackPane();
	    glass.setPrefSize(300, 120);
	    grid.setVgap(20);
	    glass.getChildren().add(label);
	    StackPane.setAlignment(label, Pos.TOP_CENTER);
	    glass.getChildren().add(label2);
	    StackPane.setAlignment(label2, Pos.CENTER);
	    glass.getChildren().add(label3);
	    StackPane.setAlignment(label3, Pos.BOTTOM_CENTER);
	    //glass.getChildren().remove(label);
	    grid.add(glass, 0, 1);
		
		ScrollPane eventPane = new ScrollPane();
		eventPane.setPrefSize(300, 420);
		eventPane.setOpacity(0.9);
		eventPane.setHbarPolicy(ScrollBarPolicy.NEVER);
		grid.add(eventPane, 0, 2);

		// add command box
		TextField userCommandBox = new TextField();
		grid.add(userCommandBox, 0, 3);
		userCommandBox.requestFocus();
	
		// set exit button
		/*Image imageExit = new Image(getClass().getResourceAsStream("pink.png"));
		ImageView exit = new ImageView(imageExit);
	    exit.setOnMouseClicked(new EventHandler<MouseEvent>(){
	    	@Override
	    	public void handle(MouseEvent event){
	    		Event.fireEvent(primaryStage, new WindowEvent(primaryStage, WindowEvent.WINDOW_CLOSE_REQUEST ));
	    	}
	    });
	    grid.add(exit, 0, 4);*/
		
		GridPane eventGrid = new GridPane();
		eventGrid.setHgap(3);
		eventGrid.setVgap(1);

		// Control the dragging of stage
		DragController.dragStage(grid, primaryStage);

		// set the scene
		Scene scene = new Scene(backg, 350, 660);
		primaryStage.setScene(scene);

		// initialize GUI and Logic

		
		/*Font font= new Font(32);
		showBox.setFont( font );
		showBox.appendText("hhhhh\n");*/
		
		// catch the motion of users
		userCommandBox.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode().equals(KeyCode.ENTER)) {
					GUI_try.userCommand = userCommandBox.getText();
					// link with logic
					System.out.print(GUI_try.userCommand);
					userCommandBox.clear();
					// link with logic
					if (!GUI_try.userCommand.equals("")) {

						logger.log(Level.INFO, "get the output");
						// Yui_GUI.returnCommand =
						// ToDoList.implement(Yui_GUI.userCommand);
						eventGrid.getChildren().clear();
						// GUILogic.showEvents(eventGrid,deadlineIcon,
						// eventIcon, floatingIcon);
						//showBox.appendText(Yui_GUI.returnCommand + "\n" + "\n");
						// listBk.setImage(listBkImage);
						logger.log(Level.INFO, "end of processing");
					}
				}

				if (event.getCode().equals(KeyCode.UP)) {
					eventPane.setVvalue(eventPane.getVvalue() - 0.3f);
				}
				if (event.getCode().equals(KeyCode.DOWN)) {
					eventPane.setVvalue(eventPane.getVvalue() + 0.3f);
				}
			}
		});
		primaryStage.show();
	}
}