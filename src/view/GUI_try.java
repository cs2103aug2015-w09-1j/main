package view;

import javafx.application.Application;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GUI_try extends Application {
	protected static String command;
	protected static String returnCommand;
	protected static double xOffset = 0;
	protected static double yOffset = 0;
	protected Image backgroundImage;
	protected Image iconImage;
	protected String title="Slent Jarvis";
	GridPane grid;
	StackPane SystemMessageBlock;
	ScrollPane TaskDisplayBlock;
	TextField userCommandBlock;
	
	public static void main(String args[]) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		loadImage();
		
		initialStage(primaryStage);
		
		primaryStage.show();
		
		buildView(primaryStage);
	}
	
	private void loadImage() {
		backgroundImage=new Image(getClass().getResourceAsStream("back3.png"));
		iconImage=new Image(getClass().getResourceAsStream("icon.png"));
	}
	
	private void initialStage(Stage primaryStage) {
		primaryStage.setTitle(title);
		primaryStage.initStyle(StageStyle.UNDECORATED);
		primaryStage.getIcons().add(iconImage);
	}
	
	private void buildView(Stage primaryStage) {
		grid = new GridPane();
		grid.setHgap(5);
		grid.setVgap(20);
		grid.setPadding(new Insets(10, 5, 5, 25));

		ImageView backgroung = new ImageView(backgroundImage);
		Group back = new Group();
		back.getChildren().addAll(backgroung,grid);
		
		SystemMessageBlock= new StackPane();
		SystemMessageBlock=buildSysMsgBlk(SystemMessageBlock);
	    grid.add(SystemMessageBlock, 0, 1);
		
		TaskDisplayBlock = new ScrollPane();
		TaskDisplayBlock=buildTskDisBlk(TaskDisplayBlock);
		grid.add(TaskDisplayBlock, 0, 2);

		userCommandBlock = new TextField();
		userCommandBlock.requestFocus();
		grid.add(userCommandBlock, 0, 3);

		DragController.dragStage(grid, primaryStage);

		Scene scene = new Scene(back, 350, 660);
		primaryStage.setScene(scene);
	}

	private StackPane buildSysMsgBlk(StackPane SystemMessageBlock) {
		Font commonFont=Font.font("Stencil Std", FontWeight.BOLD, FontPosture.REGULAR, 22);
		Font highlightFont=Font.font("Stencil Std", FontWeight.BOLD, FontPosture.REGULAR, 30);
		Color commonColor=Color.web("#039ed3");
		
		final Label line_1 = new Label("Welcome to SilentJarvis!");
	    line_1.setFont(commonFont);
	    line_1.setTextFill(commonColor);
	    
	    final Label line_2 = new Label("Recent");
	    line_2.setFont(highlightFont);
	    line_2.setTextFill(commonColor);
	    
	    final Label line_3 = new Label("tasks are listed below");
	    line_3.setFont(commonFont);
	    line_3.setTextFill(commonColor);

	    SystemMessageBlock.setPrefSize(300, 120);
	    
	    SystemMessageBlock.getChildren().add(line_1);
	    StackPane.setAlignment(line_1, Pos.TOP_CENTER);
	    
	    SystemMessageBlock.getChildren().add(line_2);
	    StackPane.setAlignment(line_2, Pos.CENTER);
	    
	    SystemMessageBlock.getChildren().add(line_3);
	    StackPane.setAlignment(line_3, Pos.BOTTOM_CENTER);
	    
		return SystemMessageBlock;
	}

	private ScrollPane buildTskDisBlk(ScrollPane TaskDisplayBlock) {
		TaskDisplayBlock.setPrefSize(300, 420);
		TaskDisplayBlock.setOpacity(0.9);
		TaskDisplayBlock.setHbarPolicy(ScrollBarPolicy.NEVER);
		
		return TaskDisplayBlock;
	}
}