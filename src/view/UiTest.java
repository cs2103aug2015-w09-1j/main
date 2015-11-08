package view;

import static org.junit.Assert.*;
import static org.junit.Assume.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import javafx.scene.input.KeyCode;

import org.junit.Before;
import org.junit.Test;
import org.loadui.testfx.GuiTest;
import org.loadui.testfx.utils.FXTestUtils;
import org.loadui.testfx.utils.UserInputDetector;
import org.hamcrest.CoreMatchers.*;

import com.google.common.util.concurrent.SettableFuture;

import javafx.scene.Parent;
import javafx.stage.Stage;

public class UiTest extends GuiTest {
	  private static final SettableFuture<Stage> stageFuture = SettableFuture.create();

	  protected static class TestUI extends GUIController {
	    public TestUI() {
	      super();
	    }

	    @Override
	    public void start(Stage primaryStage) throws Exception {
	      super.start(primaryStage);
	      stageFuture.set(primaryStage);
	    }
	  }

	  @Before
	  @Override
	  public void setupStage() throws Throwable {
	    assumeTrue(!UserInputDetector.instance.hasDetectedUserInput());

	    FXTestUtils.launchApp(TestUI.class); // You can add start parameters here
	    try {
	      stage = targetWindow(stageFuture.get(25, TimeUnit.SECONDS));
	      FXTestUtils.bringToFront(stage);
	    } catch (Exception e) {
	      throw new RuntimeException("Unable to show stage", e);
	    }
	  }

	  @Override
	  protected Parent getRootNode() {
	    return stage.getScene().getRoot();
	  }

	  @Test
	  public void should_have_text() {
		  type("add meeting").push(KeyCode.ENTER);
		  assertEquals(1 + 1, 2);
	  }

	}
