package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;

import static org.hamcrest.CoreMatchers.is;

import org.junit.Test;

import com.athaydes.automaton.FXApp;

import com.athaydes.automaton.cli.AutomatonScriptRunner;

import view.MainApp;

public class AppTest {

    static class ScriptOutputCapturer {
        List<String> strings = new ArrayList<>();

        public void write(String s) {
            strings.add(s);
        }

        public String lastLine() {
            return strings.get(strings.size() - 2); // last line is just new-line
        }
    }

    @BeforeClass
    public static void setup() {
        FXApp.startApp(new MainApp());
    }

    @Test
    public void firstTest() {
        runScript("src/test/groovy/firstAScript.groovy");
        
    }

    public static void runScript(String path) {
        try { // let Scene get started properly, may take some time as Automaton replaces the Scene between tests
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ScriptOutputCapturer writer = new ScriptOutputCapturer();
        AutomatonScriptRunner.getInstance().run(path, writer);

        System.out.println("Writer: " + writer.strings);

        assertThat(writer.strings.isEmpty(), is(false));
        assertThat(writer.lastLine(), is("Test PASSED!"));
    }

}
