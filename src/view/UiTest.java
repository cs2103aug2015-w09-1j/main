package view;

import static org.junit.Assert.*;
import static org.junit.Assume.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.concurrent.TimeUnit;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;

import org.junit.Before;
import org.junit.Test;
import org.loadui.testfx.GuiTest;
import org.loadui.testfx.Assertions.*;
import org.loadui.testfx.utils.FXTestUtils;
import org.loadui.testfx.utils.UserInputDetector;
import org.hamcrest.CoreMatchers.*;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.testfx.api.*;
import org.testfx.api.FxAssert;
import org.testfx.matcher.base.NodeMatchers;

import com.google.common.util.concurrent.SettableFuture;

import controller.Controller;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.stage.Stage;
import util.Storage;

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
	      Platform.setImplicitExit(false);
	    }
	  }


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
	    ((TextField) find("#text-field")).clear();
	  }

	  @Override
	  protected Parent getRootNode() {
	    return stage.getScene().getRoot();
	  }
	  
	  
	  @Test
	  public void showAddTest() {
		  type("add meeting").push(KeyCode.ENTER);
		  assertEquals(((Label) find("#sig")).getText(), "New task added!");
		  assertThat(find("#text-field"), NodeMatchers.hasText(""));
	  }
	  
	  
	  @Test
	  public void showWelcomeTest() {
		  assertEquals(((Label) find("#msg")).getText(), "Welcome to SilentJarvis! Recent tasks are listed below");
		  assertEquals(((Label) find("#sig")).getText(), "");
	  }
	  @Test
	  public void showErrorTest() {
		  type("asdfasd").push(KeyCode.ENTER);
		  assertEquals(((Label) find("#msg")).getText(), "Error! Invalid or wrong format of command.");
		  assertEquals(((Label) find("#sig")).getText(), "");
	  }
	  
	  @Test
	  public void showTodayTest() {
		  type("show today").push(KeyCode.ENTER);
		  assertEquals(((Label) find("#msg")).getText(), "Today's tasks are listed below");
		  assertEquals(((Label) find("#sig")).getText(), "");
	  }
	  
	  @Test
	  public void showSetFileNameTest() {
		  type("set filename test").push(KeyCode.ENTER);
		  assertEquals(((Label) find("#msg")).getText(), "New filename: " + Storage.getInstance().getfileName());
		  assertEquals(((Label) find("#sig")).getText(), "Set successfully!");
	  }
	  
	  @Test
	  public void showSetPathTest() {
		  type("set path test").push(KeyCode.BACK_SLASH).push(KeyCode.ENTER);
		  assertEquals(((Label) find("#msg")).getText(), "New path: " + Storage.getInstance().getPath());
		  assertEquals(((Label) find("#sig")).getText(), "Set successfully!");
	  }
	  
	  @Test
	  public void showAllTest() {
		  type("display").push(KeyCode.ENTER);
		  assertEquals(((Label) find("#msg")).getText(), "All tasks are listed below");
		  assertEquals(((Label) find("#sig")).getText(), "");
	  }
	  @Test
	  public void showArchived() {
		  type("show archived").push(KeyCode.ENTER);
		  assertEquals(((Label) find("#msg")).getText(), "Archived tasks are listed below");
		  assertEquals(((Label) find("#sig")).getText(), "");
	  }
	  
	  @Test
	  public void showCompletedTest() {
		  type("show complete").push(KeyCode.ENTER);
		  assertEquals(((Label) find("#msg")).getText(), "Completed tasks are listed below");
		  assertEquals(((Label) find("#sig")).getText(), "");
	  }
	  
	  @Test
	  public void showSearchTest() {
		  type("search meet").push(KeyCode.ENTER);
		  assertEquals(((Label) find("#msg")).getText(), "Search results");
		  assertEquals(((Label) find("#sig")).getText(), "");
	  }
	  
	  @Test
	  public void showFloatingTest() {
		  type("show floating").push(KeyCode.ENTER);
		  assertEquals(((Label) find("#msg")).getText(), "Floating tasks are listed below");
		  assertEquals(((Label) find("#sig")).getText(), "");
	  }
	  
	  @Test
	  public void showByTest() {
		  type("show by tomorrow").push(KeyCode.ENTER);
		  assertEquals(((Label) find("#msg")).getText(), "Tasks before selected date are listed below");
		  assertEquals(((Label) find("#sig")).getText(), "");
	  }
	  
	  @Test
	  public void showOnTest() {
		  type("show on tomorrow").push(KeyCode.ENTER);
		  assertEquals(((Label) find("#msg")).getText(), "Tasks on selected date are listed below");
		  assertEquals(((Label) find("#sig")).getText(), "");
	  }
	  
	  @Test
	  public void showSaveTest() {
		  type("load").push(KeyCode.ENTER);
		  type("save").push(KeyCode.ENTER);
		  assertEquals(((Label) find("#sig")).getText(), "Saved to " + Storage.getInstance().getPath() + Storage.getInstance().getfileName());
	  }
	  
	  @Test
	  public void showDeleteTest() {
		  type("delete 1").push(KeyCode.ENTER);
		  assertEquals(((Label) find("#sig")).getText(), "Task Deleted!");
	  }
	  
	  @Test
	  public void showUpdateTest() {
		  type("edit 1 taskName test").push(KeyCode.ENTER);
		  assertEquals(((Label) find("#sig")).getText(), "Task Edited!");
	  }
	  
	  @Test
	  public void showUndoTest() {
		  type("undo").push(KeyCode.ENTER);
		  assertEquals(((Label) find("#sig")).getText(), "Undo successfully!");
	  }
	  
	  @Test
	  public void showLoadTest() {
		  type("load").push(KeyCode.ENTER);
		  assertEquals(((Label) find("#sig")).getText(), "Loaded from " + Storage.getInstance().getPath() + Storage.getInstance().getfileName());
	  }
	  
	  @Test
	  public void showCompleteTest() {
		  type("complete 1").push(KeyCode.ENTER);
		  assertEquals(((Label) find("#sig")).getText(), "Task complete!");
	  }
	  
	  @Test
	  public void showUnComOrArcTest() {
		  type("uncomplete 1").push(KeyCode.ENTER);
		  assertEquals(((Label) find("#sig")).getText(), "Task recovered!");
		  type("display").push(KeyCode.ENTER);
		  type("unarchived 1").push(KeyCode.ENTER);
		  assertEquals(((Label) find("#sig")).getText(), "Task recovered!");
	  }
	  
	  @Test
	  public void showArchiveTest() {
		  type("archive 1").push(KeyCode.ENTER);
		  assertEquals(((Label) find("#sig")).getText(), "Task archived!");
	  }
	  
	  @Test
	  public void showClearTest() {
		  type("clear").push(KeyCode.ENTER);
		  assertEquals(((Label) find("#sig")).getText(), "All tasks selected have been cleared.");
	  }
	  
	  @Test
	  public void showHelpTest() {
		  type("help").push(KeyCode.ENTER);
		  assertEquals(((Text) find("#help-title")).getText(), "Sample format:");
		  assertEquals(((Text) find("#help-string")).getText(), Controller.getHelpString());
		  assertEquals(((Text) find("#help-end")).getText(), "Press <ESC> to close.");
		  push(KeyCode.ESCAPE);	  
		  
	  }
	  
	  @Test
	  public void displayTodayTitleTest() {
		  type("display").push(KeyCode.ENTER);
		  assertThat(find("#task-grid"), NodeMatchers.hasChild("#today-group"));
	  }
	  @Test
	  public void displayFollowingTitleTest() {
		  type("display").push(KeyCode.ENTER);
		  assertThat(find("#task-grid"), NodeMatchers.hasChild("#following-group"));
	  }
	  @Test
	  public void displayFloatingTitleTest() {
		  type("display").push(KeyCode.ENTER);
		  assertThat(find("#task-grid"), NodeMatchers.hasChild("#floating-group"));
	  }
	  
	  @Test
	  public void displayAEventTaskTest() {
		  type("clear").push(KeyCode.ENTER);
		  type("add meeting from today 2pm to tomorrow 2pm").push(KeyCode.ENTER);
		  assertEquals(((Text) find("#event-name")).getText(), " " + Integer.valueOf(1).toString() + ". " + "meeting");
		  assertEquals(((Text) find("#event-info")).getText(), "  S:  " + LocalDate.now().toString() + " " + "14:00" + "    E:  " + LocalDate.now().plusDays(1).toString() + " "
					+ "14:00");
	  }
	  @Test
	  public void displayAFloatingTaskTest() {
		  type("clear").push(KeyCode.ENTER);
		  type("add reading competitive programming").push(KeyCode.ENTER);
		  assertEquals(((Text) find("#floating-name")).getText(), " " + Integer.valueOf(1).toString() + ". " + "reading competitive programming");
	  }
	  
	  @Test
	  public void displayADeadlineTaskTest() {
		  type("clear").push(KeyCode.ENTER);
		  type("add dinner date by tomorrow 8pm").push(KeyCode.ENTER);
		  assertEquals(((Text) find("#deadline-name")).getText(), " " + Integer.valueOf(1).toString() + ". " + "dinner date");
		  assertEquals(((Text) find("#deadline-info")).getText(), "    By: " + LocalDate.now().plusDays(1).toString() + " " + "20:00");
	  }
	  

	  
	  
	  
	  
	  

	}
