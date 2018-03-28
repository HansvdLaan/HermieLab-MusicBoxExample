import instancemanager.InstanceManager;
import javassist.CannotCompileException;
import javassist.NotFoundException;
import junit.framework.TestCase;
import org.junit.Test;
import sample.Main;
import sample.hermielab.ApplicationAdapter;
import sample.hermielab.ApplicationInjector;

public class simpleTest {

    private ApplicationAdapter adapter;

    @Test
    public void testSout() throws NotFoundException, CannotCompileException {
        ApplicationInjector injector = new ApplicationInjector();
        injector.inject();
        adapter = new ApplicationAdapter(InstanceManager.getInstance());
        adapter.start();

    }
}
