package sample.hermielab;

import adapter.Adapter;
import instancemanager.InstanceManager;
import java.lang.IllegalArgumentException;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.stage.Stage;
import org.graphwalker.core.statistics.Execution;
import sample.Controller;
import sample.Main;
import sample.MusicBoxOutput;

public class ApplicationAdapter extends Adapter {
	private InstanceManager instance;

	public ApplicationAdapter(InstanceManager instanceManager) {
		this.instance = instanceManager;

	}

	@Override
	public boolean checkPredicate(String ID) {
		switch(ID) {
			default:
				throw new IllegalArgumentException("checkPredicate:unkown ID:" + ID);
		}
	}

	public void pushQuit() {
		((Controller) instance.getClassInstance(Controller.class)).quitPushed(new ActionEvent());
	}

	public void pushRhythm() {
		((Controller) instance.getClassInstance(Controller.class)).rhythmPushed(new ActionEvent());
	}

	public void pushSong() {
		((Controller) instance.getClassInstance(Controller.class)).songPushed(new ActionEvent());
	}

	@Override
	public Optional<Object> processInput(String ID) {
		switch(ID) {
			case "PushRhythm":
				this.pushRhythm();
				return Optional.empty();
			case "PushSong":
				this.pushSong();
				return Optional.empty();
			case "PushQuit":
				this.pushQuit();
				return Optional.empty();
			default:
				throw new IllegalArgumentException("processInput:unkown ID:" + ID);
		}
	}

	public String output() {
		return ((Controller) instance.getClassInstance(Controller.class)).getOutput();
	}

	@Override
	public Object processOutput(String ID) {
		switch(ID) {
			case "output":
				return this.output();
			case "this":
			return Optional.of(this.getInputCO());
			default:
				throw new IllegalArgumentException("processOutput:unkown ID:" + ID);
		}
	}

	@Override
	public void start() {
		System.out.println("[DEBUG] Starting Adapter");
		this.start0();
	}

	public void start0() {
		try {
			new Thread(() -> {
					try {
						System.out.println("[DEBUG] Launching Main");
						Main.launch(Main.class);
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println(e.getMessage());
					}
			}
			).start();
			Thread.sleep(2000);

			System.out.println("[DEBUG] Main was created:" + (InstanceManager.getInstance().getClassInstance(Main.class) != null));
			System.out.println("[DEBUG] Controller was created:" + (InstanceManager.getInstance().getClassInstance(Controller.class) != null));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize() {
	}

	@Override
	public void shutdown() {
	}

	@Override
	public void preQuery() {
	}

	@Override
	public void postQuery() {
		((Controller) instance.getClassInstance(Controller.class)).reset();
	}

	@Override
	public void preInputInvocation() {
	}

	@Override
	public void postInputInvocation() {
	}

	@Override
	public void preOutputInvocation() {
	}

	@Override
	public void postOutputInvocation() {
	}

	@Override
	public Object transformOutput(String ID, Object output) {
		switch(ID) {
			case "toBoolean":
			return (Boolean) output;
			case "toString":
			return output.toString();
			default:
				throw new IllegalArgumentException("transformOutput:unkown ID:" + ID);
		}
	}
}
