package test;

import static org.junit.Assert.*;


import static org.hamcrest.Matchers.*;
import org.junit.Test;

import com.athaydes.automaton.FXApp;
import com.athaydes.automaton.FXer;

import javafx.scene.control.TextField;
import view.MainApp;

public class AppTest {

	@Test
	public void test() {
		FXApp.startApp( new MainApp() );
		FXApp.initializeIfStageExists();
		if (FXApp.isInitialized()) {
		  FXer fxer = FXer.getUserWith( FXApp.getScene().getRoot() );
		  fxer.clickOn(TextField.class).enterText("add meeting with boss").waitForFxEvents();
		  assertEquals(1 + 1, 2);

		} else {
		  throw new RuntimeException( "Could not find a JavaFX Stage" );
		}
	}

}
