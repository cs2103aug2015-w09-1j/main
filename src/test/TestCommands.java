package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
@RunWith(Suite.class)
@Suite.SuiteClasses({
   TestCreate.class,
   TestDelete.class,
   TestCreateBulk.class,
   TestDeleteBulk.class,
   TestUpdate.class
})
public class TestCommands {   
}