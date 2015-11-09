package test;
/* Integration test suites to run all the available tests together.
 * @@author Jason (A0127830J)
 */

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
   TaskMemoryTest.class,
   TestCreate.class,
   TestCreateBulk.class,
   TestDelete.class,
   TestDeleteBulk.class,
   TestUpdate.class,
   SystematicTest.class,
})
public class TestCommands {   
}