package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import controller.LogicTest;
import view.UiTest;
@RunWith(Suite.class)
@Suite.SuiteClasses({
	UiTest.class,
   LogicTest.class,
   CommandParserTest.class,
   TestStorage.class,
   SystematicTest.class
})
public class TestCommands {   
}