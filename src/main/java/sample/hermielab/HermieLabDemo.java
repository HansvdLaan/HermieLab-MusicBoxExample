package sample.hermielab;

import adapter.Adapter;
import instancemanager.InstanceManager;
import javafx.application.Application;
import javafx.stage.Stage;
import learningsetup.ExampleMealyMachineLearningSetup;

import java.nio.file.Paths;
import java.util.Random;

/**
 * Created by Hans on 5-3-2018.
 */
public class HermieLabDemo extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        ApplicationInjector injector = new ApplicationInjector();
        injector.inject();
        Adapter adapter = new ApplicationAdapter(InstanceManager.getInstance());
        ExampleMealyMachineLearningSetup setup = new ExampleMealyMachineLearningSetup(adapter,
                Paths.get("src","main","java","sample","hermielab"),
                "experiment1",
        1,
        10,
        10000,
        new Random(42));
        setup.initializeOracles();
        setup.executeExperiment();
    }
}
