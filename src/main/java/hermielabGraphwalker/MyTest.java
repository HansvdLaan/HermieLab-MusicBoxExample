package hermielabGraphwalker;

import instancemanager.InstanceManager;
import org.graphwalker.core.machine.ExecutionContext;
import org.graphwalker.java.annotation.GraphWalker;
import sample.Controller;
import sample.hermielab.ApplicationAdapter;
import sample.hermielab.ApplicationInjector;

import static org.junit.Assert.assertEquals;


@GraphWalker(value = "random(edge_coverage(100))", start = "v_Off")
public class MyTest extends ExecutionContext implements myTest {


    private ApplicationAdapter adapter;

    private String getState() {
        Controller controller = (Controller) InstanceManager.getInstance().getClassInstance(Controller.class);
        return controller.getState();
    }

    public void init() {
        new ApplicationInjector();
        InstanceManager manager = InstanceManager.getInstance();
        adapter = new ApplicationAdapter(manager);
        adapter.start();
    }
    private boolean init = false;

    @Override
    public void v_Off() {
        if(!init) {
            init();
            init = true;
        }
        System.out.println("v_Off");
        assertEquals("OFF", getState());
    }

    @Override
    public void e_PushQuit_Quit() {
        System.out.println("e_PushQuit");
        adapter.pushQuit();
        assertEquals("QUIT", adapter.output());
    }

    @Override
    public void e_PushQuit_None() {
        System.out.println("e_PushQuit");
        adapter.pushQuit();
        assertEquals("NONE", adapter.output());
    }

    @Override
    public void v_Both() {
        System.out.println("v_both");
        assertEquals("BOTH", getState());

    }

    @Override
    public void v_Rhythm() {
        System.out.println("v_rhythm");
        assertEquals("RHYTHM", getState());

    }

    @Override
    public void e_PushRhythm_None() {
        System.out.println("e_PushRhythm");
        adapter.pushRhythm();
        assertEquals("NONE", adapter.output());
    }

    @Override
    public void e_PushRhythm_StartRhythm() {
        System.out.println("e_PushRhythm");
        adapter.pushRhythm();
        assertEquals("STARTRHYTHM", adapter.output());

    }

    @Override
    public void v_Song() {
        System.out.println("v_song");
        assertEquals("SONG", getState());
    }

    @Override
    public void e_PushSong_None() {
        System.out.println("e_PushSong");
        adapter.pushSong();
        assertEquals("NONE", adapter.output());


    }

    @Override
    public void e_PushSong_StartSong() {
        System.out.println("e_PushSong");
        adapter.pushSong();
        assertEquals("STARTSONG", adapter.output());
    }




}
