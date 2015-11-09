package test;
/* Integration test suites to run all the available tests together.
 * @@author Jason (A0127830J)
 */

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses({
   UiTest.class,
   SystematicTest.class,
   LogicTest.class,
   CommandParserTest.class,
   TestStorage.class,
   TaskMemoryTest.class,
   TestCreate.class,
   TestCreateBulk.class,
   TestDelete.class,
   TestDeleteBulk.class,
   TestUpdate.class

})
public class TestCommands {   
}