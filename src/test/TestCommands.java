package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import controller.LogicTest;
import view.UiTest;
@RunWith(Suite.class)
@Suite.SuiteClasses({
   CommandParserTest.class,
   UiTest.class,
   LogicTest.class,
   TestStorage.class
})
public class TestCommands {   
}